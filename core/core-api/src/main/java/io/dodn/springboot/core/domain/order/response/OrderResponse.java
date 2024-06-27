package io.dodn.springboot.core.domain.order.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import io.dodn.springboot.core.domain.order.Order;
import io.dodn.springboot.core.domain.product.response.ProductResponse;
import lombok.Builder;
import lombok.Getter;



@Getter
public class OrderResponse {

    private Long id;


    private int totalPrice;

    private LocalDateTime registeredDateTime;

    private List<ProductResponse> products;

    private String userEmail;
    @Builder
    public OrderResponse(String userEmail,Long id, int totalPrice, LocalDateTime registeredDateTime, List<ProductResponse> products) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.registeredDateTime = registeredDateTime;
        this.products = products;
        this.userEmail = userEmail;
    }

    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .registeredDateTime(order.getRegisteredDateTime())
                .products(order.getOrderProducts().stream()
                        .map(orderProduct -> ProductResponse.of(orderProduct.getProduct()))
                        .collect(Collectors.toList())
                )
                .userEmail(order.getUserEmail())
                .build();
    }
}
