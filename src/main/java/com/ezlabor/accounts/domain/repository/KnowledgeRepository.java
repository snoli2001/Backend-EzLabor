package com.ezlabor.accounts.domain.repository;
import com.ezlabor.accounts.domain.model.background.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KnowledgeRepository extends JpaRepository<Knowledge, Long> {

    List<Knowledge> findByFreelancerId(Long freelancerId);
    Optional<Knowledge> findByIdAndFreelancerId (Long knowledgeId, Long freelancerId);
}
