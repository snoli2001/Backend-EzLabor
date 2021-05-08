package com.ezlabor.accounts;

import com.ezlabor.accounts.domain.model.background.Certificate;
import com.ezlabor.accounts.domain.model.background.Knowledge;
import com.ezlabor.accounts.domain.repository.CertificateRepository;
import com.ezlabor.accounts.domain.repository.KnowledgeRepository;
import com.ezlabor.accounts.domain.service.CertificateService;
import com.ezlabor.accounts.domain.service.KnowledgeService;
import com.ezlabor.accounts.service.CertificateServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CertificateServiceImplTest {
    @MockBean
    private CertificateRepository certificateRepository;
    @Autowired
    private CertificateService certificateService;
    @MockBean
    private KnowledgeRepository knowledgeRepository;
    @TestConfiguration
    static class CertificateServiceTestConfiguration{

        @Bean
        public CertificateService certificateService(){
            return new CertificateServiceImpl();
        }
    }

    @Test
    void whenGetCertificateByKnowledgeIdAndCertificateIdWithValidIdThenReturnsCertificate() {
        //Arrange
        Long id = 1L;
        Knowledge knowledge = new Knowledge();
        knowledge.setId(id);
        knowledge.setName("angular");

        Certificate certificate = new Certificate();
        certificate.setId(id);
        certificate.setCertificate_url("http://esteesuncertificado.com");
        when(certificateRepository.findByIdAndKnowledgeId(id,id)).thenReturn(Optional.of(certificate));
        //Act
        Optional<Certificate> foundCertificate = certificateService.getCertificateByIdAndKnowledgeId(id,id);
        //Assert
        assertThat(foundCertificate.get().getId()).isEqualTo(id);
    }

    @Test
    void whenGetAllCertificatesByKnowledgeIdWithValidIdThenReturnsCertificates(){
        //Arrange
        Long id = 1L;
        Knowledge knowledge = new Knowledge();
        knowledge.setId(id);
        knowledge.setName("angular");
        List<Certificate> certificateList = new ArrayList<Certificate>();
        Certificate certificate = new Certificate();
        certificate.setId(id);
        certificate.setKnowledge(knowledge);
        certificate.setCertificate_url("http://esteesuncertificado.com");
        certificateList.add(certificate);

        Certificate certificate2 = new Certificate();
        certificate2.setId(id);
        certificate2.setKnowledge(knowledge);
        certificate2.setCertificate_url("http://esteesuncertificado.com");
        certificateList.add(certificate2);
        when(certificateRepository.findByFreelancerIdAndKnowledgeId(id,id)).thenReturn(certificateList);
        //Act
        List<Certificate> foundCertificates = certificateService.getAllCertificatesByFreelancerIdAndKnowledgeId(id, id);
        //Assert
        assertThat(foundCertificates.get(0).getKnowledge().getId()).isEqualTo(id);
        assertThat(foundCertificates.size()).isEqualTo(2);
    }
}
