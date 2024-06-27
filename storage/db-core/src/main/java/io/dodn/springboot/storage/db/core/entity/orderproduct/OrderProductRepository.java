package io.dodn.springboot.storage.db.core.entity.orderproduct;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderProductRepository {

    private final OrderProductJPARepository orderProductJPARepository;




}
