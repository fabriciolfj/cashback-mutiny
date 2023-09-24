package com.github.cashback.entrypoints.controller.cashbacktotalcontroller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashbackTotalResponse {

    private String customer;
    private BigDecimal total;
}
