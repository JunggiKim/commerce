package io.dodn.springboot.core.domain.order.response;

import java.time.LocalDateTime;
import java.util.List;

import io.dodn.springboot.core.domain.product.response.CreateOrderProductResponse;
import io.dodn.springboot.storage.db.core.entity.order.response.OrderRegistrationResponse;
import io.dodn.springboot.storage.db.core.entity.orderproduct.response.OrderProductRegistrationResponse;


public record CreateOrderResponse(
         Long orderId,
         Long totalPrice,
         LocalDateTime registeredDateTime,
         List<CreateOrderProductResponse> products,
         String userEmail
){

   public static CreateOrderResponse of (OrderRegistrationResponse orderResponse, List<OrderProductRegistrationResponse> orderProducts){
       return new CreateOrderResponse(
               orderResponse.orderId(),
               orderResponse.totalPrice(),
               orderResponse.registeredDateTime(),
               orderProducts.stream().map(orderProductDto ->
                       new CreateOrderProductResponse(
                              orderProductDto.productId(),
                              orderProductDto.type(),
                              orderProductDto.name(),
                              orderProductDto.price())).toList(),
               orderResponse.userEmail()
       );
   }
}
