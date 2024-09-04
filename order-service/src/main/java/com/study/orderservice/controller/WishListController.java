package com.study.orderservice.controller;

import com.study.orderservice.dto.request.WishListRequestDto;
import com.study.orderservice.dto.response.WishListResponseDto;
import com.study.orderservice.response.CommonResponse;
import com.study.orderservice.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishListController {

    private final WishListService wishListService;

    // wishlist에 상품 등록
    @PostMapping("")
    public ResponseEntity<CommonResponse> addWishList(@RequestBody WishListRequestDto requestDto) {
        return new ResponseEntity<>(wishListService.addWishList(requestDto), HttpStatus.CREATED);
    }

    // wishlist 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<CommonResponse<List<WishListResponseDto>>> getWishList(@PathVariable Long memberId){
        return new ResponseEntity<>(wishListService.getWishList(memberId), HttpStatus.OK);
    }

    // wishlist 수정
    @PatchMapping("{wishListId}")
    public ResponseEntity<CommonResponse> updateWishList(@PathVariable Long wishListId, @RequestBody WishListRequestDto requestDto){
        return new ResponseEntity<>(wishListService.updateWishList(wishListId, requestDto), HttpStatus.CREATED);
    }


    // wishlist 삭제
    @DeleteMapping("{wishListId}")
    public ResponseEntity<CommonResponse> deleteWishList(@PathVariable Long wishListId){
        return new ResponseEntity<>(wishListService.deleteWishList(wishListId), HttpStatus.CREATED);
    }

}
