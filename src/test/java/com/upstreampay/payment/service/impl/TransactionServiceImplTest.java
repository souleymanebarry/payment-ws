package com.upstreampay.payment.service.impl;

import com.upstreampay.payment.dtos.TransactionDto;
import com.upstreampay.payment.enums.Status;
import com.upstreampay.payment.enums.TypePayment;
import com.upstreampay.payment.models.Transaction;
import com.upstreampay.payment.repositories.TransactionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;


@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;
    @Mock
    private TransactionRepository transactionRepository;

    @Test
    @DisplayName("Should save a new Transaction")
    void shouldSaveATransaction() {
        //given
        Transaction transaction = Transaction.builder()
                .amount(BigDecimal.valueOf(10))
                .type(TypePayment.PAYPAL)
                .status(Status.NEW)
                .build();

        //when
        when(transactionRepository.save(any())).thenReturn(Mono.just(transaction));
        Mono<TransactionDto> result = transactionService.saveTransaction(transaction);

        //then
        StepVerifier.create(result)
                .assertNext(t -> {
                    assertThat(t.getAmount()).isEqualByComparingTo(BigDecimal.valueOf(10));
                    assertThat(t.getStatus()).isEqualTo(Status.NEW);
                    assertThat(t.getType()).isEqualTo(TypePayment.PAYPAL);
                })
                .verifyComplete();
        verify(transactionRepository, times(1)).save(any());
    }


    @Test
    void shouldReturnAllTransactions() {
        //given
        Transaction transactionOne = Transaction.builder()
                .amount(BigDecimal.valueOf(110))
                .type(TypePayment.BANK_CARD)
                .status(Status.NEW)
                .build();

        Transaction transactionTwo = Transaction.builder()
                .amount(BigDecimal.valueOf(10))
                .type(TypePayment.PAYPAL)
                .status(Status.AUTHORIZED)
                .build();
        Transaction transactionTree = Transaction.builder()
                .amount(BigDecimal.valueOf(105))
                .type(TypePayment.GIFT_CARD)
                .status(Status.CAPTURED)
                .build();
        List<Transaction> transactions = asList(transactionOne, transactionTwo, transactionTree);

        //when
        when(transactionRepository.findAll()).thenReturn(Flux.fromIterable(transactions));
        final Flux<TransactionDto> result = transactionService.findAllTransactions();

        //then
        StepVerifier.create(result)
                .expectNextCount(3)
                .verifyComplete();

        verify(transactionRepository, times(1)).findAll();
        verifyNoMoreInteractions(transactionRepository);
    }

    @Test
    void shouldReturnTransactionsWhenExistingTransactionId() {
        //given
        Transaction transaction = Transaction.builder()
                .id("ZZZ")
                .amount(BigDecimal.valueOf(10))
                .type(TypePayment.PAYPAL)
                .status(Status.NEW)
                .build();

        // when
        when(transactionRepository.findById("ZZZ")).thenReturn(Mono.just(transaction));
        final Mono<TransactionDto> result = transactionService.getTransactionById("ZZZ");

        StepVerifier.create(result)
                .assertNext(t -> {
                    assertThat(t.getAmount()).isEqualByComparingTo(BigDecimal.valueOf(10));
                    assertThat(t.getStatus()).isEqualTo(Status.NEW);
                    assertThat(t.getType()).isEqualTo(TypePayment.PAYPAL);
                })
                .verifyComplete();
    }
}
