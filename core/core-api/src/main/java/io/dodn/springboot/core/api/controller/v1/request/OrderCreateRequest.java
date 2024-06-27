package io.dodn.springboot.core.api.controller.v1.request;

import java.util.List;


import io.dodn.springboot.core.domain.order.request.OrderCreateServiceRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    @NotEmpty(message ="주문 상품은 필수입니다.")

    private String userEmail;

    private List<OrderCreateRequestProductDTO> orderCreateRequestProductDTOS;

    record OrderCreateRequestProductDTO  (
            String productNumber,
            int quantity
    ){
    }




    @Builder
    private OrderCreateRequest(List<OrderCreateRequestProductDTO> orderCreateRequestProductDTOS,String userEmail) {
        this.orderCreateRequestProductDTOS = orderCreateRequestProductDTOS;
        this.userEmail = userEmail;
    }

    public OrderCreateServiceRequest toServiceRequest() {
        return OrderCreateServiceRequest.builder()
                .orderCreateServiceRequestProductDTOS(orderCreateRequestProductDTOS)
                .userEmail(userEmail)
                .build();
    }
}
