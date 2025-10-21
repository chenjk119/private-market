package com.titanbay.privatemarket.service;

import com.titanbay.privatemarket.entity.Investor;
import com.titanbay.privatemarket.repository.InvestorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Investor service class
 * Handles business logic for service endpoints
 */
@Service
public class InvestorService {

    private final InvestorRepository investorRepository;

    public InvestorService(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    public List<Investor> getAllInvestors() {
        return investorRepository.findAll();
    }

    public Investor addInvestor(Investor investor) {
        return investorRepository.save(investor);
    }
}
