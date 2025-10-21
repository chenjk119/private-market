package com.titanbay.privatemarket.controller;

import com.titanbay.privatemarket.entity.Investor;
import com.titanbay.privatemarket.service.InvestorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for managing investors
 * Provides endpoints to create, retrieve investors
 * Base URL: /investors
 */
@RestController
@RequestMapping("/investors")
public class InvestorController {

    private final InvestorService investorService;

    public InvestorController(InvestorService investorService) {
        this.investorService = investorService;
    }

    @GetMapping
    public List<Investor> getAllInvestors() {
        return investorService.getAllInvestors();
    }

    @PostMapping
    public ResponseEntity<Investor> saveInvestor(@RequestBody Investor investor) {
        Investor response =  investorService.addInvestor(investor);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
