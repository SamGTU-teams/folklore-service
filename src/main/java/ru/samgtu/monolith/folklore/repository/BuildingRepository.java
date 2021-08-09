package ru.samgtu.monolith.folklore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.samgtu.monolith.folklore.model.persistence.Building;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.util.Collection;

/**
 * Creation date: 08.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface BuildingRepository extends JpaRepository<Building, Long> {
    Page<Building> findByNameStartsWithIgnoreCase(String name, Pageable pageable);

    Page<Building> findByTagsIn(Collection<Tag> tags, Pageable pageable);
}
