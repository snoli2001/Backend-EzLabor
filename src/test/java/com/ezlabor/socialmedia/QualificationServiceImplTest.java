package com.ezlabor.socialmedia;

import com.ezlabor.socialmedia.domain.model.Qualification;
import com.ezlabor.socialmedia.domain.repository.QualificationRepository;
import com.ezlabor.socialmedia.domain.service.QualificationService;
import com.ezlabor.socialmedia.service.QualificationServiceImpl;
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
public class QualificationServiceImplTest {
    @MockBean
    private QualificationRepository qualificationRepository;
    @Autowired
    private QualificationService qualificationService;

    @TestConfiguration
    static class QualificationServiceImplTestConfiguration{
        @Bean
        public QualificationService qualificationService(){
            return new QualificationServiceImpl();
        }
    }
    @Test
    public void whenGetQualificationByIdWithValidIdThenReturnsQualification(){
        // Arrange
        Long id = 1L;
        Qualification qualification = new Qualification();
        qualification.setId(1L);
        qualification.setComment("Cumplió a tiempo");
        qualification.setStars(5)
        System.out.println();
        when(qualificationRepository.findById(id))
                .thenReturn(Optional.of(qualification));
        // Act
        Optional<Qualification> foundQualification = QualificationService.getQualificationById(id);
        // Assert
        assertThat(foundQualification.get().getId()).isEqualTo(id);
    }

    @Test
    public void whenGetQualificationByIdWithInValidIdThenReturnsNull(){
        // Arrange
        Long id = 2L;
        when(qualificationRepository.findById(id))
                .thenReturn(null);
        // Act
        Optional<Qualification> foundQualification = QualificationService.getQualificationById(id);
        // Assert
        assertThat(foundQualification).isEqualTo(null);
    }

    @Test
    public void whenGetQualificationBySolutionIdWithValidSolutionIdThenReturnsQualification(){
        // Arrange
        Long solutionId = 1L;
        Solution solution = new Solution();
        solution.setId(1L);


}
