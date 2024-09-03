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

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long productId;

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
