package io.dodn.springboot.storage.db.core.entity.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.dodn.springboot.core.enums.OrderStatus;
import io.dodn.springboot.storage.db.core.BaseEntity;
import io.dodn.springboot.storage.db.core.entity.orderproduct.OrderProductEntity;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;
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
public class OrderEntity extends BaseEntity {


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private int totalPrice;

    private LocalDateTime registeredDateTime;

    private String userEmail;


    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderProductEntity> orderProducts = new ArrayList<>();

    @Builder
    private OrderEntity(String userEmail  ,List<ProductEntity> products, OrderStatus orderStatus, LocalDateTime registeredDateTime) {
        this.orderStatus = orderStatus;
        this.totalPrice = calculateTotalPrice(products);
        this.registeredDateTime = registeredDateTime;
        this.orderProducts = products.stream()
                .map(OrderProductEntity::of)
                .collect(Collectors.toList());
        this.userEmail = userEmail;
    }


    public static OrderEntity create(List<ProductEntity>  products,LocalDateTime registeredDateTime,String userEmail) {
    return OrderEntity.builder()
            .orderStatus(OrderStatus.INIT)
            .products(products)
            .registeredDateTime(registeredDateTime)
            .userEmail( userEmail)
            .build();
    }

    public static OrderEntity create(List<ProductEntity>  products,LocalDateTime registeredDateTime) {
    return OrderEntity.builder()
            .orderStatus(OrderStatus.INIT)
            .products(products)
            .registeredDateTime(registeredDateTime)
            .build();
    }


    private static int calculateTotalPrice(List<ProductEntity> products) {
        return products.stream()
                .mapToInt(ProductEntity::getPrice)
                .sum();
    }


}
