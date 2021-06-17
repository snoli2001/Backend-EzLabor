package com.ezlabor.hiring.controller;


import com.ezlabor.hiring.domain.model.Offer;
import com.ezlabor.hiring.domain.model.Specialty;
import com.ezlabor.hiring.domain.repository.SpecialtyRepository;
import com.ezlabor.hiring.resource.Offer.OfferResource;
import com.ezlabor.hiring.resource.Offer.OfferSpecialtiesResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/specialties")
public class SpecialtiesController {
    @Autowired
    private SpecialtyRepository specialtyRepository;

    @GetMapping()
    public List<Specialty> getAllOffers(){
        return specialtyRepository.findAll();
    }
}
