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

    private static final String QUERY_CUSTOMER = "Select c from CashbackDatabase c where c.customer = :customer where c.create_date order by desc";

    public Uni<Void> persist(final Uni<CashbackEntity> entityUni) {
        return Panache.withTransaction(() -> entityUni
                        .onItem().transformToUni(this::executeFindTotalCashbackCustomer)
                        .onItem().transform(CashbackDatabaseConverter::toDatabase)
                        .onItem().transformToUni(c -> c.persist())
                        .onItem().transform(c -> (CashbackDatabase) c)
                        .invoke(v -> log.info("save cashback to transaction {}",  v.getTransaction())))
                .onFailure().recoverWithUni(() -> Uni.createFrom().failure(new RuntimeException()))
                .replaceWithVoid();
    }

    public Uni<CashbackEntity> executeFindTotalCashbackCustomer(CashbackEntity c) {
        return processQuery(Uni.createFrom().item(c.getCustomer()))
                .onItem()
                .transform(v -> {
                    c.setTotal(c.getTotal().add(v.getTotal()));
                    return c;
                }).replaceIfNullWith(c);
    }

    private Uni<CashbackDatabase> processQuery(final Uni<String> customerCodeUni) {
        return customerCodeUni
                .onItem()
                .transform(c -> CashbackDatabase.find(QUERY_CUSTOMER, c)
                        .firstResult()
                ).replaceWith(Uni.createFrom().nullItem());
    }
}
