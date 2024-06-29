package io.dodn.springboot.storage.db.core.entity.product.response;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;

public record AllFiledProductEntityDTO(
	 Long productNumber,
	 ProductType type,
	 ProductSellingStatus sellingStatus,
	 String name,
	 Long price,
	 Long quantity
){

	public static AllFiledProductEntityDTO of (ProductEntity productEntity){
		return new AllFiledProductEntityDTO(
		productEntity.getProductNumber(),
		productEntity.getType(),
		productEntity.getSellingStatus(),
		productEntity.getName(),
		productEntity.getPrice(),
		productEntity.getQuantity()
		);
	}

}
