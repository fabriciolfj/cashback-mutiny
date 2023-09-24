package com.github.cashback.providers.database.converter;

import com.github.cashback.entities.CashbackEntity;
import com.github.cashback.providers.database.data.CashbackDatabase;

import java.time.LocalDateTime;

public class CashbackDatabaseConverter {

    private CashbackDatabaseConverter() { }

    public static CashbackEntity toEntity(final CashbackDatabase data) {
        return CashbackEntity
                .builder()
                .total(data.getTotal())
                .movementValue(data.getValueTransaction())
                .customer(data.getCustomer())
                .transaction(data.getTransaction())
                .value(data.getValue())
                .build();
    }

    public static CashbackDatabase toDatabase(final CashbackEntity entity) {
        return CashbackDatabase.builder()
                .value(entity.getValue())
                .customer(entity.getCustomer())
                .transaction(entity.getTransaction())
                .valueTransaction(entity.getMovementValue())
                .createDate(LocalDateTime.now())
                .total(entity.getTotal())
                .build();
    }
}
