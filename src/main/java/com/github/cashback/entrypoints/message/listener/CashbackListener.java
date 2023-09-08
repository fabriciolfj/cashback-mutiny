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
import org.eclipse.microprofile.reactive.messaging.Message;

@Slf4j
@RequiredArgsConstructor
@ApplicationScoped
public class CashbackListener {

    private final CashbackCalculeUseCase useCase;

    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    @Incoming("cashback-credit")
    public Uni<Void> process(final Message<CashbackDTO> dto) {
        return Uni.createFrom().item(dto.getPayload())
                .invoke(d -> log.info("receive payload dto {}", d))
                .onItem()
                .transform(t -> CashbackDTOConverter.toEntity(t))
                .onItem()
                .transformToUni(entity -> useCase.execute(Uni.createFrom().item(entity)))
                .invoke(c -> {
                    log.info("processo successful");
                }).replaceWithVoid();
    }
}
