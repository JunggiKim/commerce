package io.dodn.springboot.core.api.domain.product;



import io.dodn.springboot.core.api.domain.product.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.api.domain.product.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

	private Long id;

	private String productNumber;

	private ProductType type;

	private ProductSellingStatus sellingStatus;

	private String name;

	private int price;

	@Builder
	private Product(Long id, String productNumber, ProductType type, ProductSellingStatus sellingStatus, String name,
		int price) {
		this.productNumber = productNumber;
		this.type = type;
		this.sellingStatus = sellingStatus;
		this.name = name;
		this.price = price;
	}


}
