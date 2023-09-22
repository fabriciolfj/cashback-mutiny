package com.github.cashback.usecase.cashbacksave;

import com.github.cashback.entities.CashbackEntity;
import io.smallrye.mutiny.Uni;

public interface CashbackProviderSave {

    Uni<Void> process(final Uni<CashbackEntity> entityUni);
}
