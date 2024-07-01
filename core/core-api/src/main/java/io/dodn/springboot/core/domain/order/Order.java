package io.dodn.springboot.core.domain.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.dodn.springboot.core.domain.orderproduct.OrderProduct;
import io.dodn.springboot.core.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order  {

    private Long orderId;

    private OrderStatus orderStatus;

    private BigDecimal totalPrice;

    private LocalDateTime registeredDateTime;

    private String userEmail;

    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Builder
    private Order(Long orderId, String userEmail  , List<OrderProduct> products, OrderStatus orderStatus, LocalDateTime registeredDateTime, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.registeredDateTime = registeredDateTime;
        this.orderProducts = products;
        this.userEmail = userEmail;
    }


    public static Order create(List<OrderProduct>  products,LocalDateTime registeredDateTime,String userEmail) {
    return Order.builder()
            .orderStatus(OrderStatus.INIT)
            .totalPrice(calculateTotalPrice(products))
            .products(products)
            .registeredDateTime(registeredDateTime)
            .userEmail(userEmail)
            .build();
    }

    public static BigDecimal calculateTotalPrice(List<OrderProduct> products) {
        long sumNumber = products.stream().mapToLong(value -> value.getPrice().longValue()).sum();
        return BigDecimal.valueOf(sumNumber);
    }

    public static Order create(List<OrderProduct>  products , LocalDateTime registeredDateTime , BigDecimal totalPrice , String userEmail) {
    return Order.builder()
            .orderStatus(OrderStatus.INIT)
            .totalPrice(totalPrice)
            .registeredDateTime(registeredDateTime)
            .userEmail(userEmail)
            .products(products)
            .build();
    }


//    private static Long calculateTotalPrice(List<OrderProduct> products) {
//        return products.stream()
//                .mapToLong(product -> Long.parseLong(String.valueOf(product)))
//                .sum();
//    }


}
