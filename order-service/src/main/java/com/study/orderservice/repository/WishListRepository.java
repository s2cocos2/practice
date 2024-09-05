package com.study.orderservice.repository;

import com.study.orderservice.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    WishList findByMemberIdAndProductId(Long memberId, Long productId);
//
    List<WishList> findByMemberId(Long memberId);

    Optional<WishList> findByWishListId(Long wishListId);

//    Optional<WishList> findByIdAndUser(Long wishListId, User user);
//    Optional<WishList> findByWishListIdAndUser_UserId(Long wishListId, Long userId);
}
