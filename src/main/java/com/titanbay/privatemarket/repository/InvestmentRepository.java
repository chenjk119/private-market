package com.titanbay.privatemarket.repository;

import com.titanbay.privatemarket.entity.Fund;
import com.titanbay.privatemarket.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

/**
 * Investment repository class
 * Interact with investment table
 */
@Repository
public interface InvestmentRepository extends JpaRepository<Investment, UUID> {

    List<Investment> findByFund(Fund fund);
}

