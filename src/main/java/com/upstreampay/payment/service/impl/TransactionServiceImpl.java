package com.upstreampay.payment.service.impl;

import com.upstreampay.payment.dtos.TransactionDto;
import com.upstreampay.payment.exceptions.CapturedStatusException;
import com.upstreampay.payment.exceptions.ChangeStatusException;
import com.upstreampay.payment.exceptions.NotFoundException;
import com.upstreampay.payment.models.Transaction;
import com.upstreampay.payment.repositories.TransactionRepository;
import com.upstreampay.payment.service.TransactionService;
import com.upstreampay.payment.utils.MapUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.upstreampay.payment.enums.Status.AUTHORIZED;
import static com.upstreampay.payment.enums.Status.CAPTURED;

@Service
@Slf4j
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Mono<TransactionDto> saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction)
                .map(MapUtils::transactionToTransactionDTO);
    }

    @Override
    public Flux<TransactionDto> findAllTransactions() {
        return transactionRepository.findAll()
                .doOnError(e -> log.error("failed to load Transaction ", e))
                .doOnComplete(() -> log.info("loaded Transactions"))
                .map(MapUtils::transactionToTransactionDTO);
    }

    @Override
    public Mono<TransactionDto> getTransactionById(String id) {
        return findTransactionOrThrowException(id)
                .map(MapUtils::transactionToTransactionDTO);
    }
    @Override
    public Mono<TransactionDto> updateTransaction(String id, TransactionDto transaction) {
        return findTransactionOrThrowException(id)
                .flatMap(t -> {
                    if (CAPTURED.equals(transaction.getStatus())) {
                        if (!AUTHORIZED.equals(t.getStatus())) {
                            return Mono.error(new ChangeStatusException
                                    ("Cannot change state to CAPTURED if state is not AUTHORIZED"));
                        } else {
                            t.setStatus(CAPTURED);
                        }
                    }
                    if (CAPTURED.equals(t.getStatus())) {
                        return Mono.error(new CapturedStatusException
                                ("Cannot change the status of a CAPTURED transaction"));
                    }
                    if (transaction.getAmount() == null && transaction.getType() ==null &&
                            transaction.getStatus() == null) {
                        t.setOrder(transaction.getOrderLines());
                    }//ajouter ou modifier toute la LISTE des commandes d'une transation

                    return transactionRepository.save(t);
                })
                .map(MapUtils::transactionToTransactionDTO);
    }

    private Mono<Transaction> findTransactionOrThrowException(String id) {
        return transactionRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(
                        String.format("Transaction id %s is not found", id))));
    }

}
