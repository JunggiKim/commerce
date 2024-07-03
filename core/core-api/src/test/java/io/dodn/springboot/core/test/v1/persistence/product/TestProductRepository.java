package io.dodn.springboot.core.test.v1.persistence.product;

import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@ComponentScan
class TestProductRepository {

    private final TestProductJpaRepository testProductJpaRepository;

    TestProductRepository(TestProductJpaRepository testProductJpaRepository) {
        this.testProductJpaRepository = testProductJpaRepository;
    }

    public List<TestProductDto> saveAll(List<TestProductDto> productDtoList) {
        List<ProductEntity> entityList = productDtoList.stream().map(TestProductDto::toEntity).toList();
        return testProductJpaRepository.saveAll(entityList).stream().map(TestProductDto::of).toList();
    }

    public TestProductDto save(TestProductDto productDto) {
        return TestProductDto.of(testProductJpaRepository.save(productDto.toEntity()));
    }
}
