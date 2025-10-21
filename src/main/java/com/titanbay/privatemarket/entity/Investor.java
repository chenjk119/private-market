package com.titanbay.privatemarket.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "investor")
@Getter
@Setter
public class Investor {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "investor_type", nullable = false)
    @JsonProperty("investor_type")
    private String investorType;

    @Column(nullable = false)
    private String email;

    @Column(name = "created_at", updatable = false, insertable = false)
    @JsonProperty("created_at")
    private Instant createdAt;

    // Constructors
    public Investor() {}

    public Investor(String name, String investorType, String email) {
        this.name = name;
        this.investorType = investorType;
        this.email = email;
    }
}
