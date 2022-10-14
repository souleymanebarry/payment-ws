package com.upstreampay.payment.utils;

import com.upstreampay.payment.dtos.TransactionDto;
import com.upstreampay.payment.models.Transaction;
import org.springframework.beans.BeanUtils;

public final class MapUtils {

    private MapUtils(){}
    public static TransactionDto transactionToTransactionDTO(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        BeanUtils.copyProperties(transaction, transactionDto);
        return transactionDto;
    }
}
