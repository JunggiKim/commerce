package io.dodn.springboot.storage.db.core.entity.stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StockJPARepository extends JpaRepository<StockEntity,Long> {


    List<StockEntity> findAllByProductNumberIn(List<String> productNumbers);


}
