package com.titanbay.privatemarket.repository;

import com.titanbay.privatemarket.entity.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Fund repository class
 * Interact with fund table
 */
@Repository
public interface FundRepository extends JpaRepository<Fund, UUID> {

}

