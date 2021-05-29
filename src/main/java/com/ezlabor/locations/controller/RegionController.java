package com.ezlabor.locations.controller;

import com.ezlabor.locations.domain.model.Region;
import com.ezlabor.locations.domain.service.RegionService;
import com.ezlabor.locations.resource.Region.RegionResource;
import com.ezlabor.locations.resource.Region.SaveRegionResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RegionController {

    @Autowired
    private RegionService regionService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/regions")
    public List<RegionResource> getAllRegions(){
        return regionService.getAllRegions()
                .stream()
                .map(
                        region -> mapper.map(region,RegionResource.class)
                ).collect(Collectors.toList());
    }

    @GetMapping("/regions/{id}")
    public RegionResource getRegionById(@PathVariable Long id){
        return convertToResource(regionService.getRegionById(id));
    }

    @PostMapping("/regions")
    public RegionResource saveRegion(@Valid @RequestBody SaveRegionResource resource){
        Region region = convertToEntity(resource);
        return convertToResource(regionService.createRegion(region));
    }

    @PutMapping("/regions/{id}")
    public RegionResource updateRegion(@PathVariable Long id, @RequestBody SaveRegionResource resource){
        Region region = convertToEntity(resource);
        return convertToResource(regionService.updateRegion(id, region));
    }

    @DeleteMapping("/regions/{id}")
    public ResponseEntity<?> deleteRegions(@PathVariable Long id){
        return regionService.deleteRegion(id);
    }


    private Region convertToEntity(SaveRegionResource resource){
        return mapper.map(resource, Region.class);
    }

    private RegionResource convertToResource(Region entity){
        return mapper.map(entity, RegionResource.class);
    }
}
