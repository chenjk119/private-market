package com.titanbay.privatemarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "investment")
@Getter
@Setter
public class Investment {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investor_id", nullable = false)
    @JsonIgnore
    private Investor investor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fund_id", nullable = false)
    private Fund fund;

    @Column(name = "amount_usd", nullable = false, precision = 18, scale = 2)
    @JsonProperty("amount_usd")
    private BigDecimal amountUsd;

    @Column(name = "investment_date", nullable = false)
    @JsonProperty("investment_date")
    private LocalDate investmentDate;

    // Constructors
    public Investment() {}



    public Investment(Investor investor, Fund fund, BigDecimal amountUsd, LocalDate investmentDate) {
        this.investor = investor;
        this.fund = fund;
        this.amountUsd = amountUsd;
        this.investmentDate = investmentDate;
    }
}
