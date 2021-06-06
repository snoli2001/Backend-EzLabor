package com.ezlabor.locations.service;

import com.ezlabor.locations.domain.model.Province;
import com.ezlabor.locations.domain.model.Region;
import com.ezlabor.locations.domain.repository.DistrictRepository;
import com.ezlabor.locations.domain.repository.ProvinceRepository;
import com.ezlabor.locations.domain.repository.RegionRepository;
import com.ezlabor.locations.domain.service.ProvinceService;
import com.ezlabor.locations.domain.service.RegionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProvinceServiceImplTest {
    @MockBean
    private ProvinceRepository provinceRepository;
    @Autowired
    private ProvinceService provinceService;
    @MockBean
    private RegionRepository regionRepository;
    @MockBean
    private DistrictRepository districtRepository;

    @TestConfiguration
    static class ProvinceServiceImplTestConfiguration{
        @Bean
        public ProvinceService provinceService(){ return new ProvinceServiceImpl(); }
    }

    @Test
    public void WhenGetAllProvincesByRegionIdWithValidIdThenReturnsProvinces(){
        //Arrange
        Long id = 1L;
        Region region = new Region();
        region.setId(id);
        region.setName("Selva Alta");

        List<Province> provinceList = new ArrayList<>();

        Province province1 = new Province();
        province1.setId(1L);
        province1.setName("asdaasd");
        province1.setRegion(region);
        provinceList.add(province1);

        Province province2 = new Province();
        province2.setId(2L);
        province2.setName("prueba");
        province2.setRegion(region);
        provinceList.add(province2);

        when(provinceRepository.findAllByRegionId(id)).thenReturn(provinceList);
        //Act
        List<Province> foundProvinces = provinceService.getAllProvincesByRegionId(id);

        //Assert
        assertThat(foundProvinces.get(0).getRegion()).isEqualTo(region);
        assertThat(foundProvinces.size()).isEqualTo(2);
    }
}