package io.dodn.springboot.storage.db.core.domain.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.dodn.springboot.core.enums.OrderStatus;
import io.dodn.springboot.storage.db.core.BaseEntity;
import io.dodn.springboot.storage.db.core.domain.orderproduct.OrderProduct;
import io.dodn.springboot.storage.db.core.domain.product.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private int totalPrice;

    private LocalDateTime registeredDateTime;

    private String userEmail;


    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Builder
    private Order(String userEmail  ,List<Product> products, OrderStatus orderStatus, LocalDateTime registeredDateTime) {
        this.orderStatus = orderStatus;
        this.totalPrice = calculateTotalPrice(products);
        this.registeredDateTime = registeredDateTime;
        this.orderProducts = products.stream()
                .map(product -> OrderProduct.of(this , product))
                .collect(Collectors.toList());
        this.userEmail = userEmail;
    }


    public static Order create(List<Product>  products,LocalDateTime registeredDateTime,String userEmail) {
    return Order.builder()
            .orderStatus(OrderStatus.INIT)
            .products(products)
            .registeredDateTime(registeredDateTime)
            .userEmail( userEmail)
            .build();
    }

    public static Order create(List<Product>  products,LocalDateTime registeredDateTime) {
    return Order.builder()
            .orderStatus(OrderStatus.INIT)
            .products(products)
            .registeredDateTime(registeredDateTime)
            .build();
    }


    private static int calculateTotalPrice(List<Product> products) {
        return products.stream()
                .mapToInt(Product::getPrice)
                .sum();
    }


}
