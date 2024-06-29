package io.dodn.springboot.core.domain.product;



import java.math.BigDecimal;

import io.dodn.springboot.core.domain.stock.Stock;
import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

	private Long productNumber;

	private ProductType type;

	private ProductSellingStatus sellingStatus;

	private String name;

	private BigDecimal price;

	private Long quantity;

	@Builder
	private Product(Long productNumber, ProductType type, ProductSellingStatus sellingStatus, String name,
		BigDecimal price , Long quantity) {
		this.productNumber = productNumber;
   		this.type = type;
		this.sellingStatus = sellingStatus;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}



    public  boolean isQuantityLessThan(Long quantity) {
        return this.quantity < quantity;

    }


    public void deductQuantity(Long quantity) throws IllegalAccessException {
        if (isQuantityLessThan(quantity)) {
            throw new IllegalArgumentException(this.name +  "차감할 재고 수량이 없습니다.");
        }
        this.quantity -= quantity;
    }

}
