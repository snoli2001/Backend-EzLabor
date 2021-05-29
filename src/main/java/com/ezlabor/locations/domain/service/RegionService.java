package com.ezlabor.locations.domain.service;

import com.ezlabor.locations.domain.model.Region;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RegionService {
    Region createRegion(Region region);
    Region getRegionById(Long regionId);
    List<Region> getAllRegions();
    Region updateRegion(Long regionId,Region region);
    ResponseEntity<?> deleteRegion(Long regionId);
}
