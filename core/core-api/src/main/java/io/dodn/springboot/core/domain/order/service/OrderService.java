package io.dodn.springboot.core.domain.order.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.dodn.springboot.core.domain.order.request.OrderCreateServiceRequest;
import io.dodn.springboot.core.domain.product.Product;
import io.dodn.springboot.core.domain.stock.Stockservice;
import io.dodn.springboot.storage.db.core.entity.order.OrderEntity;
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
@Transactional (readOnly = true)
@Service
@RequiredArgsConstructor
public class OrderService {

     private final ProductRepository productRepository;

     private final OrderRepository orderRepository;

     private final StockRepository stockRepository;

     private final Stockservice stockservice;

    private final ApplicationEventPublisher applicationEventPublisher;
    /*
*/
    @Transactional
    public OrderResponse createOrder(OrderCreateServiceRequest request, LocalDateTime registeredDateTime) throws IllegalAccessException {
        Order.create(request,registeredDateTime);


        return  null;
    }

    private List<ProductEntity> findProductsBy(List<String> productNumbers) {
        List<ProductEntity> products = productRepository.findAllByProductNumberIn(productNumbers);


//        Map<String, ProductEntity> productMap =

        Map<Integer, ProductEntity> productMap = products.stream()
                .collect(Collectors.toMap(ProductEntity::getProductNumber, product -> product));

        return productNumbers.stream()
                .map(productMap::get)
                .collect(Collectors.toList());
    }


        //list<stirng> = {a, a,b, c }
       //  map<a,2> map<b,1> map<c,1>




}
