package io.dodn.springboot.core.domain.order.service;


import io.dodn.springboot.core.domain.order.Order;
import io.dodn.springboot.storage.db.core.entity.order.request.OrderRegistrationRequest;
import org.springframework.stereotype.Component;

// 도메인을 데이터베이스 엑세스 개체로 변환해주는 역할 domain - > DAO
// 이유는 도메인은 데이터베이스 기술을 JPA든 My Batis든 모르는게 맞기 때문에 변환 계층을 하나 엮어주는 게 맞다고 한다.

@Component
public class OrderConvert {


    public OrderRegistrationRequest toOrderRegistrationRequest(Order order) {
        return new OrderRegistrationRequest(
                order.getOrderStatus(),
                order.calculateTotalPrice(),
                order.getRegisteredDateTime(),
                order.getUserEmail());
    }
}
