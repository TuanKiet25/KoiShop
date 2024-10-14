package com.example.demo.repository;

import com.example.demo.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {

    //select
    Account findAccountByPhone(String phone);

    Account findAccountById(long id);
}
