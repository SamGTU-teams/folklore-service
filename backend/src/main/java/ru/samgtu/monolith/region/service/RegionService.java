package ru.samgtu.monolith.region.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.samgtu.monolith.region.model.persistence.Region;

/**
 * Creation date: 16.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface RegionService {
    Region findRegionById(Long id);

    Page<Region> findRegionsByName(String name,
                                   Pageable pageable);

    Page<Region> getRegions(Pageable pageable);
}
