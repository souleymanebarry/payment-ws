package com.upstreampay.payment.repositories;

import com.upstreampay.payment.models.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {
}
