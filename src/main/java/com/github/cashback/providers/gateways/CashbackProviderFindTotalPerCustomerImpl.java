package com.github.cashback.providers.gateways;

import com.github.cashback.entities.CashbackEntity;
import com.github.cashback.providers.database.repository.CashbackRepository;
import com.github.cashback.usecase.cashbacktotalcustomer.CashbackProviderFindTotalPerCustomer;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class CashbackProviderFindTotalPerCustomerImpl implements CashbackProviderFindTotalPerCustomer {

    private final CashbackRepository repository;

    @Override
    public Uni<CashbackEntity> process(final CashbackEntity entity) {
        return repository.executeFindTotalCashbackCustomer(entity)
                .invoke(c -> log.info("total customer {} {}", entity.getCustomer(), entity.getTotal()));
    }
}
