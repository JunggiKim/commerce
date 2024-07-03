package io.dodn.springboot.core.test.v1.persistence.product;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public TestProductRepository testProductRepository(TestProductJpaRepository testProductJpaRepository){
        return new TestProductRepository(testProductJpaRepository);
    }

//    @Bean
//    public TestProductJpaRepository testProductRepository(){
//        return new Test
//    }

}
