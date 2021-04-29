package com.acme.ezlabor.accounts.domain.repository;

import com.acme.ezlabor.accounts.domain.model.background.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

}
