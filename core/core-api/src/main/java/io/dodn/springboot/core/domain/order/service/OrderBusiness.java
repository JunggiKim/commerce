package io.dodn.springboot.core.domain.order.service;


import java.util.List;

import io.dodn.springboot.core.api.support.Business;
import io.dodn.springboot.core.domain.order.OrderRepository;
import io.dodn.springboot.core.domain.order.request.OrderCreateServiceRequest;
import io.dodn.springboot.core.domain.orderproduct.OrderProduct;
import io.dodn.springboot.core.domain.orderproduct.service.OrderProductConvert;
import io.dodn.springboot.core.domain.product.service.ProductBusiness;
import io.dodn.springboot.storage.db.core.entity.product.ProductRepository;
import io.dodn.springboot.storage.db.core.entity.product.response.OrderCreatePersistenceResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Business
@RequiredArgsConstructor
public class OrderBusiness {

	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final ProductBusiness productBusiness;
	private OrderProductConvert productConvert;







	public List<OrderProduct> orderProductRegistration(OrderCreateServiceRequest request) throws
		IllegalAccessException {


		List<OrderCreatePersistenceResponse> orderCreateDtoList =
			productBusiness.productDeductQuantities(request.productDTOS());


		List<OrderProduct> OrderProductList = orderCreateDtoList.stream().map(dto -> productConvert.toDomain(dto)).toList();

		return null;
	}




}
