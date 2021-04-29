package com.acme.ezlabor.accounts.domain.repository;

import com.acme.ezlabor.accounts.domain.model.Employer;
import com.acme.ezlabor.accounts.domain.model.Freelancer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer,Long> {
    Employer findByFirstname(String firstname);
    Employer findByLastname(String lastname);
    Page<Employer> findEmployersByCompanyName(String companyName, Pageable pageable);
    Employer findByUsername(String username);
}
