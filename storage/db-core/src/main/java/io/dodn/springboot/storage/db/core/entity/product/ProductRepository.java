package io.dodn.springboot.storage.db.core.entity.product;


import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.storage.db.core.entity.product.response.AllFiledProductEntityDTO;
import io.dodn.springboot.storage.db.core.entity.product.request.OrderCreatePersistenceRequest;
import io.dodn.springboot.storage.db.core.entity.product.response.OrderCreatePersistenceResponse;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {

    /**
     select *
     from product
     where selling_Status in('SELLING','HOLD')
     **/
     List<ProductEntity> findAllBySellingStatusIn(List<ProductSellingStatus> sellingStatuses);
     List<AllFiledProductEntityDTO> findAllByProductNumberIn(List<Long> productNumbers);
     int findLatesProductNumber();
     List<OrderCreatePersistenceResponse> updateList(List<OrderCreatePersistenceRequest> productDTOList);


}
