package com.github.cashback.entrypoints.controller.cashbacktotalcontroller.converter;

import com.github.cashback.entities.CashbackEntity;
import com.github.cashback.entrypoints.controller.cashbacktotalcontroller.dto.CashbackTotalResponse;

public class CashbackEntityTotalConverter {

    private CashbackEntityTotalConverter() { }

    public static CashbackEntity toEntity(final String customer) {
        return CashbackEntity.builder()
                .customer(customer)
                .build();
    }

    public static CashbackTotalResponse toResponse(final CashbackEntity entity) {
        return CashbackTotalResponse.builder()
                .total(entity.getTotal())
                .customer(entity.getCustomer())
                .build();
    }

}
