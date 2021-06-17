package com.ezlabor.accounts.service;

import com.ezlabor.accounts.domain.model.Freelancer;
import com.ezlabor.accounts.domain.repository.FreelancerRepository;
import com.ezlabor.accounts.domain.service.FreelancerService;
import com.ezlabor.common.AccountType;
import com.ezlabor.common.exception.ResourceNotFoundException;
import com.ezlabor.locations.domain.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FreelancerServiceImpl implements FreelancerService {
    @Autowired
    private FreelancerRepository freelancerRepository;
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<Freelancer> getAllFreelancers() {
        return freelancerRepository.findAll();
    }

    @Override
    public Freelancer createFreelancer(Freelancer freelancer, Long districtId) {
        if(freelancerRepository.findByUsername(freelancer.getUsername())!=null)
            return freelancerRepository.findByUsername(freelancer.getUsername());
        return this.districtRepository.findById(districtId).map(
                district -> {
                    freelancer.setDistrict(district);
                    freelancer.setAccountType(AccountType.FREELANCER);
                    freelancer.setPassword(encoder.encode(freelancer.getPassword()));
                    return freelancerRepository.save(freelancer);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("District", "Id", districtId));
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
    public Optional<Freelancer> getFreelancerById(Long freelancerId) {
        return freelancerRepository.findById(freelancerId);
    }

    @Override
    public List<Freelancer> getFreelancersByProfession(String profession) {
        return freelancerRepository.findAllByProfession(profession);
    }

    @Override
    public List<Freelancer> getFreelancersByFirstname(String firstName) {
        return freelancerRepository.findAllByFirstname(firstName);
    }

    @Override
    public List<Freelancer> getFreelancersByLastname(String lastName) {
        return freelancerRepository.findAllByLastname(lastName);
    }
}
