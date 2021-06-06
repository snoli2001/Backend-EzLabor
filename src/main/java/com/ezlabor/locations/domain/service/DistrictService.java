package com.ezlabor.locations.domain.service;

import com.ezlabor.locations.domain.model.District;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DistrictService {
    District createDistrictByProvinceId (Long provinceId,District district);
    Optional<District> getDistrictByIdAndProvinceId(Long districtId, Long provinceId);
    List<District> getAllDistrictsByProvinceId(Long provinceId);
    District updateDistrict(Long provinceId, Long districtId, District district);
    ResponseEntity<?> deleteDistrict(Long provinceId ,Long districtId);
}
