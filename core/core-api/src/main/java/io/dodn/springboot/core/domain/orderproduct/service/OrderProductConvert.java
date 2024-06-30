package io.dodn.springboot.core.domain.orderproduct.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import io.dodn.springboot.core.domain.orderproduct.OrderProduct;
import io.dodn.springboot.core.domain.product.Product;
import io.dodn.springboot.storage.db.core.entity.product.response.OrderCreatePersistenceResponse;

@Component

public class OrderProductConvert {

	public OrderProduct toDomain(OrderCreatePersistenceResponse dto) {
		Product product = Product.builder()
			.productNumber(dto.productNumber())
			.type(dto.type())
			.sellingStatus(dto.sellingStatus())
			.name(dto.name())
			.price(BigDecimal.valueOf(dto.price()))
			.build();

		return OrderProduct.builder()
			.product(product)
			.build();

	}

}
