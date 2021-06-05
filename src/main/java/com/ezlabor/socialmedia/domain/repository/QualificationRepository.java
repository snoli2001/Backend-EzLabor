package com.ezlabor.socialmedia.domain.repository;

import com.ezlabor.socialmedia.domain.model.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long> {
    List<Qualification> findBySolutionId(Long solutionId);
    Optional<Qualification> findByIdAndSolutionId (Long qualificationId, Long solutionId);
}
