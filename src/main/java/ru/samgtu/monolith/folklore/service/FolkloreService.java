package ru.samgtu.monolith.folklore.service;

import org.springframework.data.domain.Pageable;
import ru.samgtu.monolith.folklore.model.persistence.Building;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.util.List;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface FolkloreService {
    List<Building> getBuildingsByTags(List<Tag> tags,
                                      Pageable pageable);

    List<Building> getBuildingsByName(String name,
                                      Pageable pageable);

    Building getBuildingById(Long id);
}
