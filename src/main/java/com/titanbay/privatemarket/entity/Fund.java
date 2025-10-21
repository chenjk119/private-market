package com.titanbay.privatemarket.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "fund")
@Getter
@Setter
public class Fund {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "vintage_year", nullable = false)
    @JsonProperty("vintage_year")
    private Integer vintageYear;

    @Column(name = "target_size_usd", precision = 18, scale = 2, nullable = false)
    @JsonProperty("target_size_usd")
    private BigDecimal targetSizeUsd;

    @Column(nullable = false)
    private String status;

    @Column(name = "created_at", updatable = false, nullable = false)
    @JsonProperty("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

}
