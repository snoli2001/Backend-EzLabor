package com.ezlabor.accounts.domain.service;

import com.ezlabor.accounts.domain.model.background.Certificate;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CertificateService {
    List<Certificate> getAllCertificatesByFreelancerIdAndKnowledgeId(Long freelancerId, Long knowledgeId);
    Optional<Certificate> getCertificateByIdAndKnowledgeId(Long certificateId, Long knowledgeId);
    Certificate createCertificate(Long knowledgeId, Certificate certificate);
    Certificate updateCertificate(Long knowledgeId, Long certificateId, Certificate certificateDetails);
    ResponseEntity<?> deleteCertificate(Long knowledgeId, Long certificateId);
}
