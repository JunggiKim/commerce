package io.dodn.springboot.storage.db.core.domain.stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StockRepository extends JpaRepository<StockEntity,Long> {


    List<StockEntity> findAllByProductNumberIn(List<String> productNumbers);


}
