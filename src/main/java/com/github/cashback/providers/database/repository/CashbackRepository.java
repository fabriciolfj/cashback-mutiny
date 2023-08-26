package com.github.cashback.providers.database.repository;

import com.github.cashback.entities.CashbackEntity;
import com.github.cashback.providers.database.converter.CashbackDatabaseConverter;
import com.github.cashback.providers.database.data.CashbackDatabase;
import com.github.cashback.usecase.CashbackProviderSave;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class CashbackRepository implements CashbackProviderSave {

    @Override
    public Uni<Void> process(Uni<CashbackEntity> entityUni) {
        return entityUni
                .onItem().transform(CashbackDatabaseConverter::toDatabase)
                .onItem().transformToUni(c -> Panache.withTransaction(() -> c.persist()))
                .onItem().transform(c -> (CashbackDatabase) c)
                .invoke(v -> log.info("save cashback to transaction {}",  v.getTransaction()))
                .onFailure().recoverWithUni(() -> Uni.createFrom().failure(new RuntimeException()))
                .replaceWithVoid();
    }
}
