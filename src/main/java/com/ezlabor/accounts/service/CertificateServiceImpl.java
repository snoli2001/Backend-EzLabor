package com.ezlabor.accounts.service;

import com.ezlabor.accounts.domain.model.background.Certificate;
import com.ezlabor.accounts.domain.repository.CertificateRepository;
import com.ezlabor.accounts.domain.repository.KnowledgeRepository;
import com.ezlabor.accounts.domain.service.CertificateService;
import com.ezlabor.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;
    private final KnowledgeRepository knowledgeRepository;

    @Autowired
    public CertificateServiceImpl(CertificateRepository certificateRepository, KnowledgeRepository knowledgeRepository) {
        this.certificateRepository = certificateRepository;
        this.knowledgeRepository = knowledgeRepository;
    }

    @Override
    public List<Certificate> getAllCertificatesByKnowledgeId(Long knowledgeId, Pageable pageable) {
        return certificateRepository.findByKnowledgeId(knowledgeId);
    }

    @Override
    public Certificate getCertificateByIdAndKnowledgeId(Long certificateId, Long knowledgeId) {
        return certificateRepository.findByIdAndKnowledgeId(certificateId, knowledgeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("certificate not found with Id" + certificateId + "and knowledgeId"+ knowledgeId));
    }

    @Override
    public Certificate createCertificate(Long knowledgeId, Certificate certificate) {
        return knowledgeRepository.findById(knowledgeId).map(knowledge -> {
            certificate.setKnowledge(knowledge);
            return certificateRepository.save(certificate);
        })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Knowledge", "Id", knowledgeId
                ));
    }

    @Override
    public Certificate updateCertificate(Long knowledgeId, Long certificateId, Certificate certificateDetails) {
        if(!knowledgeRepository.existsById(knowledgeId))
            throw new ResourceNotFoundException(
                    "knowledge","Id", knowledgeId
            );
        return certificateRepository.findById(certificateId)
                .map(certificate -> {
                    certificate.setCertificate_url(certificateDetails.getCertificate_url());
                    return certificateRepository.save(certificate);
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "certificate","Id", certificateId
                ));
    }

    @Override
    public ResponseEntity<?> deleteCertificate(Long knowledgeId, Long certificateId) {
        if(!knowledgeRepository.existsById(knowledgeId))
            throw new ResourceNotFoundException(
                    "knowledge","Id", knowledgeId
            );
        return certificateRepository.findById(certificateId)
                .map(certificate -> {
                    certificateRepository.delete(certificate);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "certificate","Id", certificateId
                ));
    }
}
