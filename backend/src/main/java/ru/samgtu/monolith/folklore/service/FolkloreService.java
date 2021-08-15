package ru.samgtu.monolith.folklore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.samgtu.monolith.folklore.model.persistence.Building;
import ru.samgtu.monolith.folklore.model.persistence.BuildingLob;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.util.Collection;
import java.util.Set;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface FolkloreService {
    Page<Building> getBuildings(Pageable pageable);

    Page<Building> findBuildingsByTags(Set<Tag> tags,
                                       Pageable pageable);

    Page<Building> findBuildingsByName(String name,
                                       Pageable pageable);

    Building findBuildingById(Long id);

    BuildingLob findBuildingInfoById(Long id);

    Collection<Building> findBuildingsByIds(Collection<Long> ids);
}
