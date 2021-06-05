package com.ezlabor.locations.domain.service;

import com.ezlabor.locations.domain.model.District;
import com.ezlabor.locations.service.DistrictServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface DistrictService {
    District createDistrictByProvinceId (Long provinceId,District district);
    District getDistrictByIdAndProvinceId(Long provinceId, Long districtId);
    List<District> getAllDistrictsByProvinceId(Long provinceId);
    District updateDistrict(Long provinceId, Long districtId, District district);
    ResponseEntity<?> deleteDistrict(Long provinceId ,Long districtId);
}
