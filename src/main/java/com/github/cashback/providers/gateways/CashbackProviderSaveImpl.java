package com.github.cashback.providers.gateways;

import com.github.cashback.entities.CashbackEntity;
import com.github.cashback.providers.database.repository.CashbackRepository;
import com.github.cashback.usecase.cashbacksave.CashbackProviderSave;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class CashbackProviderSaveImpl implements CashbackProviderSave {

    private final CashbackRepository cashbackRepository;

    @Override
    public Uni<Void> process(Uni<CashbackEntity> entityUni) {
        return cashbackRepository.persist(entityUni)
                .invoke(c -> log.info("cashback save success"));
    }
}
