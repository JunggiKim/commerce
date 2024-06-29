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

	// 재고 감소후 재고 감소 가 성공적으로 되었다면
	// 주문 과 주문 상품을 등록한다.
	// 성공한 주문내역을 반환 한다

	@Transactional
	public OrderResponse createOrder(OrderCreateServiceRequest request, LocalDateTime registeredDateTime) throws
		IllegalAccessException {

		List<OrderCreatePersistenceResponse> orderCreateDtoList =
			productBusiness.productDeductQuantities(request.productDTOS());

		List<OrderProduct> orderProductList = productBusiness.orderProductregisattrion(orderCreateDtoList);

		// OrderCreateResponse orderCreateResponse

		return orderBusiness.orderregisattrion(orderProductList);
		;
	}

}
