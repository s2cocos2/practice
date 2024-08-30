package com.study.orderservice.dto.response;

import com.study.orderservice.entity.WishList;
import lombok.Getter;

@Getter
public class WishListResponseDto {
    private Long id;
    private String productName;
    private Long count;
    private Long price;

    public WishListResponseDto(WishList wishList) {
        this.id = wishList.getWishListId();
//        this.productName = wishList.getProduct().getTitle();
        this.count = wishList.getCount();
//        this.price = wishList.getProduct().getPrice();
    }
}
