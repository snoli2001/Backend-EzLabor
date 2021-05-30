package com.ezlabor.hiring.domain.service;

import com.ezlabor.hiring.domain.model.Offer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface OfferService {
    List<Offer> getAllOffersByEmployerId(Long employerId);
    List<Offer> getAllOffers();
    Optional<Offer> getOfferByIdAndEmployerId(Long employerId, Long id);
    Offer CreateOffer(Long employerId, Offer offer);
    Offer updateOffer(Long employerId, Long id, Offer offerDetails);
    ResponseEntity<?> deleteOffer(Long offerId, Long employerId);
}