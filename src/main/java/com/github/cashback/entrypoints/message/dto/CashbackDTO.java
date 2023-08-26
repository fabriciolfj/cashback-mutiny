package com.github.cashback.entrypoints.message.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CashbackDTO {

    private String code;
    private String customer;
    private BigDecimal value;
}
