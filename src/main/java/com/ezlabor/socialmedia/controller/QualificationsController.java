package com.ezlabor.socialmedia.controller;

import com.ezlabor.accounts.resource.knowledge.KnowledgeResource;
import com.ezlabor.socialmedia.domain.model.Qualification;
import com.ezlabor.socialmedia.domain.service.QualificationService;
import com.ezlabor.socialmedia.resource.qualification.QualificationResource;
import com.ezlabor.socialmedia.resource.qualification.SaveQualificationResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class QualificationsController {
    @Autowired
    private QualificationService qualificationService;
     @Autowired
    private ModelMapper mapper;

    @Operation(tags = "qualifications")
    @PostMapping("/solutions/{solutionId}/qualifications")
    public KnowledgeResource createQualification(@PathVariable Long solutionId, @Valid @RequestBody SaveQualificationResource resource){

        Qualification qualification = convertToEntity(resource);
        return convertToResource(qualificationService.createQualification(solutionId, qualificationDetails));
    }

    @Operation(tags = "qualifications")
    @PutMapping("/solutions/{solutionId}/qualifications/{qualificationId}")
    public QualificationResource updateQualification(@PathVariable Long solutionId, @PathVariable Long qualificationId, @Valid @RequestBody SaveQualificationResource resource){
        Qualification qualification = convertToEntity(resource);

        return convertToResource(qualificationService.updateQualification(solutionId, qualificationId, qualificationDetails));
    }

    @Operation(tags = "qualifications")
    @DeleteMapping("/solutions/{solutionId}/qualifications/{qualificationId}")
    public ResponseEntity<?> deleteQualification(@PathVariable Long solutionId, @PathVariable Long qualificationId){
        return qualificationService.deleteQualification(solutionId, qualificationId);
    }




    private QualificationResource convertToResource(Qualification qualification){

        return mapper.map(qualification, QualificationResource.class);
    }



    private Qualification convertToEntity(SaveQualificationResource resource){

        return mapper.map(resource, Qualification.class);
    }


}
