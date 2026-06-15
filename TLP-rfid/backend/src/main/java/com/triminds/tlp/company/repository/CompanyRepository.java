package com.triminds.tlp.company.repository;

import com.triminds.tlp.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByTenantId(String tenantId);
}
