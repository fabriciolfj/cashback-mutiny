package com.github.cashback.usecase.cashbacktotalcustomer;

import com.github.cashback.entities.CashbackEntity;
import io.smallrye.mutiny.Uni;

public interface CashbackProviderFindTotalPerCustomer {

    Uni<CashbackEntity> process(final CashbackEntity entity);
}
