package com.acme.ezlabor.accounts.controller;

import com.acme.ezlabor.accounts.domain.model.Employer;
import com.acme.ezlabor.accounts.domain.service.EmployerService;
import com.acme.ezlabor.accounts.resource.employer.EmployerResource;
import com.acme.ezlabor.accounts.resource.employer.SaveEmployerResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployersController {
    private final EmployerService employerService;
    private final ModelMapper mapper;

    @Autowired
    public EmployersController(EmployerService employerService, ModelMapper mapper) {
        this.employerService = employerService;
        this.mapper = mapper;
    }


    @GetMapping("/employers")
    public Page<EmployerResource> getAllEmployers(Pageable pageable,
                                                  @RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String lastname){

        Page<Employer> employerPage = employerService.getAllEmployers(pageable);
        List<EmployerResource> resources = employerPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @GetMapping("/employers/{employerId}/")
    public EmployerResource getEmployerById(@PathVariable Long employerId){
        return convertToResource(employerService.getEmployerById(employerId));
    }

    @PostMapping("/employers")
    public EmployerResource createEmployer(@Valid @RequestBody SaveEmployerResource resource){
        Employer employer = convertToEntity(resource);
        employer.setCreatedAt(new Date());
        return convertToResource(employerService.createEmployer(employer));
    }

    @PutMapping("/employers/{employerId}")
    public EmployerResource updateEmployer(@PathVariable Long employerId, @Valid @RequestBody SaveEmployerResource resource){
        Employer employer = convertToEntity(resource);
        employer.setUpdatedAt(new Date());
        return convertToResource(employerService.updateEmployer(employerId, employer));
    }

    @DeleteMapping("/employers/{employerId}")
    public ResponseEntity<?> deleteEmployer(@PathVariable Long employerId){
        return employerService.deleteEmployer(employerId);
    }

    private Employer convertToEntity(SaveEmployerResource resource){
        return mapper.map(resource, Employer.class);
    }

    private EmployerResource convertToResource(Employer entity){
        return mapper.map(entity, EmployerResource.class);
    }




}
