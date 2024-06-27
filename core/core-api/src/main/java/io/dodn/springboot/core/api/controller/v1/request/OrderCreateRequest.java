package io.dodn.springboot.core.api.controller.v1.request;

import java.util.List;


import io.dodn.springboot.core.domain.order.request.OrderCreateServiceRequest;
import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public record OrderCreateRequest(
        String userEmail,
        @NotEmpty(message = "주문 상품은 필수입니다.")
        List<OrderCreateRequestProductDTO> orderCreateRequestProductDTOS
) {

        //생각을 해봐야하는건 어차피 프러덕트의 넘버만 보내나 모든 정보를 값객체에담아서보내거나
        // 둘다 디비를 조회해야하는건가? 그런건가 싶으면 다시 그냥 기존 오더의 비즈니스 로직을 따르자
        // 생각을 해보면 괜히 배민과 캐치테이블인 이 커머스 도메인의 비즈니스로직을 본게 아니다
        // 생각을 다시 해보자
    record OrderCreateRequestProductDTO(
            int productNumber,
            int quantity,
            ProductType type,
            ProductSellingStatus status,
            String name,
            int price
    ) {
    }


    public OrderCreateServiceRequest toServiceRequest() {
        return new OrderCreateServiceRequest(
                this.userEmail,
                this.orderCreateRequestProductDTOS
        );

    }
}
