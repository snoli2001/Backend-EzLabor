package com.ezlabor.accounts.controller;

import com.ezlabor.accounts.domain.model.Publication;
import com.ezlabor.accounts.domain.service.PublicationService;
import com.ezlabor.accounts.resource.PublicationResource;
import com.ezlabor.accounts.resource.SavePublicationResource;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/users/{userId}/publications")
    public List<PublicationResource> getAllPublicationsByUserId(@PathVariable Long userId) {
        return publicationService.getAllPublicationsByUserId(userId).stream().map(this::convertToResource).collect(Collectors.toList());

    }

    @GetMapping("/users/{userId}/publications/{publicationId}")
    public PublicationResource getPublicationByIdAndUserId(@PathVariable Long userId, @PathVariable Long publicationId) {
        return convertToResource(publicationService.getPublicationByIdAndUserId(publicationId, userId));
    }

    @PostMapping("/users/{userId}/publications")
    public PublicationResource createPublication(@PathVariable Long userId, @Valid @RequestBody SavePublicationResource resource) {
        return convertToResource(publicationService.createPublication(userId, convertToEntity(resource)));
    }

    @PutMapping("/users/{userId}/publications/{publicationId}")
    public PublicationResource updatePublication(@PathVariable Long userId,
                                                 @PathVariable Long publicationId,
                                                 @Valid @RequestBody SavePublicationResource resource) {
        return convertToResource(publicationService.updatePublication(userId, publicationId, convertToEntity(resource)));
    }

    @DeleteMapping("/users/{userId}/publications/{publicationId}")
    public ResponseEntity<?> deletePublication(@PathVariable Long userId, @PathVariable Long publicationId) {
        return publicationService.deletePublication(userId, publicationId);
    }

    private Publication convertToEntity(SavePublicationResource resource) {
        return mapper.map(resource, Publication.class);
    }

    private PublicationResource convertToResource(Publication entity) {
        return mapper.map(entity, PublicationResource.class);
    }
}
