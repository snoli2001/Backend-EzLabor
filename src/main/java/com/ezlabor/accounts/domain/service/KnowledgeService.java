package com.ezlabor.accounts.domain.service;

import com.ezlabor.accounts.domain.model.background.Knowledge;
import com.ezlabor.accounts.resource.knowledge.SaveKnowledgeResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface KnowledgeService {
    List<Knowledge> getAllKnowledgeByFreelancerId(Long freelancerId);
    Knowledge getKnowledgeByIdAndFreelancerId(Long knowledgeId, Long freelancerId);
    Knowledge createKnowledge(Long freelancerId, Knowledge knowledge);
    Knowledge updateKnowledge(Long freelancerId, Long knowledgeId, Knowledge knowledge);
    ResponseEntity<?> deleteKnowledge(Long freelancerId, Long knowledgeId);
}
