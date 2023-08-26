package com.github.cashback.usecase;

import com.github.cashback.entities.CashbackEntity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.math.BigDecimal;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class CashbackCalculeUseCase {

    @ConfigProperty(name = "cal.percentage")
    private String percentage;
    private final CashbackProviderSave providerSave;

    public Uni<Void> execute(final Uni<CashbackEntity> uniEntity) {
        return uniEntity
                .invoke(c -> log.info("before execute calcule to transaction {}", c.getTransaction()))
                .onItem()
                .transform(c -> c.calcule(new BigDecimal(percentage)))
                .invoke(r -> log.info("calculated cashback to transaction {}, customer {}, value {}", r.getTransaction(), r.getCustomer(), r.getValue()))
                .onItem()
                .transformToUni(v -> providerSave.process(Uni.createFrom().item(v)));
    }
}
