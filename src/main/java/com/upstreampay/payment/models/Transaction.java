package com.upstreampay.payment.models;


import com.upstreampay.payment.enums.Status;
import com.upstreampay.payment.enums.TypePayment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.math.BigDecimal;
import java.util.List;

import static com.upstreampay.payment.enums.Status.NEW;

@Document(collection = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    private String id;
    private BigDecimal amount;
    private TypePayment type;
    @Default
    private Status status = NEW;

    @DocumentReference
    private List<OrderLine> order;
}
