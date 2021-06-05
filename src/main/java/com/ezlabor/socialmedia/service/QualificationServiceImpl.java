package com.ezlabor.socialmedia.service;

import com.ezlabor.common.exception.ResourceNotFoundException;
import com.ezlabor.socialmedia.domain.model.Qualification;
import com.ezlabor.socialmedia.domain.repository.QualificationRepository;
import com.ezlabor.socialmedia.domain.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualificationServiceImpl implements QualificationService {
    @Autowired
    private QualificationRepository qualificationRepository;
    @Autowired
    private SolutionRepository solutionRepository;

    @Override
    public List<Qualification> getAllQualificationsBySolutionId(Long solutionId) {
        return qualificationRepository.findBySolutionId(solutionId);
    }
    @Override
    public Qualification createQualification(Long solutionId, Qualification qualification) {
        return solutionRepository.findById(solutionId).map(service -> {
            qualification.setService(solution);
            return qualificationRepository.save(qualification);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Solution", "Id", solutionId));
    }

    @Override
    public Qualification updateQualification(Long solutionId, Long qualificationId, Qualification qualificationDetails) {
        if(!solutionRepository.existsById(solutionId)) {
            throw new ResourceNotFoundException(
                    "Solution", "Id", solutionId
            );
        }
        return qualificationRepository.findById(qualificationId)
                .map(qualification ->{
                    qualification.setComment(qualificationDetails.getComment());
                    qualification.setStars(qualificationDetails.getStars());
                    return qualificationRepository.save(qualification);
                } )
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Qualification", "Id", qualificationId
                ));

    }

    @Override
    public ResponseEntity<?> deleteQuallification(Long solutionId, Long qualificationId) {
        if(!solutionRepository.existsById(solutionId)) {
            throw new ResourceNotFoundException(
                    "Solution", "Id", solutionId
            );
        }
        return qualificationRepository.findById(qualificationId)
                .map(qualification ->{
                    qualificationRepository.delete(qualification);
                    return ResponseEntity.ok().build();
                } )
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Qualification", "Id", qualificationId
                ));
    }

}
