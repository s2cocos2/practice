package com.study.orderservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class OrderDetails extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailsId;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id", nullable = false)
    private Orders orders;

    @Column(nullable = false)
    private Long productId;

//    public OrderDetails(Product product, Long price, Long count){
//        this.product = product;
//        this.price = price;
//        this.count = count;
//    }
    public OrderDetails(Long price, Long count){
        this.price = price;
        this.count = count;
    }

    public void removeOrders() {
        if (this.orders != null) {
            this.orders.getOrderDetailsList().remove(this);
            this.orders = null;
        }
    }

    public void updateOrders(Orders orders) {
        this.orders = orders;
    }




}
