package io.dodn.springboot.storage.db.core.entity.stock;


import io.dodn.springboot.storage.db.core.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class StockEntity extends BaseEntity {

    private Integer productNumber;

    private int quantity;



    @Builder
    private StockEntity( int productNumber, int quantity) {
        this.productNumber = productNumber;
        this.quantity = quantity;
    }

    public static StockEntity create(int productNumber,int quantity){
      return StockEntity.builder()
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
