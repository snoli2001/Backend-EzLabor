package com.acme.ezlabor.accounts.service;

import com.acme.ezlabor.accounts.domain.model.Freelancer;
import com.acme.ezlabor.accounts.domain.repository.FreelancerRepository;
import com.acme.ezlabor.accounts.domain.service.FreelancerService;
import com.acme.ezlabor.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FreelancerServiceImpl implements FreelancerService {

    private final FreelancerRepository freelancerRepository;

    @Autowired
    public FreelancerServiceImpl(FreelancerRepository freelancerRepository) {
        this.freelancerRepository = freelancerRepository;
    }


    @Override
    public Page<Freelancer> getAllFreelancers(Pageable pageable) {
        return freelancerRepository.findAll(pageable);
    }

    @Override
    public Freelancer createFreelancer(Freelancer freelancer) {
        if(freelancerRepository.findByUsername(freelancer.getUsername())!=null)
            return freelancerRepository.findByUsername(freelancer.getUsername());
        return freelancerRepository.save(freelancer);
    }

    @Override
    public Freelancer updateFreelancer(Long freelancerId, Freelancer freelancerDetails) {
        return freelancerRepository.findById(freelancerId).map(freelancer -> {
            freelancer.setFirstname(freelancerDetails.getFirstname());
            freelancer.setLastname(freelancerDetails.getLastname());
            freelancer.setPhone(freelancerDetails.getPhone());
            freelancer.setDescription(freelancerDetails.getDescription());
            freelancer.setProfession(freelancerDetails.getProfession());
            freelancer.setWebPage(freelancerDetails.getWebPage());
            freelancer.setFacebookLink(freelancerDetails.getFacebookLink());
            freelancer.setInstagramLink(freelancerDetails.getInstagramLink());
            freelancer.setTwitterLink(freelancerDetails.getTwitterLink());
            freelancer.setImageUrl(freelancerDetails.getImageUrl());
            return freelancerRepository.save(freelancer);

        }).orElseThrow(() -> new ResourceNotFoundException("Freelancer", "Id", freelancerId));
    }

    @Override
    public ResponseEntity<?> deleteFreelancer(Long freelancerId) {
        Freelancer freelancer = freelancerRepository.findById(freelancerId)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer", "Id", freelancerId));
        freelancerRepository.delete(freelancer);
        return ResponseEntity.ok().build();
    }

    @Override
    public Freelancer getFreelancerById(Long freelancerId) {
        return null;
    }

    @Override
    public Page<Freelancer> getFreelancersByProfession(String profession) {
        return null;
    }

    @Override
    public Page<Freelancer> getFreelancersByFirstname(String firstName) {
        return null;
    }

    @Override
    public Page<Freelancer> getFreelancersByLastname(String lastName) {
        return null;
    }
}
