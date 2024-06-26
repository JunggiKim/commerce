package io.dodn.springboot.core.api.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.dodn.springboot.core.api.domain.product.Product;
import io.dodn.springboot.core.api.domain.product.request.ProductCreateServiceRequest;
import io.dodn.springboot.core.api.domain.product.response.ProductResponse;
import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.storage.db.core.domain.product.ProductEntity;
import io.dodn.springboot.storage.db.core.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;



    @Transactional
    public ProductResponse createProduct(ProductCreateServiceRequest request) {
        String nextProductNumber = createNextProductNumber();

        ProductEntity product =  request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }

    private String createNextProductNumber() {
        String latesProdectNumber = productRepository.findLatesProductNumber();
        if(latesProdectNumber == null){
            return "001";
        }

        int latesProdectNumberInt = Integer.valueOf(latesProdectNumber);
        int nextProductNumberInt = latesProdectNumberInt + 1;
        return String.format("%03d", nextProductNumberInt);  // 상품 번호를 3자리로 포맷팅 하는 부분
    }

    public List<ProductResponse> getSellingProducts() {
        return productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay())
                .stream()
                .map(product -> ProductResponse.of(product))
                .collect(Collectors.toList());
    }


}
