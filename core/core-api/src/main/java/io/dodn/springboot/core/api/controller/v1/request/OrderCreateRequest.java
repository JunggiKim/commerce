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
        @NotEmpty(message = "주문 상품 목록은 필수입니다.")
        List<Integer> products
) {


    public OrderCreateServiceRequest toServiceRequest() {
        return new OrderCreateServiceRequest(
                this.userEmail,
                this.products
        );

    }
}
