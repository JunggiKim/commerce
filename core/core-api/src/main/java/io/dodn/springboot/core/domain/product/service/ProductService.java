package io.dodn.springboot.core.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;

import io.dodn.springboot.core.domain.product.response.CreateOrderProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.dodn.springboot.core.domain.product.request.ProductCreateServiceRequest;
import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;
import io.dodn.springboot.storage.db.core.entity.product.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;



    @Transactional
    public CreateOrderProductResponse createProduct(ProductCreateServiceRequest request) {
        int nextProductNumber = createNextProductNumber();

        ProductEntity savedProduct = productRepository.productRegistration(request.toEntity(nextProductNumber));

        return CreateOrderProductResponse.of(savedProduct);
    }

    private int createNextProductNumber() {
        int latesProductNumber = productRepository.findLatesProductNumber();
        return latesProductNumber + 1;
    }

    public List<CreateOrderProductResponse> getSellingProducts() {
        return productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay())
                .stream()
                .map(CreateOrderProductResponse::of)
                .collect(Collectors.toList());
    }


}
