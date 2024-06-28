package io.dodn.springboot.storage.db.core.entity.product;


import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {


     List<ProductEntity> findAllBySellingStatusIn(List<ProductSellingStatus> sellingStatuses);

     List<ProductEntity> findAllByProductNumberIn(List<Integer> productNumbers);

     int findLatesProductNumber();


}
