package com.github.cashback.entrypoints.message.converter;

import com.github.cashback.entities.CashbackEntity;
import com.github.cashback.entrypoints.message.dto.CashbackDTO;

import java.math.BigDecimal;

public class CashbackDTOConverter {

    private CashbackDTOConverter() { }

    public static CashbackEntity toEntity(final CashbackDTO dto) {
        return CashbackEntity.builder()
                .customer(dto.getCustomer())
                .transaction(dto.getCode())
                .movementValue(dto.getValue())
                .total(BigDecimal.ZERO)
                .build();
    }
}
