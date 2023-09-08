package com.github.cashback.providers.database.data;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cashback")
public class CashbackDatabase extends PanacheEntity {

    private String customer;
    @Column(name = "transaction_code")
    private String transaction;
    private BigDecimal value;
    @Column(name = "value_transaction")
    private BigDecimal valueTransaction;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    private BigDecimal total;
}
