package com.acme.ezlabor.accounts.service;

import com.acme.ezlabor.accounts.domain.model.Employer;
import com.acme.ezlabor.accounts.domain.repository.EmployerRepository;
import com.acme.ezlabor.accounts.domain.service.EmployerService;
import com.acme.ezlabor.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }


    @Override
    public Page<Employer> getAllEmployers(Pageable pageable) {
        return employerRepository.findAll(pageable);
    }

    @Override
    public Employer createEmployer(Employer employer) {
        if(employerRepository.findByUsername(employer.getUsername()) != null)
            return employerRepository.findByUsername(employer.getUsername());
        return employerRepository.save(employer);
    }

    @Override
    public Employer updateEmployer(Long employerId, Employer employerDetails) {
        return employerRepository.findById(employerId).map(employer -> {
            employer.setFirstname(employerDetails.getFirstname());
            employer.setLastname(employerDetails.getFirstname());
            employer.setCompanyName(employerDetails.getCompanyName());
            employer.setDescription(employerDetails.getDescription());
            employer.setPersonalPhone(employerDetails.getPersonalPhone());
            employer.setContactCompanyEmail(employerDetails.getContactCompanyEmail());
            employer.setWebPage(employerDetails.getWebPage());
            employer.setFacebookLink(employerDetails.getFacebookLink());
            employer.setInstagramLink(employerDetails.getInstagramLink());
            employer.setTwitterLink(employerDetails.getTwitterLink());
            employer.setImageUrl(employerDetails.getImageUrl());
            return employerRepository.save(employer);
        }).orElseThrow(() -> new ResourceNotFoundException("Employer", "Id", employerId));
    }

    @Override
    public ResponseEntity<?> deleteEmployer(Long employerId) {
        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(() -> new ResourceNotFoundException("Employer", "Id", employerId));
        employerRepository.delete(employer);
        return ResponseEntity.ok().build();
    }

    @Override
    public Employer getEmployerById(Long employerId) {
        return null;
    }

    @Override
    public Page<Employer> getEmployerByCompanyName(String companyName) {
        return null;
    }

    @Override
    public Page<Employer> getEmployersByFirstname(String firstName) {
        return null;
    }

    @Override
    public Page<Employer> getEmployersByLastname(String lastName) {
        return null;
    }
}
