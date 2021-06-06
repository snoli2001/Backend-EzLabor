package com.ezlabor.accounts.domain.service;

import com.ezlabor.accounts.domain.model.background.Certificate;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CertificateService {
    List<Certificate> getAllCertificatesBySkillId(Long skillId);
    Optional<Certificate> getCertificateByIdAndSkillId(Long certificateId, Long skillId);
    Certificate createCertificate(Long skillId, Certificate certificate);
    Certificate updateCertificate(Long skillId, Long certificateId, Certificate certificateDetails);
    ResponseEntity<?> deleteCertificate(Long skillId, Long certificateId);
}
