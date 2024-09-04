package com.study.orderservice.service;

import com.study.orderservice.client.MemberServiceClient;
import com.study.orderservice.client.ProductServiceClient;
import com.study.orderservice.dto.request.WishListRequestDto;
import com.study.orderservice.dto.response.MemberResponseDto;
import com.study.orderservice.dto.response.ProductResponseDto;
import com.study.orderservice.dto.response.WishListResponseDto;
import com.study.orderservice.entity.WishList;
import com.study.orderservice.exception.CommonException;
import com.study.orderservice.repository.WishListRepository;
import com.study.orderservice.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.study.orderservice.exception.errorcode.ClientErrorCode.*;
import static com.study.orderservice.response.SuccessMessage.*;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepository wishListRepository;
    private final MemberServiceClient memberServiceClient;
    private final ProductServiceClient productServiceClient;

    // wishlist에 상품 추가
    public CommonResponse addWishList(WishListRequestDto requestDto) {
        MemberResponseDto member = memberServiceClient.getMemberById(requestDto.memberId());
        if (member == null) {
            throw new CommonException(NO_ACCOUNT);
        }

        ProductResponseDto product = productServiceClient.getProductById(requestDto.productId());
        if (product == null) {
            throw new CommonException(NO_PRODUCT);
        }

        WishList existingWishListItem = wishListRepository.findByMemberAndProduct(requestDto.memberId(), requestDto.productId());
        if (existingWishListItem != null) {
            existingWishListItem.updateCount(existingWishListItem.getCount() + requestDto.count());
            wishListRepository.save(existingWishListItem);
        } else {
            WishList wishList = new WishList(requestDto.memberId(), requestDto.productId(), requestDto.count());
            wishListRepository.save(wishList);
        }
        return new CommonResponse(WISHLIST_SUCCESS);
    }

    // wishlist 조회
    @Transactional(readOnly = true)
    public CommonResponse<List<WishListResponseDto>> getWishList(Long memberId) {
        MemberResponseDto member = memberServiceClient.getMemberById(memberId);
        if (member == null) {
            throw new CommonException(NO_ACCOUNT);
        }

        List<WishList> wishLists = wishListRepository.findByMemberId(memberId);

        List<WishListResponseDto> responseDtos = wishLists.stream()
                .map(WishListResponseDto::new)
                .collect(Collectors.toList());

        return new CommonResponse(GET_WISHLIST_SUCCESS, responseDtos);
    }

    // wishlist 수정
    @Transactional
    public CommonResponse updateWishList(Long wishListId, WishListRequestDto requestDto) {
        WishList wishList = wishListRepository.findByWishListId(wishListId).orElseThrow(
                () -> new CommonException(NO_WISHLIST));

        wishList.updateCount(requestDto.count());

        wishListRepository.save(wishList);
        return new CommonResponse(MODIFY_WISHLIST_SUCCESS);
    }

    // wishlist 삭제
    public CommonResponse deleteWishList(Long wishListId) {
        WishList wishList = wishListRepository.findByWishListId(wishListId).orElseThrow(
                () -> new CommonException(NO_WISHLIST));

        wishListRepository.delete(wishList);
        return new CommonResponse(DELETE_WISHLIST_SUCCESS);
    }
}
