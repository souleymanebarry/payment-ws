package com.upstreampay.payment.repositories;

import com.upstreampay.payment.models.OrderLine;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OrderLineRepository extends ReactiveMongoRepository<OrderLine, String> {
}
