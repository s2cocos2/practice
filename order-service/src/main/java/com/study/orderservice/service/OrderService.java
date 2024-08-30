package com.study.orderservice.service;

import com.study.orderservice.dto.request.OrderRequestDto;
import com.study.orderservice.dto.response.OrderResponseDto;
import com.study.orderservice.repository.OrderRepository;
import com.study.orderservice.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
//    private final UserRepository userRepository;
//    private final ProductRepository productRepository;
    // TODO : user, product 연결


    // 주문 하기
    @Transactional
    public List<OrderResponseDto> createOrder(OrderRequestDto requestDto) {
//    public List<OrderResponseDto> createOrder(OrderRequestDto requestDto, User user) {
//        User currentUser = userRepository.findById(user.getUserId()).orElseThrow(
//                () -> new CommonException(ClientErrorCode.NO_ACCOUNT));
//
//        Product product = productRepository.findById(requestDto.productId()).orElseThrow(
//                () -> new CommonException(ClientErrorCode.NO_PRODUCT));

//        OrderDetails orderDetails = new OrderDetails(product, requestDto.count(), product.getPrice());
//
//        Orders orders = new Orders(currentUser, OrderStatusEnum.ORDERED);
//        orders.addOrderDetails(orderDetails);

//        orderRepository.save(orders);
//        List<OrderResponseDto> responseDtos = orders.getOrderDetailsList().stream()
//                .map(OrderResponseDto::new)
//                .collect(Collectors.toList());
//        return responseDtos;
        return null;

    }

    // 주문 조회
    @Transactional(readOnly = true)
    public CommonResponse<OrderResponseDto> getOrder(Long orderId) {
//        User user = userRepository.findById(userId).orElseThrow(
//                () -> new CommonException(ClientErrorCode.NO_ACCOUNT));

//        Orders orders = orderRepository.findById(orderId).orElseThrow(
//                () -> new CommonException(ClientErrorCode.NO_ORDER));
//
//        OrderDetails orderDetails = orders.getOrderDetailsList().stream()
//                .findFirst().orElseThrow(() -> new CommonException(ClientErrorCode.NO_ORDER_DETAILS));

//        return new CommonResponse("Orders details retrieved successfully", orderDetails);
        return null;
    }

    // 주문 취소
    @Transactional
    public CommonResponse cancelOrder(Long orderId) {
//        userRepository.findById(userId).orElseThrow(
//                () -> new CommonException(ClientErrorCode.NO_ACCOUNT));
//
//        Orders orders = orderRepository.findById(orderId).orElseThrow(
//                () -> new CommonException(ClientErrorCode.NO_ORDER));
//
//        if (orders.getStatus() != OrderStatusEnum.ORDERED) {
//            throw new CommonException(ClientErrorCode.INVALID_ORDER_STATUS);
//        }
//
//        orders.changeStatus(OrderStatusEnum.CANCELED);
//        orderRepository.save(orders);
//
//        List<OrderDetails> orderDetailsList = orders.getOrderDetailsList();
//        for (int i = 0; i < orderDetailsList.size(); i++) {
//            OrderDetails orderDetails = orderDetailsList.get(i);
//            Product product = orderDetails.getProduct();
//            Long count = orderDetails.getCount();
//
//            product.reStock(product.getStock() + count);
//            productRepository.save(product);
//        }
//        return new CommonResponse("성공");
        return null;

    }

    // 반품하기
    @Transactional
    public CommonResponse returnOrder(Long orderId, Long productId) {
//        userRepository.findById(user.getUserId()).orElseThrow(
//                () -> new CommonException(ClientErrorCode.NO_ACCOUNT));
//
//        Orders orders = orderRepository.findById(orderId).orElseThrow(
//                () -> new CommonException(ClientErrorCode.NO_ORDER));
//
//        OrderDetails orderDetails = orders.getOrderDetailsList().stream()
//                .filter(od -> od.getProduct().getProductId().equals(productId))
//                .findFirst()
//                .orElseThrow(() -> new CommonException(ClientErrorCode.NO_ORDER_DETAILS));
//
//        if (orders.getOrderDate().plusDays(3).isBefore(LocalDate.now())) {
//            throw new CommonException(ClientErrorCode.RETURN_EXPIRED);
//        }
//
//        Product product = orderDetails.getProduct();
//        Long count = orderDetails.getCount();
//        product.reStock(product.getStock() + count);
//        productRepository.save(product);
//
//        orderDetails.removeOrders();
//        orders.getOrderDetailsList().removeIf(od -> od.equals(orderDetails));
//        orderRepository.save(orders);
//
//        return new CommonResponse<>("성공적으로 반품 되었습니다.");
        return null;

    }
}
