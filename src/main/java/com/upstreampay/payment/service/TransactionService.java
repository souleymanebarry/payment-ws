package com.upstreampay.payment.service;

import com.upstreampay.payment.dtos.TransactionDto;
import com.upstreampay.payment.models.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Mono<TransactionDto> saveTransaction(Transaction transaction);

    Flux<TransactionDto> findAllTransactions();

    Mono<TransactionDto> getTransactionById(String id);

    Mono<TransactionDto> updateTransaction(String id, TransactionDto transaction);

}
