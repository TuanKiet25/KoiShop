package com.example.demo.Service;

import com.example.demo.Entity.Account;
import com.example.demo.Entity.enums.Role;
import com.example.demo.exception.*;
import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.request.RegisterRequest;
import com.example.demo.model.response.EmailDetail;
import com.example.demo.model.response.LoginResponse;
import com.example.demo.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

//    @Autowired
//    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    EmailService emailService;


    public Account register(RegisterRequest registerRequest) {
        try {
            // RegisterRequest => Account
//            Account account = modelMapper.map(registerRequest, Account.class);
            Account account = new Account();
            account.setEmail(registerRequest.getEmail());
            account.setRole(Role.CUSTOMER);
            account.setPhone(registerRequest.getPhone());
            account.setFullName(registerRequest.getFullName());
            String password = registerRequest.getPassword();
            account.setPassword(passwordEncoder.encode(password));
            Account newAccount = accountRepository.save(account);
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setAccount(newAccount);
            emailDetail.setSubject("Chào mừng bạn đến với trang web Koi Farm Shop của chúng tôi");
            emailDetail.setLink("-link web-");
            emailService.sentEmail(emailDetail);
            return newAccount;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getPhone(),
                    loginRequest.getPassword()
            ));
            Account account = (Account) authentication.getPrincipal();
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setPhone(account.getPhone());
            loginResponse.setEmail(account.getEmail());
            loginResponse.setFullName(account.getFullName());
            loginResponse.setId(account.getId());
            loginResponse.setRole(account.getRole());
            loginResponse.setToken(tokenService.generateToken(account));
            return loginResponse;
        } catch (Exception e) {
            throw new BadRequestException("Invalid username or password!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        return accountRepository.findAccountByPhone(phone);
    }
    public Account getCurrentAccount(){
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountRepository.findAccountById(account.getId());
    }
}
