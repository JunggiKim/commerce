package io.dodn.springboot.core.api.domain.order.request;

import java.util.List;


import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    @NotEmpty(message ="상품 번호 리스트는 필수입니다.")
    private List<String> productNumbers;

    private String userEmail;

    @Builder
    private OrderCreateRequest(List<String> productNumbers,String userEmail) {
        this.productNumbers = productNumbers;
        this.userEmail = userEmail;
    }

    public OrderCreateServiceRequest toServiceRequest() {
        return OrderCreateServiceRequest.builder()
                .productNumbers(productNumbers)
                .userEmail(userEmail)
                .build();
    }
}
