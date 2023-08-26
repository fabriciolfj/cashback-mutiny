package com.github.cashback.entrypoints.message.deserializer;

import com.github.cashback.entrypoints.message.dto.CashbackDTO;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class CasbhbackDTODeserializer extends JsonbDeserializer<CashbackDTO> {

    public CasbhbackDTODeserializer() {
        super(CashbackDTO.class);
    }
}
