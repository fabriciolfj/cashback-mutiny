package com.github.cashback.entities;

import java.math.BigDecimal;

public record CashbackEntity(String code, String transaction, String customer, BigDecimal movementValue) {}
