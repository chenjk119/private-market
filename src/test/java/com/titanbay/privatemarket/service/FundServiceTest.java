package com.titanbay.privatemarket.service;

import com.titanbay.privatemarket.entity.Fund;
import com.titanbay.privatemarket.repository.FundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FundServiceTest {

    @Mock
    private FundRepository fundRepository;

    @InjectMocks
    private FundService fundService;

    private Fund fund1;
    private Fund fund2;

    @BeforeEach
    void setUp() {
        fund1 = new Fund();
        fund1.setId(UUID.randomUUID());
        fund1.setName("Fund A");
        fund1.setTargetSizeUsd(new BigDecimal("1000000"));
        fund1.setVintageYear(2020);
        fund1.setStatus("ACTIVE");

        fund2 = new Fund();
        fund2.setId(UUID.randomUUID());
        fund2.setName("Fund B");
        fund2.setTargetSizeUsd(new BigDecimal("500000"));
        fund2.setVintageYear(2021);
        fund2.setStatus("CLOSED");
    }

    @Test
    void testGetAllFunds() {
        when(fundRepository.findAll()).thenReturn(Arrays.asList(fund1, fund2));

        List<Fund> result = fundService.getAllFunds();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(fundRepository, times(1)).findAll();
    }

    @Test
    void testSaveFundSuccess() {
        when(fundRepository.save(fund1)).thenReturn(fund1);

        Fund result = fundService.saveFund(fund1);

        assertNotNull(result);
        assertEquals("Fund A", result.getName());
        verify(fundRepository, times(1)).save(fund1);
    }

    @Test
    void testGetFundByIdSuccess() {
        UUID id = fund1.getId();
        when(fundRepository.findById(id)).thenReturn(Optional.of(fund1));

        Optional<Fund> result = fundService.getFundById(id);

        assertTrue(result.isPresent());
        assertEquals("Fund A", result.get().getName());
        verify(fundRepository, times(1)).findById(id);
    }

    @Test
    void testGetFundByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(fundRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Fund> result = fundService.getFundById(id);

        assertFalse(result.isPresent());
        verify(fundRepository, times(1)).findById(id);
    }

    @Test
    void testUpdateFundExistingFundSuccess() {
        Fund updatedFund = new Fund();
        updatedFund.setId(fund1.getId());
        updatedFund.setName("Updated Fund");
        updatedFund.setVintageYear(2025);
        updatedFund.setTargetSizeUsd(new BigDecimal("2000000"));
        updatedFund.setStatus("ACTIVE");

        when(fundRepository.findById(fund1.getId())).thenReturn(Optional.of(fund1));
        when(fundRepository.save(any(Fund.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Fund> result = fundService.updateFund(updatedFund);

        assertTrue(result.isPresent());
        assertEquals("Updated Fund", result.get().getName());
        assertEquals(2025, result.get().getVintageYear());
        assertEquals(new BigDecimal("2000000"), result.get().getTargetSizeUsd());
        verify(fundRepository, times(1)).findById(fund1.getId());
        verify(fundRepository, times(1)).save(any(Fund.class));
    }

    @Test
    void testUpdateFundNonExistingFundReturnsEmpty() {
        Fund updatedFund = new Fund();
        updatedFund.setId(UUID.randomUUID());

        when(fundRepository.findById(updatedFund.getId())).thenReturn(Optional.empty());

        Optional<Fund> result = fundService.updateFund(updatedFund);

        assertFalse(result.isPresent());
        verify(fundRepository, times(1)).findById(updatedFund.getId());
        verify(fundRepository, never()).save(any());
    }
}
