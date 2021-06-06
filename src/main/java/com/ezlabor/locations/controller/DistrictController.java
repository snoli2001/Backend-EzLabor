package com.ezlabor.locations.controller;

import com.ezlabor.locations.domain.model.District;
import com.ezlabor.locations.domain.service.DistrictService;
import com.ezlabor.locations.resource.District.DistrictResource;
import com.ezlabor.locations.resource.District.SaveDistrictResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DistrictController {

    @Autowired
    private DistrictService districtService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{provinceId}/districts")
    public List<DistrictResource> getAllDistrictsByProvinceId(@PathVariable Long provinceId){
        return districtService.getAllDistrictsByProvinceId(provinceId).stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/{provinceId}/district/{districtId}")
    public DistrictResource getDistrictByIdAndProvinceId(@PathVariable Long provinceId,@PathVariable Long districtId){
        Optional<District> district = districtService.getDistrictByIdAndProvinceId(districtId,provinceId);
        return district.map(this::convertToResource).orElse(null);
    }

    @PostMapping("/{provinceId}/districts")
    public DistrictResource createDistrict(@PathVariable Long provinceId, @RequestBody SaveDistrictResource districtResource){
        District district = convertToEntity(districtResource);
        return convertToResource(districtService.createDistrictByProvinceId(provinceId,district));
    }

    @PutMapping("/{provinceId}/districts/{districtId}")
    public DistrictResource updateDistrict(@PathVariable Long provinceId,@PathVariable Long districtId,@RequestBody SaveDistrictResource districtResource){
        District district = convertToEntity(districtResource);
        return convertToResource(districtService.updateDistrict(provinceId,districtId,district));
    }

    @DeleteMapping("{provinceId}/districts/{districtId}")
    public ResponseEntity<?> deleteDistrict(@PathVariable Long provinceId,@PathVariable Long districtId){
        return districtService.deleteDistrict(provinceId,districtId);
    }

    private District convertToEntity(SaveDistrictResource resource){
        return mapper.map(resource, District.class);
    }

    private DistrictResource convertToResource(District entity){
        return mapper.map(entity, DistrictResource.class);
    }
}
