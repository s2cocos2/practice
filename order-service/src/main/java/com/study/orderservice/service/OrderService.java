package com.study.orderservice.service;

import com.study.orderservice.client.MemberServiceClient;
import com.study.orderservice.client.ProductServiceClient;
import com.study.orderservice.dto.request.OrderRequestDto;
import com.study.orderservice.dto.response.MemberResponseDto;
import com.study.orderservice.dto.response.OrderResponseDto;
import com.study.orderservice.dto.response.ProductResponseDto;
import com.study.orderservice.entity.OrderDetails;
import com.study.orderservice.entity.Orders;
import com.study.orderservice.exception.CommonException;
import com.study.orderservice.repository.OrderRepository;
import com.study.orderservice.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.study.orderservice.exception.errorcode.ClientErrorCode.*;
import static com.study.orderservice.response.SuccessMessage.*;
import static com.study.orderservice.type.OrderStatusEnum.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberServiceClient memberServiceClient;
    private final ProductServiceClient productServiceClient;

    // 주문 하기
    @Transactional
    public CommonResponse createOrder(OrderRequestDto requestDto) {
        MemberResponseDto member = memberServiceClient.getMemberById(requestDto.memberId());
        if (member == null) {
            throw new CommonException(NO_ACCOUNT);
        }

        ProductResponseDto product = productServiceClient.getProductById(requestDto.productId());
        if (product == null) {
            throw new CommonException(NO_PRODUCT);
        }

        OrderDetails orderDetails = new OrderDetails(product.getProductId(), requestDto.count(), product.getPrice());

        Orders orders = new Orders(member.getMemberId(), ORDERED);
        orders.addOrderDetails(orderDetails);

        orderRepository.save(orders);

        return new CommonResponse(ORDER_SUCCESS);
    }

    // 주문 조회
    @Transactional(readOnly = true)
    public CommonResponse<OrderResponseDto> getOrder(Long orderId) {
        Orders orders = orderRepository.findById(orderId).orElseThrow(
                () -> new CommonException(NO_ORDER));

        OrderDetails orderDetails = orders.getOrderDetailsList().stream()
                .findFirst().orElseThrow(() -> new CommonException(NO_ORDER_DETAILS));

        OrderResponseDto orderResponseDto = new OrderResponseDto(orderDetails);

        return new CommonResponse(GET_ORDER_SUCCESS, orderResponseDto);
    }

    // 주문 취소
    @Transactional
    public CommonResponse cancelOrder(Long orderId) {
        Orders orders = orderRepository.findById(orderId).orElseThrow(
                () -> new CommonException(NO_ORDER));

        if (orders.getStatus() != ORDERED) {
            throw new CommonException(INVALID_ORDER_STATUS);
        }

        orders.changeStatus(CANCELED);
        orderRepository.save(orders);

        // 재고 업데이트
        List<OrderDetails> orderDetailsList = orders.getOrderDetailsList();
        for (int i = 0; i < orderDetailsList.size(); i++) {
            OrderDetails orderDetails = orderDetailsList.get(i);
            Long productId = orderDetails.getProductId();
            Long count = orderDetails.getCount();

            ProductResponseDto product = productServiceClient.getProductById(productId);
            if (product == null) {
                throw new CommonException(NO_PRODUCT);
            }

            productServiceClient.updateProductStock(productId, product.getStock() + count);

        }
        return new CommonResponse(ORDER_CANCEL);

    }

    // 반품하기
    @Transactional
    public CommonResponse returnOrder(Long orderId, Long productId) {
        Orders orders = orderRepository.findById(orderId).orElseThrow(
                () -> new CommonException(NO_ORDER));

        OrderDetails orderDetails = orders.getOrderDetailsList().stream()
                .filter(od -> od.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new CommonException(NO_ORDER_DETAILS));

        if (orders.getOrderDate().plusDays(3).isBefore(LocalDate.now())) {
            throw new CommonException(RETURN_EXPIRED);
        }

        Long count = orderDetails.getCount();

        ProductResponseDto product = productServiceClient.getProductById(productId);
        if (product == null) {
            throw new CommonException(NO_PRODUCT);
        }

        productServiceClient.updateProductStock(productId, product.getStock() + count);

        orderDetails.removeOrders();
        orders.getOrderDetailsList().removeIf(od -> od.equals(orderDetails));
        orderRepository.save(orders);

        return new CommonResponse(RETURN_ORDER);

    }
}
