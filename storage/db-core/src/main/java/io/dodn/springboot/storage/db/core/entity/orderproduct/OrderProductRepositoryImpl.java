package io.dodn.springboot.storage.db.core.entity.orderproduct;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
class OrderProductRepositoryImpl implements OrderProductRepository{

    private final OrderProductJPARepository orderProductJPARepository;




}

