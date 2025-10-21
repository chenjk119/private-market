package com.titanbay.privatemarket.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class InvestmentResponseDto {

    private UUID id;

    @JsonProperty("fund_id")
    private UUID fundId;

    @JsonProperty("investor_id")
    private UUID investorId;

    @JsonProperty("amount_usd")
    private BigDecimal amountUsd;

    @JsonProperty("investment_date")
    private LocalDate investmentDate;

    public InvestmentResponseDto(UUID id, UUID investorId, UUID fundId, BigDecimal amountUsd, LocalDate investmentDate) {
        this.id = id;
        this.investorId = investorId;
        this.fundId = fundId;
        this.amountUsd = amountUsd;
        this.investmentDate = investmentDate;
    }
}
