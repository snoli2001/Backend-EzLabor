package com.ezlabor.hiring.domain.service;

import com.ezlabor.hiring.domain.model.Requirement;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface RequirementService {
    List<Requirement> getAllRequirementsByOfferId(Long offerId);
    Requirement getRequirementByIdAndOfferId(Long offerId, Long id);
    Requirement createRequirement(Long offerId, Requirement requirement);
    Requirement updateRequirement(Long offerId, Long id, Requirement requirementDetails);
    ResponseEntity<?> deleteRequirement(Long offerId, Long id);
}
