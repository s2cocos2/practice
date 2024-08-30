package com.study.orderservice.dto.response;

import com.study.orderservice.entity.OrderDetails;
import com.study.orderservice.type.OrderStatusEnum;
import lombok.Getter;

@Getter
public class OrderResponseDto {
    private Long orderId;
    private String productName;
    private Long price;
    private Long count;
    private OrderStatusEnum status;
    //총가격 및 갯수로 수정

    public OrderResponseDto(OrderDetails orderDetails) {
        this.orderId = orderDetails.getOrders().getOrdersId();
//        this.productName = orderDetails.getProduct().getTitle();
        this.price = orderDetails.getPrice();
        this.count = orderDetails.getCount();
        this.status = orderDetails.getOrders().getStatus();
    }
}
