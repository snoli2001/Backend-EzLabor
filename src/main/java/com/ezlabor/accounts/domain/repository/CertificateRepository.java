package com.ezlabor.accounts.domain.repository;

import com.ezlabor.accounts.domain.model.background.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByFreelancerIdAndKnowledgeId(Long freelancerId, Long knowledgeId);
    Optional<Certificate> findByIdAndKnowledgeId (Long certificateId, Long knowledgeId);
}
