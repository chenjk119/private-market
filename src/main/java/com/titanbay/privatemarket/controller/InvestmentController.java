package com.titanbay.privatemarket.controller;

import com.titanbay.privatemarket.dto.InvestmentRequestDto;
import com.titanbay.privatemarket.dto.InvestmentResponseDto;
import com.titanbay.privatemarket.service.InvestmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing Investments associated with Funds.
 * Provides endpoint to create new investments for a given fund.
 * Base URL: /funds/{fundId}/investments
 */
@RestController
@RequestMapping("/funds/{fundId}/investments")
public class InvestmentController {

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }


    @PostMapping
    public ResponseEntity<InvestmentResponseDto> saveInvestment(
            @PathVariable("fundId") UUID fundId,
            @Valid @RequestBody InvestmentRequestDto investment
    ) {
        InvestmentResponseDto response = investmentService.saveInvestment(fundId, investment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<InvestmentResponseDto> getAllInvestments(@PathVariable("fundId") UUID fundId) {
        return investmentService.getInvestmentsByFundId(fundId);
    }
}
