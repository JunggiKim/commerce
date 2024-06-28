package io.dodn.springboot.core.domain.product;



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


	private int productNumber;

	private ProductType type;

	private ProductSellingStatus sellingStatus;

	private String name;

	private int price;

	private Stock stock;

	@Builder
	private Product(int productNumber, ProductType type, ProductSellingStatus sellingStatus, String name,
		int price , Stock stock) {
		this.productNumber = productNumber;
		this.type = type;
		this.sellingStatus = sellingStatus;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}


}
