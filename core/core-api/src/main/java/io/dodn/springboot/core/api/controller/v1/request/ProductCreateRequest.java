package io.dodn.springboot.core.api.controller.v1.request;

import io.dodn.springboot.core.domain.product.request.ProductCreateServiceRequest;
import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;


@Getter
public record ProductCreateRequest(
        @NotNull(message = "상품 타입은 필수입니다.")
        ProductType type,

        @NotNull(message = "상품 판매상태는 필수입니다.")
        ProductSellingStatus sellingStatus,
        @NotBlank(message = "상품 이름은 필수입니다.")
        String name,
        @Positive(message = "상품 가격은 양수여야 합니다.")
        int price

) {

    @Builder
    public ProductCreateRequest(ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }

    public ProductCreateServiceRequest toServiceRequest() {
        return ProductCreateServiceRequest.builder()
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }
}
