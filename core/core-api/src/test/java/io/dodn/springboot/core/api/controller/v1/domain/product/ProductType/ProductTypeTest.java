package io.dodn.springboot.core.api.controller.v1.domain.product.ProductType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


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
        ProductType giventype = ProductType.ELECTRONICPRODUCTS;

        //  when
        boolean result = ProductType.containsStockType(giventype);

        //  then
        assertThat(result).isTrue();
    }


}