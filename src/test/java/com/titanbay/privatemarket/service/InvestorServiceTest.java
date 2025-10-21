package com.titanbay.privatemarket.service;

import com.titanbay.privatemarket.entity.Investor;
import com.titanbay.privatemarket.repository.InvestorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InvestorServiceTest {

    @Mock
    private InvestorRepository investorRepository;

    @InjectMocks
    private InvestorService investorService;

    private Investor investor1;
    private Investor investor2;

    @BeforeEach
    void setUp() {
        investor1 = new Investor();
        investor1.setId(UUID.randomUUID());
        investor1.setName("Alice");
        investor1.setEmail("alice@example.com");

        investor2 = new Investor();
        investor2.setId(UUID.randomUUID());
        investor2.setName("Bob");
        investor2.setEmail("bob@example.com");
    }

    @Test
    void testGetAllInvestors() {
        when(investorRepository.findAll()).thenReturn(Arrays.asList(investor1, investor2));

        List<Investor> result = investorService.getAllInvestors();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(investorRepository, times(1)).findAll();
    }

    @Test
    void testAddInvestorSuccess() {
        when(investorRepository.save(investor1)).thenReturn(investor1);

        Investor result = investorService.addInvestor(investor1);

        assertNotNull(result);
        assertEquals("Alice", result.getName());
        assertEquals("alice@example.com", result.getEmail());
    }
}
