package com.titanbay.privatemarket.service;

import com.titanbay.privatemarket.dto.InvestmentRequestDto;
import com.titanbay.privatemarket.dto.InvestmentResponseDto;
import com.titanbay.privatemarket.entity.Fund;
import com.titanbay.privatemarket.entity.Investment;
import com.titanbay.privatemarket.entity.Investor;
import com.titanbay.privatemarket.repository.FundRepository;
import com.titanbay.privatemarket.repository.InvestmentRepository;
import com.titanbay.privatemarket.repository.InvestorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Investment service class
 * Handles business logic for investment endpoints
 */
@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final FundRepository fundRepository;
    private final InvestorRepository investorRepository;

    public InvestmentService(InvestmentRepository investmentRepository,
                             FundRepository fundRepository,
                             InvestorRepository investorRepository) {
        this.investmentRepository = investmentRepository;
        this.fundRepository = fundRepository;
        this.investorRepository = investorRepository;
    }

    public List<InvestmentResponseDto> getInvestmentsByFundId(UUID fundId) {
        Fund fund = fundRepository.findById(fundId)
                .orElseThrow(() -> new RuntimeException("Fund not found"));
        List<Investment> investments = investmentRepository.findByFund(fund);
        return investments.stream()
                .map(investment -> {
                    return new InvestmentResponseDto(
                            investment.getId(),
                            investment.getInvestor().getId(),
                            investment.getFund().getId(),
                            investment.getAmountUsd(),
                            investment.getInvestmentDate()
                    );
                }).toList();
    }

    public InvestmentResponseDto saveInvestment(UUID fundId, InvestmentRequestDto investmentRequestDto) {
        Optional<Fund> fundOptional = fundRepository.findById(fundId);
        if(fundOptional.isEmpty()){
            throw new RuntimeException("Fund not found");
        }
        Fund fund = fundOptional.get();
        Optional<Investor> investorOptional = investorRepository.findById(investmentRequestDto.getInvestorId());
        if(investorOptional.isEmpty()) {
            throw new RuntimeException("Investor not found");
        }
        Investor investor = investorOptional.get();
        Investment investment = new Investment(
                investor,
                fund,
                investmentRequestDto.getAmountUsd(),
                investmentRequestDto.getInvestmentDate()
        );

        Investment savedInvestment = investmentRepository.save(investment);
        return new InvestmentResponseDto(
                savedInvestment.getId(),
                savedInvestment.getInvestor().getId(),
                savedInvestment.getFund().getId(),
                savedInvestment.getAmountUsd(),
                savedInvestment.getInvestmentDate()
        );
    }
}
