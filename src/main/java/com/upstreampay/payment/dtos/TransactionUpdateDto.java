package com.upstreampay.payment.dtos;

import com.upstreampay.payment.enums.Status;
import com.upstreampay.payment.enums.TypePayment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionUpdateDto {

  private BigDecimal amount;
  private TypePayment type;
  private Status status;
}
