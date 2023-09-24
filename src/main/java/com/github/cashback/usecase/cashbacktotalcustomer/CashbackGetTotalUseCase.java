package com.github.cashback.usecase.cashbacktotalcustomer;

import com.github.cashback.entities.CashbackEntity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class CashbackGetTotalUseCase {

    private final CashbackProviderFindTotalPerCustomer provider;

    public Uni<CashbackEntity> execute(final CashbackEntity entity) {
        return provider
                .process(entity);
    }
}
