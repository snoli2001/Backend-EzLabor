package com.ezlabor.accounts.domain.service;

import com.ezlabor.accounts.domain.model.background.Certificate;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CertificateService {
    List<Certificate> getAllCertificatesByKnowledgeId(Long knowledgeId, Pageable pageable);
    Certificate getCertificateByIdAndKnowledgeId(Long certificateId, Long knowledgeId);
    Certificate createCertificate(Long knowledgeId, Certificate certificate);
    Certificate updateCertificate(Long knowledgeId, Long certificateId, Certificate certificateDetails);
    ResponseEntity<?> deleteCertificate(Long knowledgeId, Long certificateId);
}
