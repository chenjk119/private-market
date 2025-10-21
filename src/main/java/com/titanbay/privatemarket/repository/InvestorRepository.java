package com.titanbay.privatemarket.repository;

import com.titanbay.privatemarket.entity.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Investor repository class
 * Interact with investor table
 */
@Repository
public interface InvestorRepository extends JpaRepository<Investor, UUID> {

}

