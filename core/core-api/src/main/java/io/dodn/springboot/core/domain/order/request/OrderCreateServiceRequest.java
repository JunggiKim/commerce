package io.dodn.springboot.core.domain.order.request;

import java.util.List;

import io.dodn.springboot.core.api.controller.v1.request.OrderCreateRequest;
import io.dodn.springboot.core.domain.order.Order;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateServiceRequest {

    private String userEmail;

    @NotEmpty(message = "주문 상품은 필수입니다.")
    private List<OrderCreateServiceRequestProductDTO> orderCreateServiceRequestProductDTOS;

    record OrderCreateServiceRequestProductDTO  (
            String productNumber,
            int quantity
    ){
    }




    @Builder
    private OrderCreateServiceRequest(List<OrderCreateServiceRequestProductDTO> orderCreateServiceRequestProductDTOS,String userEmail) {
        this.orderCreateServiceRequestProductDTOS = orderCreateServiceRequestProductDTOS;
        this.userEmail = userEmail;
    }

    public Order toOrder(){
         Order.builder()

                 .
        return
    }



}
