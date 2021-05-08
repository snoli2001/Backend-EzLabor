package com.ezlabor.accounts.controller;
import com.ezlabor.accounts.resource.certificate.CertificateResource;
import org.modelmapper.ModelMapper;
import com.ezlabor.accounts.domain.model.background.Certificate;
import com.ezlabor.accounts.domain.service.CertificateService;
import com.ezlabor.accounts.resource.certificate.SaveCertificateResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class KnowledgeCertificatesController {
    @Autowired
    private CertificateService certificateService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/knowledge/{knowledgeId}/certificates/")
    public List<CertificateResource> getAllCertificatesByKnowledgeId(@PathVariable Long knowledgeId)
    {
        return certificateService.getAllCertificatesByKnowledgeId(knowledgeId).stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("knowledge/{knowledgeId}/certificates/{certificateId}")
    public CertificateResource getCertificateById(@PathVariable Long knowledgeId, @PathVariable Long certificateId){
        return convertToResource(certificateService.getCertificateByIdAndKnowledgeId(certificateId, knowledgeId).get());
    }

    @PostMapping("knowledge/{knowledgeId}/certificates")
    public CertificateResource createCertificate(@PathVariable Long knowledgeId, @Valid @RequestBody SaveCertificateResource resource){
        Certificate certificate = convertToEntity(resource);
        return convertToResource(certificateService.createCertificate(knowledgeId, certificate));
    }

    @PutMapping("/certificates/{certificateId}")
    public CertificateResource updateCertificate(@PathVariable Long knowledgeId, Long certificateId, @Valid @RequestBody SaveCertificateResource resource){
        Certificate certificate = convertToEntity(resource);
        return convertToResource(certificateService.updateCertificate(knowledgeId,certificateId,certificate));
    }


    @DeleteMapping("/certificates/{certificateId}")
    public ResponseEntity<?> deleteCertificate(@PathVariable Long
                                                       knowledgeId, Long certificateId){
        return certificateService.deleteCertificate(knowledgeId, certificateId);
    }

    private Certificate convertToEntity(SaveCertificateResource resource){
        return mapper.map(resource, Certificate.class);
    }

    private CertificateResource convertToResource(Certificate entity){
        return mapper.map(entity, CertificateResource.class);
    }



}