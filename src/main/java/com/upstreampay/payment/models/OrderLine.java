package com.upstreampay.payment.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLine {

    private String id;
    private String productName;
    private int quantity;
    private BigDecimal price;
}
