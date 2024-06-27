package io.dodn.springboot.core.domain.stock;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Stock {


    private String productNumber;

    private int quantity;



    @Builder
    private Stock( String productNumber, int quantity) {
        this.productNumber = productNumber;
        this.quantity = quantity;
    }

    public static Stock create(String productNumber,int quantity){
      return Stock.builder()
              .productNumber(productNumber)
              .quantity(quantity)
              .build();
}


    public boolean isQuantityLessThan(int quantity) {
        return this.quantity < quantity;

    }


    public void deductQuantity(int quantity) throws IllegalAccessException {
            if(isQuantityLessThan(quantity)){
                throw new IllegalArgumentException("차감할 재고 수량이 없습니다.");
            }
        this.quantity -= quantity;
    }
}
