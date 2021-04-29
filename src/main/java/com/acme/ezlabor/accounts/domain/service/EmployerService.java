package com.acme.ezlabor.accounts.domain.service;

import com.acme.ezlabor.accounts.domain.model.Employer;
import com.acme.ezlabor.accounts.domain.model.Freelancer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface EmployerService {
    Page<Employer> getAllEmployers(Pageable pageable);
    Employer createEmployer(Employer employer);
    Employer updateEmployer(Long employerId, Employer employerDetails);
    ResponseEntity<?> deleteEmployer(Long employerId);

    Employer getEmployerById(Long employerId);
    Page<Employer> getEmployerByCompanyName(String companyName);
    Page<Employer> getEmployersByFirstname(String firstName);
    Page<Employer> getEmployersByLastname(String lastName);
}
