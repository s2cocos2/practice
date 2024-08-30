package com.study.orderservice.service;

import com.study.orderservice.dto.request.WishListRequestDto;
import com.study.orderservice.dto.response.WishListResponseDto;
import com.study.orderservice.repository.WishListRepository;
import com.study.orderservice.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepository wishListRepository;
//    private final UserRepository userRepository;
//    private final ProductRepository productRepository;
    // TODO : user, product 연결

    // wishlist에 상품 추가
    public CommonResponse addWishList(WishListRequestDto requestDto) {
//        User currentUser = userRepository.findById(user.getUserId()).orElseThrow(
//                () -> new CommonException(ClientErrorCode.NO_ACCOUNT));
//
//        Product product = productRepository.findById(requestDto.productId()).orElseThrow(
//                () -> new CommonException(ClientErrorCode.NO_PRODUCT));
//
//        WishList existingWishListItem = wishListRepository.findByUserAndProduct(currentUser, product);
//        if (existingWishListItem != null) {
//            existingWishListItem.updateCount(existingWishListItem.getCount() + requestDto.count());
//            wishListRepository.save(existingWishListItem);
//        } else {
//            WishList wishList = new WishList(currentUser, product, requestDto.count());
//            wishListRepository.save(wishList);
//        }
//
//        return new CommonResponse<>("위시리스트에 성공적으로 등록하였습니다.");
        return null;

    }

    // wishlist 조회
    @Transactional(readOnly = true)
    public CommonResponse<List<WishListResponseDto>> getWishList() {
//        User currentUser = userRepository.findById(user.getUserId()).orElseThrow(
//                () -> new CommonException(ClientErrorCode.NO_ACCOUNT));
//
//        List<WishList> wishLists = wishListRepository.findByUser(currentUser);
//
//        List<WishListResponseDto> responseDtos = wishLists.stream()
//                .map(WishListResponseDto::new)
//                .collect(Collectors.toList());
//
//        return new CommonResponse<>("위시 리스트 조회에 성공했습니다", responseDtos);
        return null;
    }

    // wishlist 수정
    @Transactional
    public CommonResponse updateWishList(Long wishListId, WishListRequestDto requestDto) {
//        User currentUser = userRepository.findById(user.getUserId()).orElseThrow(
//                () -> new CommonException(ClientErrorCode.NO_ACCOUNT));
//
//        WishList wishList = wishListRepository.findByWishListIdAndUser_UserId(wishListId, currentUser.getUserId()).orElseThrow(
//                () -> new CommonException(ClientErrorCode.NO_WISHLIST));
//
//        wishList.updateCount(requestDto.count());
//
//        wishListRepository.save(wishList);
        return new CommonResponse("위시리스트가 성공적으로 수정되었습니다.");

    }

    // wishlist 삭제
    public CommonResponse deleteWishList(Long wishListId) {
//        User currentUser = userRepository.findById(user.getUserId()).orElseThrow(
//                () -> new CommonException(ClientErrorCode.NO_ACCOUNT));
//
//        WishList wishList = wishListRepository.findByWishListIdAndUser_UserId(wishListId, currentUser.getUserId()).orElseThrow(
//                () -> new CommonException(ClientErrorCode.NO_WISHLIST));
//
//        wishListRepository.delete(wishList);
        return new CommonResponse("위시리스트가 성공적으로 삭제되었습니다.");
    }
}
