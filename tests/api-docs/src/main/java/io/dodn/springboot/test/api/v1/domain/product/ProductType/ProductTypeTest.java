package io.dodn.springboot.test.api.v1.domain.product.ProductType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.dodn.springboot.core.enums.ProductType.ProductType;

@SpringBootTest
class ProductTypeTest {


    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    void containsStockType(){
        //  given
        ProductType giventype = ProductType.CLOTHES;

        //  when
        boolean result = ProductType.containsStockType(giventype);

        //  then
        assertThat(result).isFalse();
        assertThat(result).isFalse();
    }
    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    void containsStockType2(){
        //  given
        ProductType giventype = ProductType.ELECTRONIC_PRODUCTS;

        //  when
        boolean result = ProductType.containsStockType(giventype);

        //  then
        assertThat(result).isTrue();
    }


}