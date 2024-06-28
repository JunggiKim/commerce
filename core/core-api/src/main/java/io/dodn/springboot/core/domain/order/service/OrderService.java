package io.dodn.springboot.core.domain.order.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.dodn.springboot.core.domain.order.request.OrderCreateServiceRequest;
import io.dodn.springboot.core.domain.product.service.ProductBusiness;
import io.dodn.springboot.core.domain.stock.StockBusiness;
import io.dodn.springboot.core.domain.stock.StockBusinessImpl;
import io.dodn.springboot.core.domain.stock.Stockservice;
import io.dodn.springboot.storage.db.core.entity.order.OrderRepository;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;
import io.dodn.springboot.storage.db.core.entity.product.ProductRepository;
import io.dodn.springboot.storage.db.core.entity.stock.StockRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.dodn.springboot.core.domain.order.Order;
import io.dodn.springboot.core.domain.order.response.OrderResponse;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OrderService {

    private final StockBusiness stockBusiness;




    private final ApplicationEventPublisher applicationEventPublisher;

    private final OrderConvert convert;


    @Transactional
    public OrderResponse createOrder(OrderCreateServiceRequest request, LocalDateTime registeredDateTime) throws IllegalAccessException {

        stockBusiness.deductStockQuantities(request);

        return null;
    }




}
