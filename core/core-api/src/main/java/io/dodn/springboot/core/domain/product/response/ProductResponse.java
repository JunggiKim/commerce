package io.dodn.springboot.core.domain.product.response;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;
import lombok.Builder;
@Builder
public record ProductResponse (
		 Long id,
		 int productNumber,
		 ProductType type,
		 ProductSellingStatus sellingStatus,
		 String name,
		 int price
){


	public static ProductResponse of(ProductEntity productEntity) {
		return ProductResponse.builder()
			.id(productEntity.getId())
			.productNumber(productEntity.getProductNumber())
			.type(productEntity.getType())
			.sellingStatus(productEntity.getSellingStatus())
			.name(productEntity.getName())
			.price(productEntity.getPrice())
			.build();
	}
}
