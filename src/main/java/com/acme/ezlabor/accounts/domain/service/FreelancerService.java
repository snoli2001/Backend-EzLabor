package com.acme.ezlabor.accounts.domain.service;

import com.acme.ezlabor.accounts.domain.model.Freelancer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FreelancerService{
    Page<Freelancer> getAllFreelancers(Pageable pageable);
    Freelancer createFreelancer(Freelancer freelancer);
    Freelancer updateFreelancer(Long freelancerId, Freelancer freelancerDetails);
    ResponseEntity<?> deleteFreelancer(Long freelancerId);

    Freelancer getFreelancerById(Long freelancerId);
    Page<Freelancer> getFreelancersByProfession(String profession);
    Page<Freelancer> getFreelancersByFirstname(String firstName);
    Page<Freelancer> getFreelancersByLastname(String lastName);
}
