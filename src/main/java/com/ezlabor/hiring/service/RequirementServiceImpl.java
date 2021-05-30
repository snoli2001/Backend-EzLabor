package com.ezlabor.hiring.service;

import com.ezlabor.common.exception.ResourceNotFoundException;
import com.ezlabor.hiring.domain.model.Requirement;
import com.ezlabor.hiring.domain.repository.OfferRepository;
import com.ezlabor.hiring.domain.repository.RequirementRepository;
import com.ezlabor.hiring.domain.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RequirementServiceImpl implements RequirementService {

    @Autowired
    private RequirementRepository requirementRepository;
    @Autowired
    private OfferRepository offerRepository;
    @Override
    public List<Requirement> getAllRequirementsByOfferId(Long offerId) {
        return requirementRepository.findAllByOfferId(offerId);
    }

    @Override
    public Optional<Requirement> getRequirementByIdAndOfferId(Long offerId, Long id) {
        return requirementRepository.findByIdAndOfferId(offerId,id);
    }

    @Override
    public Requirement createRequirement(Long offerId, Requirement requirement) {
        return offerRepository.findById(offerId).map(offer -> {
            requirement.setOffer(offer);
            return requirementRepository.save(requirement);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Offer", "Id", offerId
        ));
    }

    @Override
    public Requirement updateRequirement(Long offerId, Long id, Requirement requirementDetails) {
        if(!offerRepository.existsById(id))
            throw new ResourceNotFoundException(
                    "Offer","Id", offerId
            );

        return requirementRepository.findById(id).map(requirement -> {
            requirement.setName(requirementDetails.getName());
            requirement.setLevel(requirementDetails.getLevel());
            return requirementRepository.save(requirement);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Requirement","Id", id
        ));
    }

    @Override
    public ResponseEntity<?> deleteRequirement(Long offerId, Long id) {
        if(!offerRepository.existsById(offerId))
            throw new ResourceNotFoundException(
                    "Offer","Id", offerId
            );
        return requirementRepository.findById(id).map(requirement -> {
            requirementRepository.delete(requirement);
            return ResponseEntity.ok().build();
        }) .orElseThrow(() -> new ResourceNotFoundException(
                "Requirement","Id", id
        ));
    }
}
