package com.ezlabor.locations.controller;

import com.ezlabor.locations.domain.model.Province;
import com.ezlabor.locations.domain.service.ProvinceService;
import com.ezlabor.locations.resource.Province.ProvinceResource;
import com.ezlabor.locations.resource.Province.SaveProvinceResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("{regionId}/province")
    public List<ProvinceResource> getAllProvincesByRegionId(@PathVariable Long regionId){
        return provinceService.getAllProvincesByRegionId(regionId).stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("{regionId}/province/{provinceId}")
    public ProvinceResource getProvinceByIdAndRegionId(@PathVariable Long regionId,@PathVariable Long provinceId){
        return convertToResource(provinceService.getProvinceByIdAndRegionId(regionId,provinceId));
    }


    @PostMapping("{regionId}/province")
    public ProvinceResource createProvince(@PathVariable Long regionId,@RequestBody SaveProvinceResource provinceResource){
        Province province = convertToEntity(provinceResource);
        return convertToResource(provinceService.createProvinceByRegionId(regionId,province));
    }

    @PutMapping("{regionId}/province/{provinceId}")
    public ProvinceResource updateProvince(@PathVariable Long regionId,@PathVariable Long provinceId,@RequestBody SaveProvinceResource provinceResource){
        Province province = convertToEntity(provinceResource);
        return convertToResource(provinceService.updateProvince(regionId,provinceId,province));
    }

    @DeleteMapping("{regionId}/province/{provinceId}")
    public ResponseEntity<?> deleteProvince(@PathVariable Long regionId,@PathVariable Long provinceId){
        return provinceService.deleteProvince(regionId,provinceId);
    }

    private Province convertToEntity(SaveProvinceResource resource){
        return mapper.map(resource, Province.class);
    }

    private ProvinceResource convertToResource(Province entity){
        return mapper.map(entity, ProvinceResource.class);
    }
}
