package com.study.orderservice.repository;

import com.study.orderservice.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Long> {
//    WishList findByUserAndProduct(User user, Product product);
//
//    List<WishList> findByUser(User user);

//    Optional<WishList> findByIdAndUser(Long wishListId, User user);
//    Optional<WishList> findByWishListIdAndUser_UserId(Long wishListId, Long userId);
}
