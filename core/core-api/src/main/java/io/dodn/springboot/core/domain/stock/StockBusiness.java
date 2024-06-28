package io.dodn.springboot.core.domain.stock;


import io.dodn.springboot.core.api.support.Business;
import io.dodn.springboot.core.domain.order.request.OrderCreateServiceRequest;
import io.dodn.springboot.core.domain.product.Product;
import io.dodn.springboot.core.domain.product.service.ProductBusiness;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;
import io.dodn.springboot.storage.db.core.entity.product.ProductRepository;
import io.dodn.springboot.storage.db.core.entity.stock.StockEntity;
import io.dodn.springboot.storage.db.core.entity.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional(isolation = Isolation.SERIALIZABLE)
@RequiredArgsConstructor
@Slf4j
@Business
public class StockBusiness {

    private final StockRepository stockRepository;

    private final ProductBusiness productBusiness;


    //    List<Product> products
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deductStockQuantities(OrderCreateServiceRequest request) throws IllegalAccessException {

        List<ProductEntity> products = productBusiness.findProductsBy(request.productNumbers());

        List<Integer> stockProductNumbers = products.stream()
                .map(ProductEntity::getProductNumber).toList();

        // 재고 엔티티 조회
        Map<Integer, StockEntity> stockEntityMap = createStockMapBy(stockProductNumbers);

        // 상품별 counting
        Map<Integer, Long> productCountingMap = createCuntingMapBy(stockProductNumbers);

        // 재고 차감 시도
        for (Integer stockProductNumber : new HashSet<>(stockProductNumbers)) {
            StockEntity stockEntity = stockEntityMap.get(stockProductNumber);
            int quantity = productCountingMap.get(stockProductNumber).intValue();

            if (stockEntity.isQuantityLessThan(quantity)) {
                throw new IllegalArgumentException(stockProductNumber + "재고가 부족한 상품이 있습니다.");
            }

            stockEntity.deductQuantity(quantity);
        }
    }


    private static Map<Integer, Long> createCuntingMapBy(List<Integer> stockProductNumbers) {
        return stockProductNumbers.stream()
                .collect(Collectors.groupingBy(productNumber -> productNumber, Collectors.counting()));

    }

    private Map<Integer, StockEntity> createStockMapBy(List<Integer> stockProductNumbers) {
        return stockRepository.findAllByProductNumberIn(stockProductNumbers).stream()
                .collect(Collectors.toMap(StockEntity::getProductNumber, stock -> stock));
    }

//        return stocks.stream()
//                .collect(Collectors.toMap(StockEntity::getProductNumber, stock -> stock));
//    }

    private static List<Integer> extractStockProductNumbers(List<Product> products) {
        return products.stream().
                filter(product -> ProductType.containsStockType(product.getType()))
                .map(Product::getProductNumber)
                .collect(Collectors.toList());
    }

}
