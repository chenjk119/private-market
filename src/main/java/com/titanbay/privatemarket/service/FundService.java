package com.titanbay.privatemarket.service;

import com.titanbay.privatemarket.entity.Fund;
import com.titanbay.privatemarket.repository.FundRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Fund service class
 * Handles business logic for fund endpoints
 */
@Service
public class FundService {

    private final FundRepository fundRepository;

    public FundService(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    public List<Fund> getAllFunds() {
        return fundRepository.findAll();
    }

    public Fund saveFund(Fund fund) {
        return fundRepository.save(fund);
    }

    public Optional<Fund> getFundById(UUID id) {
        return fundRepository.findById(id);
    }

    public Optional<Fund> updateFund(Fund updateFund) {
        UUID id = updateFund.getId();
        return fundRepository.findById(id)
                .map(fund -> {
                    fund.setName(updateFund.getName());
                    fund.setVintageYear(updateFund.getVintageYear());
                    fund.setTargetSizeUsd(updateFund.getTargetSizeUsd());
                    fund.setStatus(updateFund.getStatus());
                    return fundRepository.save(fund);
                });
    }
}
