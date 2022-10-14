package com.upstreampay.payment.dtos;

import com.upstreampay.payment.enums.Status;
import com.upstreampay.payment.enums.TypePayment;
import com.upstreampay.payment.models.OrderLine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
  private String id;
  private BigDecimal amount;
  private TypePayment type;
  private Status status;

  private List<OrderLine> orderLines;
}
