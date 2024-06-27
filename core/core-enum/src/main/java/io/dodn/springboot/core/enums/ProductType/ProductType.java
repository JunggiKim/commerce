package io.dodn.springboot.core.enums.ProductType;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProductType {

    CLOTHES("의류"),

    FOOD("음식"),

    ELECTRONIC_PRODUCTS("전자제품");


    private final String text;

    public static boolean containsStockType(ProductType type) {
        return List.of(FOOD, ELECTRONIC_PRODUCTS).contains(type);
    }
}
