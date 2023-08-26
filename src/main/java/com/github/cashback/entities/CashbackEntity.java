package com.github.cashback.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashbackEntity {

    private String transaction;
    private String customer;
    private BigDecimal movementValue;
    private BigDecimal value;

    public CashbackEntity calcule(final BigDecimal percentage) {
        this.value = movementValue.multiply(percentage);
        return this;
    }
}
