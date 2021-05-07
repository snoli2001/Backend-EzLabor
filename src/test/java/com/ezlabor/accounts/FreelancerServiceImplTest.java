package com.ezlabor.accounts;

import com.ezlabor.accounts.domain.model.Freelancer;
import com.ezlabor.accounts.domain.repository.FreelancerRepository;
import com.ezlabor.accounts.domain.service.FreelancerService;
import com.ezlabor.accounts.service.FreelancerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class FreelancerServiceImplTest {
    @MockBean
    private FreelancerRepository freelancerRepository;
    @Autowired
    private FreelancerService freelancerService;

    @TestConfiguration
    static class FreelancerServiceImplTestConfiguration{
        @Bean
        public FreelancerService freelancerService(){
            return new FreelancerServiceImpl();
        }
    }
    @Test
    public void whenGetFreelancerByIdWithValidIdThenReturnsFreelancer(){
        // Arrange
        Long id = 1L;
        Freelancer freelancer = new Freelancer();
        freelancer.setId(1L);
        freelancer.setFirstname("Juan");
        System.out.println(freelancer);
        when(freelancerRepository.findById(id))
                .thenReturn(Optional.of(freelancer));
        // Act
        Optional<Freelancer> foundFreelancer = freelancerService.getFreelancerById(id);
        // Assert
        assertThat(foundFreelancer.get().getId()).isEqualTo(id);
    }

    @Test
    public void whenGetFreelancerByIdWithInvalidIdThenReturnsNull(){
        // Arrange
        Long id = 2L;
        when(freelancerRepository.findById(id))
                .thenReturn(null);
        // Act
        Optional<Freelancer> foundFreelancer = freelancerService.getFreelancerById(id);
        // Assert
        assertThat(foundFreelancer).isEqualTo(null);
    }
}
