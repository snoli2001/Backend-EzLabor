package com.ezlabor.hiring.controller;

import com.ezlabor.hiring.domain.model.Offer;
import com.ezlabor.hiring.domain.model.Specialty;
import com.ezlabor.hiring.domain.service.OfferService;
import com.ezlabor.hiring.resource.Offer.OfferResource;
import com.ezlabor.hiring.resource.Offer.OfferSpecialtiesResource;
import com.ezlabor.hiring.resource.Offer.SaveOfferResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offers")
public class OffersSpecialtiesController {

    private final OfferService offerService;
    private final ModelMapper mapper;

    @Autowired
    public OffersSpecialtiesController(OfferService offerService, ModelMapper mapper) {
        this.offerService = offerService;
        this.mapper = mapper;
    }

    @GetMapping("/specialties")
    public List<OfferResource> getAllOffers(@RequestBody OfferSpecialtiesResource specialties){
        List<Offer> offers = offerService.getAllOffersBySpecialities(specialties.getSpecialtiesId());
        return offers.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    private OfferResource convertToResource(Offer entity){
        return mapper.map(entity, OfferResource.class);
    }

}
