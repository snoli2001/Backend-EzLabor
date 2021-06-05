package com.ezlabor.locations.domain.repository;

import com.ezlabor.locations.domain.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region,Long> {
    //Region FindByRegionName(String regionname);
}
