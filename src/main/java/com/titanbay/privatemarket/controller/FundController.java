package com.titanbay.privatemarket.controller;

import com.titanbay.privatemarket.entity.Fund;
import com.titanbay.privatemarket.exception.ResourceNotFoundException;
import com.titanbay.privatemarket.service.FundService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing funds
 * Provides endpoints to create, retrieve, modify funds
 * Base URL: /funds
 */
@RestController
@RequestMapping("/funds")
public class FundController {

    private final FundService fundService;

    public FundController(FundService fundService) {
        this.fundService = fundService;
    }

    @GetMapping
    public List<Fund> getAllFunds() {
        return fundService.getAllFunds();
    }

    @PostMapping
    public ResponseEntity<Fund> createFund(@RequestBody Fund fund) {
        Fund response = fundService.saveFund(fund);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Fund> updateFund(@RequestBody Fund fund) {
        if(fund.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        return fundService.updateFund(fund)
                .map(updatedFund -> ResponseEntity.ok(updatedFund))
                .orElseThrow(() -> new ResourceNotFoundException("Fund not found with id: " + fund.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fund> getFund(@PathVariable UUID id) {
        Optional<Fund> fundOptional = fundService.getFundById(id);
        return  fundOptional.map(fund -> ResponseEntity.ok(fundOptional.get()))
                .orElseThrow(() -> new ResourceNotFoundException("Fund not found with id: " + id));
    }
}
