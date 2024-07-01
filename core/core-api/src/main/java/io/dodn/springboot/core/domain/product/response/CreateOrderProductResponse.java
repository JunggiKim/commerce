package io.dodn.springboot.core.domain.product.response;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;
import lombok.Builder;



@Builder
public record CreateOrderProductResponse(
		 Long productId,
		 ProductType type,
		 String name,
		 Long price
){


	public static CreateOrderProductResponse of(ProductEntity productEntity) {
		return CreateOrderProductResponse.builder()
			.productId(productEntity.getId())
			.type(productEntity.getType())
			.name(productEntity.getName())
			.price(productEntity.getPrice())
			.build();
	}
}
