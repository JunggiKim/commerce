package io.dodn.springboot.core.domain.product;

import java.math.BigDecimal;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    private Long productId;

    private Long productNumber;

    private ProductType type;

    private ProductSellingStatus sellingStatus;

    private String name;

    private BigDecimal price;

    private Long stockQuantity;

    @Builder
    private Product(Long productNumber, Long productId, ProductType type, ProductSellingStatus sellingStatus,
            String name, BigDecimal price, Long stockQuantity) {
        this.productId = productId;
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productNumber = productNumber;
    }

    public Product Create(Long quantity) {
        return Product.builder()

            .build();

    }

    public boolean isQuantityLessThan(Long quantity) {
        return this.stockQuantity < quantity;

    }

    public void deductQuantity(Long quantity) throws IllegalAccessException {
        if (isQuantityLessThan(quantity)) {
            throw new IllegalArgumentException(this.name + "의 차감 할 재고 수량이 없습니다.");
        }
        this.stockQuantity -= quantity;
    }

}
