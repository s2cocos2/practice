package com.study.orderservice.scheduler;

import com.study.orderservice.entity.Orders;
import com.study.orderservice.repository.OrderRepository;
import com.study.orderservice.type.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderStatusScheduler {

    private final OrderRepository orderRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateOrderStatus() {
        List<Orders> orders = orderRepository.findAll();


        for (Orders order : orders) {
            LocalDate orderDate = order.getOrderDate();

            if (order.getStatus() == OrderStatusEnum.ORDERED && orderDate.plusDays(1).isBefore(LocalDate.now())) {
                // 주문 D+1
                order.changeStatus(OrderStatusEnum.SHIPPING);
            } else if (order.getStatus() == OrderStatusEnum.SHIPPING && orderDate.plusDays(2).isBefore(LocalDate.now())) {
                // 주문 D+2
                order.changeStatus(OrderStatusEnum.DELIVERED);
            }

            orderRepository.save(order);
        }
    }
}
