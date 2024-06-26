package io.dodn.springboot.storage.db.core.entity.product;


import java.math.BigDecimal;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private ProductType type;

	@Enumerated(EnumType.STRING)
	private ProductSellingStatus sellingStatus;

	private String name;

	private Long price;

	private Long quantity;

	@Builder
	private ProductEntity(Long id, ProductType type, ProductSellingStatus sellingStatus, String name,
		Long price , Long quantity) {
		this.id = id;
		this.type = type;
		this.sellingStatus = sellingStatus;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}


}
