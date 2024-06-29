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



}
