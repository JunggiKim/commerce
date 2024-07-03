package io.dodn.springboot.core.test.v1.persistence.product;

import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TestProductJpaRepository extends JpaRepository<ProductEntity , Long> {
}
