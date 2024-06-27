package io.dodn.springboot.core.domain.stock;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.dodn.springboot.core.domain.product.Product;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.ProductRepository;
import io.dodn.springboot.storage.db.core.entity.stock.StockEntity;
import io.dodn.springboot.storage.db.core.entity.stock.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional(isolation = Isolation.SERIALIZABLE)
@Service
@RequiredArgsConstructor
@Slf4j
public class Stockservice {

  private final StockRepository stockRepository;

  private final ProductRepository productRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  public void deductStockQuantities(List<Product> products) throws IllegalAccessException {
        // 재고 차감 체크가 필요한 상품들 filter
        List<String> stockProductNumbers = extractStockProductNumbers(products);

        // 재고 엔티티 조회
        Map<String, StockEntity> stockEntityMap = createStockMapBy(stockProductNumbers);

        // 상품별 counting
        Map<String, Long> productCountingMap = createCuntingMapBy(stockProductNumbers);

        // 재고 차감 시도
        for (String stockProductNumber : new HashSet<>(stockProductNumbers)) {
            StockEntity stockEntity = stockEntityMap.get(stockProductNumber);
            int quantity = productCountingMap.get(stockProductNumber).intValue();

            if(stockEntity.isQuantityLessThan(quantity)){
                throw new IllegalArgumentException(stockProductNumber + "재고가 부족한 상품이 있습니다.");
            }

            stockEntity.deductQuantity(quantity);
        }
    }


	private static Map<String, Long> createCuntingMapBy(List<String> stockProductNumbers) {
        return stockProductNumbers.stream()
                .collect(Collectors.groupingBy(productNumber -> productNumber, Collectors.counting()));

    }

    private Map<String, StockEntity> createStockMapBy(List<String> stockProductNumbers) {
        List<StockEntity> stocks = stockRepository.findAllByProductNumberIn(stockProductNumbers);

        return stocks.stream()
                .collect(Collectors.toMap(StockEntity::getProductNumber, stock -> stock));
    }

    private static List<String> extractStockProductNumbers(List<Product> products) {
        return products.stream().
                filter(product -> ProductType.containsStockType(product.getType()))
                .map(Product::getProductNumber)
                .collect(Collectors.toList());
    }





}
