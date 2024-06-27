package io.dodn.springboot.core.api.controller.v1.api.service.order;

import io.dodn.springboot.core.api.controller.v1.request.OrderCreateRequest;
import io.dodn.springboot.core.domain.order.service.OrderService;
import io.dodn.springboot.core.domain.product.Product;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.order.OrderRepository;
import io.dodn.springboot.storage.db.core.entity.orderproduct.OrderProductRepository;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;
import io.dodn.springboot.storage.db.core.entity.product.ProductRepository;
import io.dodn.springboot.storage.db.core.entity.stock.StockEntity;
import io.dodn.springboot.storage.db.core.entity.stock.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static io.dodn.springboot.core.enums.ProductType.ProductSellingStatus.STOP_SELLING;
import static io.dodn.springboot.core.enums.ProductType.ProductType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockRepository stockRepository;


    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderService orderService;

    @AfterEach
    void tearDown() {
        orderProductRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        stockRepository.deleteAllInBatch();
    }

    @DisplayName("주문번호 리스트를 받아 주문을 생성한다.")
    @Test
    void createOrder() throws IllegalAccessException {
        //  given
        LocalDateTime registeredDateTime = LocalDateTime.now();
        ProductEntity product1 = createProductEntity(CLOTHES, 1, 1000);
        ProductEntity product2 = createProductEntity(CLOTHES, 2, 3000);
        ProductEntity product3 = createProductEntity(CLOTHES, 3, 5000);

        productRepository.saveAll(List.of(product1, product2, product3));


        OrderCreateRequest request = OrderCreateRequest.builder()
                .productNumbers(List.of("001", "002"))
                .build();
        //  when
        OrderResponse orderResponse = orderService.createOrder(request.toServiceRequest(), registeredDateTime);

        //  then


        assertThat(orderResponse.getId()).isNotNull();

        assertThat(orderResponse)
                .extracting("registeredDateTime", "totalPrice")
                .contains(registeredDateTime, 4000);

        assertThat(orderResponse.getProducts()).hasSize(2)
                .extracting("productNumber", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", 1000),
                        tuple("002", 3000)
                );


    }

    @DisplayName("중복되는 상품번호 리스트로 주문을 생성할 수 있다.")
    @Test
    void createOrderWithDuplicateProductNumber() throws IllegalAccessException {
        //  given
        LocalDateTime registeredDateTime = LocalDateTime.now();


        Product product1 = createProductEntity(CLOTHES, "001", 1000);

        Product product2 = createProductEntity(CLOTHES, "002", 3000);

        Product product3 = createProductEntity(CLOTHES, "003", 5000);

        productRepository.saveAll(List.of(product1, product2, product3));


        OrderCreateRequest request = OrderCreateRequest.builder()
                .productNumbers(List.of("001", "001"))
                .build();
        //  when
        OrderResponse orderResponse = orderService.createOrder(request.toServiceRequest(), registeredDateTime);

        //  then


        assertThat(orderResponse.getId()).isNotNull();

        assertThat(orderResponse)
                .extracting("registeredDateTime", "totalPrice")
                .contains(registeredDateTime, 2000);

        assertThat(orderResponse.getProducts()).hasSize(2)
                .extracting("productNumber", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", 1000),
                        tuple("001", 1000)
                );

    }


    @DisplayName("재고와 관련된 상품이 포함되어 있는 주문번호 리스트를 받아 주문을 생성한다.")
    @Test
    void createOrderWithStock() throws IllegalAccessException {
        //  given
        LocalDateTime registeredDateTime = LocalDateTime.now();


        ProductEntity product1 = createProductEntity(FOOD, 1, 1000);
        ProductEntity product2 = createProductEntity(ELECTRONIC_PRODUCTS, 2, 3000);
        ProductEntity product3 = createProductEntity(CLOTHES, 3, 5000);
        productRepository.saveAll(List.of(product1, product2, product3));

        StockEntity stock =  StockEntity.create("001", 2);
        StockEntity stock2 = StockEntity.create("002", 2);
        stockRepository.saveAll(List.of(stock, stock2));


        OrderCreateRequest request = OrderCreateRequest.builder()
                .productNumbers(List.of("001", "001", "002", "003"))
                .build();
        //  when
        OrderResponse orderResponse = orderService.createOrder(request.toServiceRequest(), registeredDateTime);

        //  then


        assertThat(orderResponse.getId()).isNotNull();

        assertThat(orderResponse)
                .extracting("registeredDateTime", "totalPrice")
                .contains(registeredDateTime, 10000);

        assertThat(orderResponse.getProducts()).hasSize(4)
                .extracting("productNumber", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", 1000),
                        tuple("001", 1000),
                        tuple("002", 3000),
                        tuple("003", 5000)
                );
        List<Stock> stocks = stockRepository.findAll();


        assertThat(stocks).hasSize(2)
                .extracting("productNumber","quantity")
                .containsExactlyInAnyOrder(
                        tuple("001",0),
                        tuple("002",1)
                );


    }





    private ProductEntity createProductEntity(ProductType type, int productNumber, int price) {
        return ProductEntity.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(STOP_SELLING)
                .name("메뉴이름")
                .price(price)
                .build();
    }


}