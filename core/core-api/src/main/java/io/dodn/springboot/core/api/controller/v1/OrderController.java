package io.dodn.springboot.core.api.controller.v1;

import java.time.LocalDateTime;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.dodn.springboot.core.api.domain.order.request.OrderCreateRequest;
import io.dodn.springboot.core.api.domain.order.response.OrderResponse;
import io.dodn.springboot.core.api.domain.order.service.OrderService;
import io.dodn.springboot.core.api.support.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public ApiResponse<OrderResponse> createOrder(@Valid @RequestBody OrderCreateRequest request) throws Exception {
        LocalDateTime registeredDateTime = LocalDateTime.now();
        return ApiResponse.success(orderService.createOrder(request.toServiceRequest(), registeredDateTime));
    }



}
