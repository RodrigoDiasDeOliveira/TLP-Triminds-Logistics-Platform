package com.triminds.tlp.company.service;

import com.triminds.tlp.company.model.Company;
import com.triminds.tlp.company.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public Company save(Company company) {
        return repository.save(company);
    }

    public List<Company> findAll() {
        return repository.findAll();
    }

    public Company findById(Long id) {
        return repository.findById(id)
                .orElseThrow();
    }
} 