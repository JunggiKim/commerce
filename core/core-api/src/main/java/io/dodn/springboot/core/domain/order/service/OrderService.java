package io.dodn.springboot.core.domain.order.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.dodn.springboot.core.domain.order.request.OrderCreateServiceRequest;
import io.dodn.springboot.core.domain.order.response.OrderResponse;
import io.dodn.springboot.core.domain.orderproduct.OrderProduct;
import io.dodn.springboot.core.domain.product.service.ProductBusiness;
import io.dodn.springboot.storage.db.core.entity.product.response.OrderCreatePersistenceResponse;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OrderService {

	private final ProductBusiness productBusiness;

	private final OrderBusiness orderBusiness;

	private final ApplicationEventPublisher applicationEventPublisher;

	private final OrderConvert convert;

	// 주문이 들어오면 들어온 상품의 재고가 있는지 먼저 확인을 한 후
	// 재고 제거 후 줄인 개수에 맞게 주문상품 을 등록하고 주문을 등록을 해서 반환을 한다.
	// 주문 과 주문 상품을 등록한다.
	// 성공한 주문내역을 반환 한다

	@Transactional
	public OrderResponse createOrder(OrderCreateServiceRequest request, LocalDateTime registeredDateTime) throws
		IllegalAccessException {

		List<OrderProduct> orderProductList = orderBusiness.orderProductRegistration(request);


		return orderBusiness.orderregisattrion(orderProductList);
		;
	}

}