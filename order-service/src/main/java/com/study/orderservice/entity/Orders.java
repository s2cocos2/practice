package com.study.orderservice.entity;

import com.study.orderservice.type.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Orders extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;

    @Column(nullable = false)
    private Long totalPrice;

    @Column(nullable = true)
    private Long totalCount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @Column(nullable = false)
    private Long memberId;

    // 수정하기
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetails> orderDetailsList = new ArrayList<>();

    public Orders(OrderStatusEnum status) {
        this.status = status;
    }

    public void addOrderDetails(OrderDetails orderDetails) {
        orderDetailsList.add(orderDetails);
        orderDetails.updateOrders(this);
        updateTotalPrice();
    }

//    public void removeOrderDetails(OrderDetails orderDetails) {
//        orderDetailsList.remove(orderDetails);
//        orderDetails.removeOrders();
//        updateTotalPrice();
//    }

    private void updateTotalPrice() {
        this.totalPrice = orderDetailsList.stream()
                .mapToLong(od -> od.getPrice() * od.getCount())
                .sum();
    }

    public void changeStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return getCreatedAt().toLocalDate();
    }


}
