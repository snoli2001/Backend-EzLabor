package com.ezlabor.hiring.controller;
import com.ezlabor.hiring.domain.model.Postulation;
import com.ezlabor.hiring.domain.service.PostulationService;
import com.ezlabor.hiring.resource.postulation.PostulationResource;
import com.ezlabor.hiring.resource.postulation.SavePostulationResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offers")
public class OffersPostulationsController {
    @Autowired
    private PostulationService postulationService;
    @Autowired
    private ModelMapper mapper;
    @Operation(summary = "Get Postulations", description = "Get All Postulations by offerId",
            tags = "Offer-Postulations")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Postulations by offerId",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/{offerId}/postulations")
    public List<PostulationResource> getAllOffers(@PathVariable Long offerId){
        List<Postulation> postulations = postulationService.getAllPostulationsByOfferId(offerId);
        return postulations.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @Operation(summary = "Accept Postulation", description = "Accept an postulation",
            tags = "Offer-Postulations")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should accept a Postulations",
            content = @Content(mediaType = "application/json"))})
    @PatchMapping("/{offerId}/postulations/{postulationId}/accept")
    public PostulationResource acceptPostulation(@PathVariable Long offerId,@PathVariable Long postulationId){
        return convertToResource(postulationService.acceptPostulation(offerId, postulationId));
    }

    @Operation(summary = "Reject Postulation", description = "Reject an postulation",
            tags = "Offer-Postulations")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should reject a Postulations",
            content = @Content(mediaType = "application/json"))})
    @PatchMapping("/{offerId}/postulations/{postulationId}/reject")
    public PostulationResource rejectPostulation(@PathVariable Long offerId,@PathVariable Long postulationId){
        return convertToResource(postulationService.rejectPostulation(offerId, postulationId));
    }

    @Operation(summary = "Delete Postulation", description = "Delete Postulation",
            tags = "Offer-Postulations")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should delete a Postulations",
            content = @Content(mediaType = "application/json"))})
    @DeleteMapping("/{offerId}/postulations/{postulationId}")
    public ResponseEntity<?> deletePostulation(@PathVariable Long offerId, @PathVariable Long postulationId){
        return postulationService.deletePostulation(offerId, postulationId);
    }


    private Postulation convertToEntity(SavePostulationResource resource){
        return mapper.map(resource, Postulation.class);
    }

    private PostulationResource convertToResource(Postulation entity){
        return mapper.map(entity, PostulationResource.class);
    }

}
