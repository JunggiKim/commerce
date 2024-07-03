package io.dodn.springboot.core.test.v1.persistence.product;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.core.test.example.ContextTest;
import io.dodn.springboot.core.test.example.DatabaseClean;
import io.dodn.springboot.storage.db.core.entity.product.ProductRepository;
import io.dodn.springboot.storage.db.core.entity.product.response.SellingPossibleStatusPersistenceResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static io.dodn.springboot.core.enums.ProductType.ProductSellingStatus.*;
import static io.dodn.springboot.core.enums.ProductType.ProductType.CLOTHES;
import static io.dodn.springboot.core.enums.ProductType.ProductType.FOOD;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.tuple;

@ActiveProfiles("local")
@SpringBootTest(classes = {TestProductJpaRepository.class})
class ProductRepositoryTest extends ContextTest{


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestProductRepository testProductRepository;

    @Autowired
    private TestProductJpaRepository testProductJpaRepository;


    @Autowired
    private DatabaseClean databaseClean;

    @AfterEach
    void tearDown() {
        databaseClean.all();
    }


    @DisplayName("상품 타입이 의류인 상품들만 조회한다")
    @Test
    void productTypeisFood () {
        //  given
        TestProductDto product1 = TestProductDto.builder()
                .sellingStatus(SELLING)
                .productNumber(1L)
                .build();

        TestProductDto product2 = TestProductDto.builder()
                .sellingStatus(HOLD)
                .productNumber(2L)
                .build();

        TestProductDto product3 = TestProductDto.builder()
                .sellingStatus(STOP_SELLING)
                .productNumber(3L)
                .build();

        testProductRepository.saveAll(List.of(product1, product2, product3));

        //  when
        List<SellingPossibleStatusPersistenceResponse> products = productRepository.findAllBySellingStatusIn(List.of(SELLING, HOLD));

        //  then

        assertThat(products).hasSize(2)
                .extracting("productType", "productNumber")
                .containsExactlyInAnyOrder(
                        tuple(CLOTHES, 1L),
                        tuple(CLOTHES, 2L)
                );
    }

//
//
//     @DisplayName("상품번호 리스트로 상품들을 조회한다.")
//     @Test()
//     void findAllByProductNumberIn(){
// //  given
//         Product product1 = createProduct(CLOTHES, "001", 4000, SELLING, "아메리카노");
//         Product product2 = createProduct(CLOTHES, "002", 4500, HOLD, "카페라떼");
//         Product product3 = createProduct(CLOTHES, "003", 7000, STOP_SELLING, "팥빙수");
//         productRepository.save(List.of(product1,product2,product3));
//
//
//         //  when
//
//         List<Product> products = productRepository.findAllByProductNumberIn(List.of("001","002"));
//         //  then
//
//
//
//         assertThat(products).hasSize(2)
//                 .extracting("productNumber","name","sellingStatus")
//                 .containsExactlyInAnyOrder(
//                         tuple("001","아메리카노",SELLING),
//                         tuple("002","카페라떼",HOLD)
//                 );
//     }
//
//     @DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어온다")
//     @Test()
//     void findLatesProductNumber(){
//         //  given
//
//         String targetProductNumber = "003";
//         Product product1 = createProduct(CLOTHES, "001", 4000, SELLING, "아메리카노");
//         Product product2 = createProduct(CLOTHES, "002", 4500, HOLD, "카페라떼");
//         Product product3 = createProduct(CLOTHES, targetProductNumber, 7000, STOP_SELLING, "팥빙수");
//         productRepository.save(List.of(product1,product2,product3));
//         //  when
//
//         String lastProductNumber = productRepository.findLatesProductNumber() ;
//
//         //  then
//          assertThat(lastProductNumber).isEqualTo(targetProductNumber);
//     }
//
//
//     @DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어올 때, 상품히 하나도 없는 경우에는 null을 반환한다.")
//     @Test()
//     void findLatesProductNumberWhenProductIsEmpty(){
//         // when
//
//         // String lastProductNumber = productRepository.findLatesProductNumber() ;
//         //  then
//
//         // assertThat(lastProductNumber).isEqualTo(null);
//     }

    private TestProductDto createProductDto(ProductType type, Long productNumber, BigDecimal price, ProductSellingStatus productSellingStatus, String name, Long stockQuantity) {
        return new TestProductDto(
                productNumber,
                type,
                productSellingStatus,
                name,
                price,
                stockQuantity
        );

    }

}