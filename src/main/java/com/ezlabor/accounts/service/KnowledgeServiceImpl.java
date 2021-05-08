package com.ezlabor.accounts.service;

import com.ezlabor.accounts.domain.model.background.Knowledge;
import com.ezlabor.accounts.domain.repository.FreelancerRepository;
import com.ezlabor.accounts.domain.repository.KnowledgeRepository;
import com.ezlabor.accounts.domain.service.KnowledgeService;
import com.ezlabor.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeRepository knowledgeRepository;
    @Autowired
    private FreelancerRepository freelancerRepository;

    @Override
    public List<Knowledge> getAllKnowledgeByFreelancerId(Long freelancerId) {
        return knowledgeRepository.findByFreelancerId(freelancerId);
    }

    @Override
    public Knowledge getKnowledgeByIdAndFreelancerId(Long knowledgeId, Long freelancerId) {
        return knowledgeRepository.findByIdAndFreelancerId(knowledgeId, freelancerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Knowledge not found with Id" + knowledgeId +
                                "and FreelancerId" + freelancerId));
    }

    @Override
    public Knowledge createKnowledge(Long freelancerId, Knowledge knowledge) {
        return freelancerRepository.findById(freelancerId).map(freelancer -> {
            knowledge.setFreelancer(freelancer);
            return knowledgeRepository.save(knowledge);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer", "Id", freelancerId));
    }

    @Override
    public Knowledge updateKnowledge(Long freelancerId, Long knowledgeId, Knowledge knowledgeDetails) {
       if(freelancerRepository.existsById(freelancerId)) {
           throw new ResourceNotFoundException(
                   "Freelancer", "Id", freelancerId
           );
       }
       return knowledgeRepository.findById(knowledgeId)
                   .map(knowledge ->{
                       knowledge.setName(knowledgeDetails.getName());
                       knowledge.setDescription(knowledgeDetails.getDescription());
                       knowledge.setLevel(knowledgeDetails.getLevel());
                       return knowledgeRepository.save(knowledge);
                   } )
                   .orElseThrow(() -> new ResourceNotFoundException(
                           "Knowledge", "Id", knowledgeId
                   ));

    }

    @Override
    public ResponseEntity<?> deleteKnowledge(Long freelancerId, Long knowledgeId) {
        if(freelancerRepository.existsById(freelancerId)) {
            throw new ResourceNotFoundException(
                    "Freelancer", "Id", freelancerId
            );
        }
        return knowledgeRepository.findById(knowledgeId)
                .map(knowledge ->{
                    knowledgeRepository.delete(knowledge);
                    return ResponseEntity.ok().build();
                } )
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Knowledge", "Id", knowledgeId
        ));
    }
}
