package ru.samgtu.monolith.place.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.samgtu.monolith.place.model.persistence.Place;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.util.Collection;

/**
 * Creation date: 08.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Page<Place> findByNameStartsWithIgnoreCase(String name, Pageable pageable);

    Page<Place> findByTagsIn(Collection<Tag> tags, Pageable pageable);
}
