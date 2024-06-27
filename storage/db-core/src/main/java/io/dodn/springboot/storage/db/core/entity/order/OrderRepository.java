package io.dodn.springboot.storage.db.core.entity.order;


import io.dodn.springboot.core.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final OrderJPARepository orderJPARepository;


    public List<OrderEntity> findOrdersBy(LocalDateTime startDateTime, LocalDateTime endDateTime, OrderStatus orderStatus){
     return  orderJPARepository.findOrdersBy(startDateTime,endDateTime,orderStatus);
    }


}
