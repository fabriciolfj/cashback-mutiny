package com.github.cashback.providers.database.converter;

import com.github.cashback.entities.CashbackEntity;
import com.github.cashback.providers.database.data.CashbackDatabase;

public class CashbackDatabaseConverter {

    private CashbackDatabaseConverter() { }

    public static CashbackDatabase toDatabase(final CashbackEntity entity) {
        return CashbackDatabase.builder()
                .value(entity.getValue())
                .customer(entity.getCustomer())
                .transaction(entity.getTransaction())
                .valueTransaction(entity.getMovementValue())
                .build();
    }
}
