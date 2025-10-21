package com.titanbay.privatemarket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class InvestmentRequestDto {

    @NotNull(message = "Investor ID is required")
    @JsonProperty("investor_id")
    private UUID investorId;

    @NotNull(message = "Amount is required")
    @JsonProperty("amount_usd")
    private BigDecimal amountUsd;

    @NotNull(message = "Investment date is required")
    @JsonProperty("investment_date")
    private LocalDate investmentDate;
}
