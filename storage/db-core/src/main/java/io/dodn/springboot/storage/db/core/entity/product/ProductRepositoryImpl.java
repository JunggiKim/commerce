package io.dodn.springboot.storage.db.core.entity.product;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.storage.db.core.entity.product.response.AllFiledProductEntityDTO;
import io.dodn.springboot.storage.db.core.entity.product.request.OrderCreatePersistenceRequest;
import io.dodn.springboot.storage.db.core.entity.product.response.OrderCreatePersistenceResponse;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
class ProductRepositoryImpl implements ProductRepository {

	private final ProductJPARepository productJPARepository;
	private final ProductRepository productRepository;

	/**
	 select *
	 from product
	 where selling_Status in('SELLING','HOLD')
	 **/
	@Override
	public List<ProductEntity> findAllBySellingStatusIn(List<ProductSellingStatus> sellingStatuses) {

		return productJPARepository.findAllBySellingStatusIn(sellingStatuses);
	}

	@Override
	public List<AllFiledProductEntityDTO> findAllByProductNumberIn(List<Long> productNumbers) {
		return productJPARepository.findAllByProductNumberIn(productNumbers)
			.stream().map(AllFiledProductEntityDTO::of).toList();

	}

	@Override
	public int findLatesProductNumber() {
		return productJPARepository.findLatesProductNumber();
	}

	@Override
	public List<OrderCreatePersistenceResponse> updateList(List<OrderCreatePersistenceRequest> productDTOList) {
		List<ProductEntity> entityList = productDTOList.stream()
			.map(OrderCreatePersistenceRequest::toEntity).toList();

		return productJPARepository.saveAll(entityList).stream().map(OrderCreatePersistenceResponse::of).toList();

	}

}
