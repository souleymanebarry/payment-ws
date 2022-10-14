package com.upstreampay.payment.controller;


import com.upstreampay.payment.dtos.TransactionDto;
import com.upstreampay.payment.models.Transaction;
import com.upstreampay.payment.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("api/v1/transactions")
@RestController
@AllArgsConstructor
public class TransactionControllerI {
  private final TransactionService transactionService;

  @PostMapping
  public Mono<TransactionDto> saveTransaction(@RequestBody Transaction transaction) {
    return transactionService.saveTransaction(transaction);
  }

  @GetMapping("/{id}")
  public Mono<TransactionDto> getTransactionById(@PathVariable(name = "id") String id) {
    return transactionService.getTransactionById(id);
  }

  @GetMapping
  public Flux<TransactionDto> getAllTransactions() {
    return transactionService.findAllTransactions();
  }

  @PutMapping("/{id}")
  public Mono<TransactionDto> updateTransaction(@PathVariable(name = "id") String id, @RequestBody TransactionDto transaction) {
    return transactionService.updateTransaction(id, transaction);
  }
}
