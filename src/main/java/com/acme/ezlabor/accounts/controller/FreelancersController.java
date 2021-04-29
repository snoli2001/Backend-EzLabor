package com.acme.ezlabor.accounts.controller;
import org.modelmapper.ModelMapper;
import com.acme.ezlabor.accounts.domain.model.Freelancer;
import com.acme.ezlabor.accounts.domain.service.FreelancerService;
import com.acme.ezlabor.accounts.resource.freelancer.FreelancerResource;
import com.acme.ezlabor.accounts.resource.freelancer.SaveFreelancerResource;
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
public class FreelancersController {
    private final FreelancerService freelancerService;
    private final ModelMapper mapper;

    @Autowired
    public FreelancersController(FreelancerService freelancerService, ModelMapper mapper) {
        this.freelancerService = freelancerService;
        this.mapper = mapper;
    }


    @GetMapping("/freelancers")
    public Page<FreelancerResource> getAllFreelancers(Pageable pageable,
                                                      @RequestParam(required = false) String name,
                                                      @RequestParam(required = false) String lastname,
                                                      @RequestParam(required = false) String profession){
        Page<Freelancer> freelancerPage = freelancerService.getAllFreelancers(pageable);
        List<FreelancerResource> resources = freelancerPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/freelancers")
    public FreelancerResource createFreelancer(@Valid @RequestBody SaveFreelancerResource resource){
        Freelancer freelancer = convertToEntity(resource);
        freelancer.setCreatedAt(new Date());
        return convertToResource(freelancerService.createFreelancer(freelancer));
    }

    @PutMapping("/freelancers/{freelancerId}")
    public FreelancerResource updateFreelancer(@PathVariable Long freelancerId, @Valid @RequestBody SaveFreelancerResource resource){
        Freelancer freelancer = convertToEntity(resource);
        freelancer.setUpdatedAt(new Date());
        return convertToResource(freelancerService.updateFreelancer(freelancerId, freelancer));
    }

    @DeleteMapping("/freelancers/{freelancerId}")
    public ResponseEntity<?> deleteFreelancer(@PathVariable Long freelancerId){
        return freelancerService.deleteFreelancer(freelancerId);
    }

    private Freelancer convertToEntity(SaveFreelancerResource resource){
        return mapper.map(resource, Freelancer.class);
    }

    private FreelancerResource convertToResource(Freelancer entity){
        return mapper.map(entity, FreelancerResource.class);
    }


}
