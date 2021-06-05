package com.ezlabor.accounts.service;

import com.ezlabor.accounts.domain.model.Freelancer;
import com.ezlabor.accounts.domain.model.Publication;
import com.ezlabor.accounts.domain.repository.PublicationRepository;
import com.ezlabor.accounts.domain.service.PublicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PublicationServiceImplTest {
    @MockBean
    PublicationRepository publicationRepository;
    @Autowired
    PublicationService publicationService;
    @TestConfiguration
    static class PublicationServiceImplTestConfiguration {
        @Bean
        public PublicationService publicationService() {
            return new PublicationServiceImpl();
        }
    }

    @Test
    public void whenGetAllPublicationsByUserId() {
        //Arrange
        Long idFreelancer = 1L;
        Freelancer freelancer = new Freelancer();
        freelancer.setId(1L);
        freelancer.setFirstname("Juan");

        List<Publication> publicationList = new ArrayList<>();

        Long idPublication = 2L;
        Publication publication = new Publication();
        publication.setId(idPublication);
        publication.setContent("Sample content");
        publication.setUser(freelancer);
        publicationList.add(publication);

        Long idPublication1 = 3L;
        Publication publication1 = new Publication();
        publication1.setId(idPublication1);
        publication.setContent("Sample Content 2");
        publication1.setUser(freelancer);
        publicationList.add(publication1);

        Pageable pageable = PageRequest.of(1, 1000);
        when(publicationRepository.findByUserId(idFreelancer)).thenReturn(publicationList);

        //Act
        List<Publication> foundPublications = publicationService.getAllPublicationsByUserId(idFreelancer);

        //Assert
        assertThat(foundPublications.get(0).getUser()).isEqualTo(freelancer);
        assertThat(foundPublications.size()).isEqualTo(2);
    }
}


