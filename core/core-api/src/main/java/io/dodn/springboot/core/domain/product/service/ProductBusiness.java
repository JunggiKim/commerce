package io.dodn.springboot.core.domain.product.service;


import io.dodn.springboot.core.api.support.Business;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;
import io.dodn.springboot.storage.db.core.entity.product.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Business
@RequiredArgsConstructor
public class ProductBusiness {

    private final ProductRepository productRepository;



    public List<ProductEntity> findProductsBy(List<Integer> productNumbers) {
        return productRepository.findAllByProductNumberIn(productNumbers);
    }



}
