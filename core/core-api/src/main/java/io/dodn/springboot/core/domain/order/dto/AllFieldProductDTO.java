package io.dodn.springboot.core.domain.order.dto

import java.math.BigDecimal;

import io.dodn.springboot.core.domain.product.Product;
import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;,

public record AllFieldProductDTO(
	Long productNumber,
	ProductType type,
	ProductSellingStatus sellingStatus,
	String name,
	BigDecimal price,
	Long quantity
) {

	public static AllFieldProductDTO of(Product product) {
		return new AllFieldProductDTO(
			product.getProductId(),
			product.getType(),
			product.getSellingStatus(),
			product.getName(),
			product.getPrice(),
			product.getStockQuantity()
			);
	}

}
