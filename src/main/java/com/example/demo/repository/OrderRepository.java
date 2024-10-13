package com.example.demo.repository;

import com.example.demo.Entity.Account;
import com.example.demo.Entity.KoiOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<KoiOrder, Long> {
    List<KoiOrder> findOrderByAccount(Account account);
}
