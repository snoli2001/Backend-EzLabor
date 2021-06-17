package com.ezlabor.hiring.service;

import com.ezlabor.accounts.domain.repository.EmployerRepository;
import com.ezlabor.common.exception.ResourceNotFoundException;
import com.ezlabor.hiring.domain.model.Offer;
import com.ezlabor.hiring.domain.model.Postulation;
import com.ezlabor.hiring.domain.repository.OfferRepository;
import com.ezlabor.hiring.domain.repository.SpecialtyRepository;
import com.ezlabor.hiring.domain.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public List<Offer> getAllOffersByEmployerId(Long employerId) {
        return offerRepository.findAllByEmployerId(employerId);
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public List<Offer> getAllOffersBySpecialities(List<Long> specialitiesId) {
        List<Offer> offerList = new ArrayList<>();
        specialitiesId.forEach(id -> offerList.addAll(offerRepository.findAllBySpecialtyId(id)));
        return offerList;
    }

    @Override
    public Offer getOfferByIdAndEmployerId(Long employerId, Long id) {
        if(!employerRepository.existsById(employerId))
            throw new ResourceNotFoundException(
                    "Employer","Id", employerId
            );
        if(!offerRepository.existsById(id))
            throw new ResourceNotFoundException(
                    "Offer","Id", employerId
            );
        return offerRepository.findByIdAndEmployerId(employerId,id).orElseThrow(() -> new ResourceNotFoundException(
                "Offer","Id", id
        ));
    }

    @Override
    public Offer CreateOffer(Long employerId, Offer offer, Long specialtyId) {
        return employerRepository.findById(employerId).map(employer -> {
            offer.setEmployer(employer);
            offer.setSpecialty(specialtyRepository.findById(specialtyId).orElseThrow(() -> new ResourceNotFoundException(
                    "Specialty", "Id", specialtyId
            )));
            return offerRepository.save(offer);
        }) .orElseThrow(() -> new ResourceNotFoundException(
                "Employer", "Id", employerId
        ));
    }

    @Override
    public Offer updateOffer(Long employerId, Long id, Offer offerDetails) {
        if(!employerRepository.existsById(employerId))
            throw new ResourceNotFoundException(
                    "Employer","Id", employerId
            );

        return offerRepository.findById(id).map(offer -> {
            offer.setDescription(offerDetails.getDescription());
            offer.setTitle(offerDetails.getTitle());
            offer.setStartDate(offerDetails.getStartDate());
            offer.setEndDate(offerDetails.getEndDate());
            offer.setPaymentAmount(offerDetails.getPaymentAmount());
            offer.setRequirements(offerDetails.getRequirements());
            offer.setMonthDuration(offerDetails.getMonthDuration());
            return offerRepository.save(offer);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Offer","Id", id
        ));
    }


    @Override
    public Offer activateOffer(Long employerId, Long id) {
        if(!employerRepository.existsById(employerId))
            throw new ResourceNotFoundException(
                    "Employer","Id", employerId
            );
        return offerRepository.findById(id).map(offer -> {
            offer.setActive(true);
            return offerRepository.save(offer);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Offer","Id", id
        ));
    }

    @Override
    public Offer deactivateOffer(Long employerId, Long id) {
        if(!employerRepository.existsById(employerId))
            throw new ResourceNotFoundException(
                    "Employer","Id", employerId
            );
        return offerRepository.findById(id).map(offer -> {
            offer.setActive(false);
            return offerRepository.save(offer);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Offer","Id", id
        ));
    }

    @Override
    public ResponseEntity<?> deleteOffer(Long offerId, Long employerId) {
        if(!employerRepository.existsById(employerId))
            throw new ResourceNotFoundException(
                    "Employer","Id", employerId
            );
        return offerRepository.findById(offerId).map(offer -> {
            offer.setActive(false);
            offer.getPostulations().stream().peek(postulation -> {
                if(postulation.getState() == Postulation.PostState.UNANSWERED){
                    postulation.setState(Postulation.PostState.REJECTED);
                }
            });
            offerRepository.save(offer);
            return ResponseEntity.ok().build();
        }) .orElseThrow(() -> new ResourceNotFoundException(
                "Offer","Id", offerId
        ));
    }
}
