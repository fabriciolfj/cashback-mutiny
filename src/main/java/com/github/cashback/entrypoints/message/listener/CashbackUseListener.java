package com.github.cashback.entrypoints.message.listener;

import com.github.cashback.entrypoints.message.dto.CashbackUseDTO;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class CashbackUseListener {

    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    @Incoming("cashback-debit")
    public Uni<Void> receive(final Message<CashbackUseDTO> message) {
        return Uni.createFrom().item(message)
                .onItem().transform(Message::getPayload)
                .invoke(c -> log.info("received debit cashback, payload {}", c))
                .replaceWithVoid();
    }
}
