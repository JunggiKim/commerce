package io.dodn.springboot.storage.db.core.entity.stock;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class StockRepository {

    private final StockJPARepository stockJPARepository;

    public List<StockEntity> findAllByProductNumberIn(List<String> productNumbers){
        return stockJPARepository.findAllByProductNumberIn(productNumbers);
    }

}
