package ru.samgtu.monolith.region.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.samgtu.monolith.region.model.persistence.Region;

/**
 * Creation date: 16.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface RegionRepository extends JpaRepository<Region, Long> {
    Page<Region> findByNameStartsWithIgnoreCase(String name, Pageable pageable);
}
