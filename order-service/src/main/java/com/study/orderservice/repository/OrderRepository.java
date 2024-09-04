package com.study.orderservice.repository;

import com.study.orderservice.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
//    List<Orders> findByMemberId(Long memberId);
}
