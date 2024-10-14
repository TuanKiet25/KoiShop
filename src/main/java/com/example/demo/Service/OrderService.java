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
}
