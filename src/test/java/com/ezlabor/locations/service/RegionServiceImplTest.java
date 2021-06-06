package com.ezlabor.locations.service;

import com.ezlabor.accounts.domain.model.Freelancer;
import com.ezlabor.accounts.domain.service.FreelancerService;
import com.ezlabor.accounts.service.FreelancerServiceImpl;
import com.ezlabor.locations.domain.model.Region;
import com.ezlabor.locations.domain.repository.DistrictRepository;
import com.ezlabor.locations.domain.repository.ProvinceRepository;
import com.ezlabor.locations.domain.repository.RegionRepository;
import com.ezlabor.locations.domain.service.DistrictService;
import com.ezlabor.locations.domain.service.ProvinceService;
import com.ezlabor.locations.domain.service.RegionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RegionServiceImplTest {
    @MockBean
    private RegionRepository regionRepository;
    @Autowired
    private RegionService regionService;
    @MockBean
    private ProvinceRepository provinceRepository;
    @MockBean
    private DistrictRepository districtRepository;

    @TestConfiguration
    static class RegionServiceImplTestConfiguration{
        @Bean
        public RegionService regionService(){ return new RegionServiceImpl(); }
    }

    @Test
    public void WhenGetRegionByIdWithValidIdThenReturnsRegion(){
        //Arrange
        Long id = 1L;
        Region region = new Region();
        region.setId(id);
        region.setName("Selva Alta");
        System.out.println(region);
        when(regionRepository.findById(id)).thenReturn(Optional.of(region));
        //Act
        Optional<Region> foundRegion = regionService.getRegionById(id);
        //Assert
        assertThat(foundRegion.get().getId()).isEqualTo(id);
    }

    @Test
    public void WhenGetRegionByIdWithInvalidThenReturnsNull(){
        // Arrange
        Long id = 2L;
        when(regionRepository.findById(id))
                .thenReturn(null);
        // Act
        Optional<Region> foundRegion = regionService.getRegionById(id);
        // Assert
        assertThat(foundRegion).isEqualTo(null);
    }
}