package com.ezlabor.hiring.domain.service;

import com.ezlabor.hiring.domain.model.Offer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface OfferService {
    List<Offer> getAllOffersByEmployerId(Long employerId);
    List<Offer> getAllOffers();
    List<Offer> getAllOffersBySpecialities(List<Long> specialitiesId);
    Offer activateOffer(Long employerId, Long id);
    Offer deactivateOffer(Long employerId, Long id);
    Offer getOfferByIdAndEmployerId(Long employerId, Long id);
    Offer CreateOffer(Long employerId, Offer offer, Long specialtyId);
    Offer updateOffer(Long employerId, Long id, Offer offerDetails);
    ResponseEntity<?> deleteOffer(Long offerId, Long employerId);
}
