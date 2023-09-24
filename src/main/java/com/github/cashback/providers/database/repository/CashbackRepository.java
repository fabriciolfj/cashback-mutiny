package com.github.cashback.providers.database.repository;

import com.github.cashback.entities.CashbackEntity;
import com.github.cashback.providers.database.converter.CashbackDatabaseConverter;
import com.github.cashback.providers.database.data.CashbackDatabase;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@ApplicationScoped
public class CashbackRepository {

    private static final String QUERY_CUSTOMER = "Select c from CashbackDatabase c where c.customer =?1 order by c.createDate desc LIMIT 1";

    public Uni<Void> persist(final Uni<CashbackEntity> entityUni) {
        return Panache.withTransaction(() -> entityUni
                        .onItem().transformToUni(this::getTotalCashbackCustomer)
                        .onItem().transform(CashbackDatabaseConverter::toDatabase)
                        .onItem().transformToUni(c -> c.persist())
                        .onItem().transform(c -> (CashbackDatabase) c)
                        .invoke(v -> log.info("save cashback to transaction {}",  v.getTransaction())))
                .onFailure()
                .invoke(v -> log.error("fail save, details {}", v.getMessage()))
                .replaceWithVoid();
    }

    public Uni<CashbackEntity> findTotalCustomer(final CashbackEntity entity) {
        return Panache.withTransaction(() -> findLastRowCustomer(entity)
                .onItem().ifNotNull().transform(CashbackDatabaseConverter::toEntity)
                .onFailure().recoverWithNull());
    }

    private Uni<CashbackEntity> getTotalCashbackCustomer(final CashbackEntity c) {
        return findLastRowCustomer(c)
                .onItem().transform(v -> c.addTotal(v.getTotal()))
                .onFailure().recoverWithNull().replaceWith(c);
    }

    private Uni<CashbackDatabase> findLastRowCustomer(final CashbackEntity c) {
        return CashbackDatabase.find(QUERY_CUSTOMER, c.getCustomer()).firstResult()
                .onItem().transform(v -> (CashbackDatabase) v);

    }
}
