package com.example.demo.Service;

import com.example.demo.Entity.Account;
import com.example.demo.Entity.Koi;
import com.example.demo.Entity.KoiOrder;
import com.example.demo.Entity.OrderDetail;
import com.example.demo.model.request.OrderDetailRequest;
import com.example.demo.model.request.OrderRequest;
import com.example.demo.repository.KoiRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    AccountService accountService;
    @Autowired
    KoiRepository koiRepository;
    @Autowired
    OrderRepository orderRepository;


    public KoiOrder createOrder(OrderRequest orderRequest){
        Account account = accountService.getCurrentAccount();
        List<OrderDetail> orderDetailList = new ArrayList<>();

        KoiOrder koiOrder = new KoiOrder();
        koiOrder.setAccount(account);
        koiOrder.setOrderDate(new Date());
        float total = 0;

        for(OrderDetailRequest orderDetailRequest : orderRequest.getDetailRequestlist()){
            Koi koi = koiRepository.findById(orderDetailRequest.getKoiId());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderDetailQuantity(orderDetailRequest.getQuantity());
            orderDetail.setPrice(koi.getPrice());
            orderDetail.setKoiOrder(koiOrder);
            orderDetail.setKoi(koi);
            orderDetailList.add(orderDetail);
            total += koi.getPrice() * orderDetailRequest.getQuantity();
        }
        koiOrder.setOrderDetails(orderDetailList);
        koiOrder.setTotalAmount(total);
        return orderRepository.save(koiOrder);
    }
    public List<KoiOrder> getAllOrder(){
        Account account = accountService.getCurrentAccount();
        List<KoiOrder>  koiOrderList = orderRepository.findOrderByAccount(account);
        return koiOrderList;
    }

    private String generateHMAC(String secretKey, String signData) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSha512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmacSha512.init(keySpec);
        byte[] hmacBytes = hmacSha512.doFinal(signData.getBytes(StandardCharsets.UTF_8));

        StringBuilder result = new StringBuilder();
        for (byte b : hmacBytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public String createUrl(OrderRequest orderRequest) throws  Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createDate = LocalDateTime.now();
        String formattedCreateDate = createDate.format(formatter);

        KoiOrder orders;
        orders = createOrder(orderRequest);
        float money = orders.getTotalAmount()*100;
        String amount = String.valueOf(money);

        String tmnCode = "OSMO6J5A";
        String secretKey = "0Z7ZVLADH3NHDS1K9WPITZP2KWNH84NM";
        String vnpUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        String returnUrl = "https://blearning.vn/guide/swp/docker-local?orderID=" + orders.getId();
        String currCode = "VND";

        Map<String, String> vnpParams = new TreeMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", tmnCode);
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_CurrCode", currCode);
        vnpParams.put("vnp_TxnRef", String.valueOf(orders.getId()));
        vnpParams.put("vnp_OrderInfo", "Thanh toan cho ma GD: " + orders.getId());
        vnpParams.put("vnp_OrderType", "other");
        vnpParams.put("vnp_Amount",amount);

        vnpParams.put("vnp_ReturnUrl", returnUrl);
        vnpParams.put("vnp_CreateDate", formattedCreateDate);
        vnpParams.put("vnp_IpAddr", "128.199.178.23");

        StringBuilder signDataBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            signDataBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("=");
            signDataBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("&");
        }
        signDataBuilder.deleteCharAt(signDataBuilder.length() - 1); // Remove last '&'

        String signData = signDataBuilder.toString();
        String signed = generateHMAC(secretKey, signData);

        vnpParams.put("vnp_SecureHash", signed);

        StringBuilder urlBuilder = new StringBuilder(vnpUrl);
        urlBuilder.append("?");
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("=");
            urlBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("&");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1); // Remove last '&'

        return urlBuilder.toString();
    }
}
