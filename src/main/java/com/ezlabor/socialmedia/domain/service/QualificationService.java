package com.ezlabor.socialmedia.domain.service;

import com.ezlabor.accounts.domain.model.background.Knowledge;
import com.ezlabor.socialmedia.domain.model.Qualification;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface QualificationService {
    List<Knowledge> getAllQualificationBySolutionId(Long solutionId);
    Qualification createQualification(Qualification qualification);
    Qualification updateQualification(Long qualificationId, Qualification qualificationDetails);
    ResponseEntity<?> deleteQualification(Long qualificationId);

    Optional<Qualification> getQualificationBySolutionId(Long solutionId);
    Optional<Qualification> getQualificationById(Long qualificationId);
}
