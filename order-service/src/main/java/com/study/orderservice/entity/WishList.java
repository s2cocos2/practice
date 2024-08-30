package com.study.orderservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class WishList extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishListId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product;

    @Column(nullable = false)
    private Long count;

//    public WishList(User user, Product product, Long count) {
//        this.user = user;
//        this.product = product;
//        this.count = count;
//    }

    public void updateCount(Long newCount) {
        this.count = newCount;
    }
}
