package com.github.cashback.entrypoints.message.deserializer;

import com.github.cashback.entrypoints.message.dto.CashbackUseDTO;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class CashbackDebitDTODeserializer extends JsonbDeserializer<CashbackUseDTO> {

    public CashbackDebitDTODeserializer() {
        super(CashbackUseDTO.class);
    }
}
