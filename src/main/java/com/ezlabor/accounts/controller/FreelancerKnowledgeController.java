package com.ezlabor.accounts.controller;

import com.ezlabor.accounts.domain.model.background.Knowledge;
import com.ezlabor.accounts.domain.service.FreelancerService;
import com.ezlabor.accounts.domain.service.KnowledgeService;
import com.ezlabor.accounts.resource.knowledge.KnowledgeResource;
import com.ezlabor.accounts.resource.knowledge.SaveKnowledgeResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FreelancerKnowledgeController {
    @Autowired
    private FreelancerService freelancerService;

    @Autowired
    private KnowledgeService knowledgeService;
    @Autowired
    private ModelMapper mapper;


    @GetMapping("/freelancers/{freelancerId}/knowledges")
    public List<KnowledgeResource> getKnowledgeResources(@PathVariable Long freelancerId){
        List<Knowledge> knowledgeList = knowledgeService.getAllKnowledgeByFreelancerId(freelancerId);
        return knowledgeList.stream().map(knowledge -> convertToResource(knowledge)).collect(Collectors.toList());
    }

    @PostMapping("/freelancers/{freelancerId}/knowledges")
    public KnowledgeResource createKnowledge(@PathVariable Long freelancerId,@Valid @RequestBody SaveKnowledgeResource resource){

        Knowledge knowledge = convertToEntity(resource);
        return convertToResource(knowledgeService.createKnowledge(freelancerId, knowledge));
    }

    @PutMapping("/freelancers/{freelancerId}/knowledges/{knowledgeId}")
    public KnowledgeResource updateKnowledge(@PathVariable Long freelancerId, @PathVariable Long knowledgeId, @Valid @RequestBody SaveKnowledgeResource resource){
        Knowledge knowledge = convertToEntity(resource);

        return convertToResource(knowledgeService.updateKnowledge(freelancerId, knowledgeId, knowledge));
    }


    @DeleteMapping("/freelancers/{freelancerId}/knowledges/{knowledgeId}")
    public ResponseEntity<?> deleteKnowledge(@PathVariable Long freelancerId, @PathVariable Long knowledgeId){
        return knowledgeService.deleteKnowledge(freelancerId, knowledgeId);
    }




    private KnowledgeResource convertToResource(Knowledge knowledge){

        return mapper.map(knowledge, KnowledgeResource.class);
    }



    private Knowledge convertToEntity(SaveKnowledgeResource resource){

        return mapper.map(resource, Knowledge.class);
    }



}


