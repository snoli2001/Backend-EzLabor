package com.ezlabor.accounts;

import com.ezlabor.accounts.domain.model.Employer;
import com.ezlabor.accounts.domain.repository.EmployerRepository;
import com.ezlabor.accounts.domain.service.EmployerService;
import com.ezlabor.accounts.service.EmployerServiceImpl;
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
public class EmployerServiceImplTest {
    @MockBean
    private EmployerRepository employerRepository;
    @Autowired
    private EmployerService employerService;

    @TestConfiguration
    static class FreelancerServiceImplTestConfiguration{
        @Bean
        public EmployerService employerService(){
            return new EmployerServiceImpl();
        }
    }
    @Test
    public void whenGetEmployerByIdWithValidIdThenReturnsEmployer(){
        // Arrange
        Long id = 1L;
        Employer employer = new Employer();
        employer.setId(1L);
        employer.setFirstname("Juan");
        System.out.println();
        when(employerRepository.findById(id))
                .thenReturn(Optional.of(employer));
        // Act
        Optional<Employer> foundEmployer = employerService.getEmployerById(id);
        // Assert
        assertThat(foundEmployer.get().getId()).isEqualTo(id);
    }

    @Test
    public void whenGetEmployerByIdWithInValidIdThenReturnsNull(){
        // Arrange
        Long id = 2L;
        when(employerRepository.findById(id))
                .thenReturn(null);
        // Act
        Optional<Employer> foundEmployer = employerService.getEmployerById(id);
        // Assert
        assertThat(foundEmployer).isEqualTo(null);
    }
}
