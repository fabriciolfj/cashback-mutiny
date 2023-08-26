package com.github.cashback.entrypoints.message.listener;

import com.github.cashback.entrypoints.message.converter.CashbackDTOConverter;
import com.github.cashback.entrypoints.message.dto.CashbackDTO;
import com.github.cashback.usecase.CashbackCalculeUseCase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Slf4j
@RequiredArgsConstructor
@ApplicationScoped
public class CashbackListener {

    private final CashbackCalculeUseCase useCase;

    @Acknowledgment(Acknowledgment.Strategy.POST_PROCESSING)
    @Incoming("cashback-credit")
    public Uni<Void> process(final CashbackDTO dto) {
        log.info("receive payload dto {}", dto);
        var entity = CashbackDTOConverter.toEntity(dto);
        return useCase.execute(Uni.createFrom().item(entity));
    }
}
