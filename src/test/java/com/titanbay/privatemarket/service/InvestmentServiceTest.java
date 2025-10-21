package com.titanbay.privatemarket.service;

import com.titanbay.privatemarket.dto.InvestmentRequestDto;
import com.titanbay.privatemarket.dto.InvestmentResponseDto;
import com.titanbay.privatemarket.entity.Fund;
import com.titanbay.privatemarket.entity.Investment;
import com.titanbay.privatemarket.entity.Investor;
import com.titanbay.privatemarket.repository.FundRepository;
import com.titanbay.privatemarket.repository.InvestmentRepository;
import com.titanbay.privatemarket.repository.InvestorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvestmentServiceTest {
    @Mock
    private InvestmentRepository investmentRepository;

    @Mock
    private FundRepository fundRepository;

    @Mock
    private InvestorRepository investorRepository;

    @InjectMocks
    private InvestmentService investmentService;

    @Test
    void testSaveInvestmentSuccess() {
        // given
        UUID fundId = UUID.randomUUID();
        UUID investorId = UUID.randomUUID();

        Fund fund = new Fund();
        fund.setId(fundId);
        Investor investor = new Investor();
        investor.setId(investorId);

        InvestmentRequestDto requestDto = new InvestmentRequestDto();
        requestDto.setInvestorId(investorId);
        requestDto.setAmountUsd(new BigDecimal("75000000.00"));
        requestDto.setInvestmentDate(LocalDate.of(2024, 9, 22));

        Investment savedInvestment = new Investment(investor, fund,
                requestDto.getAmountUsd(), requestDto.getInvestmentDate());

        // mock repository behavior
        when(fundRepository.findById(fundId)).thenReturn(Optional.of(fund));
        when(investorRepository.findById(investorId)).thenReturn(Optional.of(investor));
        when(investmentRepository.save(ArgumentMatchers.any(Investment.class)))
                .thenReturn(savedInvestment);

        // when
        InvestmentResponseDto result = investmentService.saveInvestment(fundId, requestDto);

        // then
        assertNotNull(result);
        assertEquals(fundId, result.getFundId());
        assertEquals(investorId, result.getInvestorId());
        assertEquals(requestDto.getAmountUsd(), result.getAmountUsd());
        assertEquals(requestDto.getInvestmentDate(), result.getInvestmentDate());
    }
}
