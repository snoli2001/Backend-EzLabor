package com.ezlabor.accounts.service;

import com.ezlabor.accounts.domain.model.Freelancer;
import com.ezlabor.accounts.domain.model.background.Knowledge;
import com.ezlabor.accounts.domain.repository.KnowledgeRepository;
import com.ezlabor.accounts.domain.service.KnowledgeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class KnowledgeServiceImplTest {
    @MockBean
    private KnowledgeRepository knowledgeRepository;
    @Autowired
    private KnowledgeService knowledgeService;
    @TestConfiguration
    static class KnowledgeServiceImplTestConfiguration{
        @Bean
        public KnowledgeService knowledgeService(){ return new KnowledgeServiceImpl();
        }
    }

    @Test
    void whenGetAllKnowledgeByFreelancerId() {
        //Arrange
        Long idFreelancer = 1L;
        Freelancer freelancer = new Freelancer();
        freelancer.setId(1L);
        freelancer.setFirstname("Juan");

        List<Knowledge> knowledgeList = new ArrayList<>();

        Long id = 2L;
        Knowledge knowledge = new Knowledge();
        knowledge.setId(2L);
        knowledge.setName("java");
        knowledge.setFreelancer(freelancer);
        knowledgeList.add(knowledge);

        Long id1 = 3L;
        Knowledge knowledge1 = new Knowledge();
        knowledge1.setId(3L);
        knowledge1.setName("mysql");
        knowledge1.setFreelancer(freelancer);
        knowledgeList.add(knowledge1);

        when(knowledgeRepository.findByFreelancerId(idFreelancer)).thenReturn(knowledgeList);

        //Act
        List<Knowledge> foundKnowledges = knowledgeService.getAllKnowledgeByFreelancerId(idFreelancer);

        //Assert
        assertThat(foundKnowledges.get(0).getFreelancer()).isEqualTo(freelancer);
        assertThat(foundKnowledges.size()).isEqualTo(2);
    }
}