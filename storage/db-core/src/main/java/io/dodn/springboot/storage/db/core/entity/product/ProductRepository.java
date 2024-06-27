package io.dodn.springboot.storage.db.core.entity.product;


import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.storage.db.core.entity.stock.StockJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final ProductJPARepository productJPARepository;


    /**
     select *
     from product
     where selling_Status in('SELLING','HOLD')
     **/
    public List<ProductEntity> findAllBySellingStatusIn(List<ProductSellingStatus> sellingStatuses){
        return productJPARepository.findAllBySellingStatusIn(sellingStatuses);
    }

    public List<ProductEntity> findAllByProductNumberIn(List<String> productNumbers){
        return productJPARepository.findAllByProductNumberIn(productNumbers);
    }

    public int findLatesProductNumber(){
        return productJPARepository.findLatesProductNumber();
    }




}
