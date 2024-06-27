package io.dodn.springboot.core.domain.order.request;

import java.time.LocalDateTime;
import java.util.List;

import io.dodn.springboot.core.api.controller.v1.request.OrderCreateRequest;
import io.dodn.springboot.core.domain.order.Order;
import io.dodn.springboot.core.domain.product.Product;
import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
public record OrderCreateServiceRequest(
        String userEmail,

        @NotEmpty(message = "주문 상품은 필수입니다.")
        List<OrderCreateServiceRequestProductDTO> orderCreateServiceRequestProductDTOS

) {


    record OrderCreateServiceRequestProductDTO(
            int productNumber,
            int quantity,
            ProductType type,
            ProductSellingStatus status,
            String name,
            int price
    ) {
    }

//    public static OrderCreateServiceRequest of (){
//
//    }


    //  order에서 주문생성 로직과 DTO 변환 고민 중...
    //  애초에 상품에 맞게 데이터를 api에서 받아야 한다.
    //
//    public List<Product> toProductList(LocalDateTime registeredDateTime) {
//
//        this.orderCreateServiceRequestProductDTOS.stream()
//                .map(dto -> {
//                    Product.builder()
//                            .productNumber(dto.productNumber)
//                            .type(Pro)
//                })
//
//
//        return Product.builder()
//                .productNumber()
//
//                .build();
//    }
//
//    public Product toProduct2(LocalDateTime registeredDateTime) {
//
//
//        return Product.builder()
//                .productNumber(this.orderCreateServiceRequestProductDTOS.)
//
//                .build();
//    }
//
//
//
//    public Order toOrder() {
//        return Order.builder()
//                .
//    }


}
