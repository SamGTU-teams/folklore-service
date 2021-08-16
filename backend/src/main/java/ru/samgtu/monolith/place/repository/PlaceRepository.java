package ru.samgtu.monolith.place.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.samgtu.monolith.place.model.persistence.Place;

import java.util.Collection;
import java.util.Optional;

/**
 * Creation date: 08.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Page<Place> findByNameStartsWithIgnoreCase(String name, Pageable pageable);

    Page<Place> findByTagsIdIn(Collection<String> ids, Pageable pageable);

    @EntityGraph(attributePaths = {"lob"})
    Optional<Place> findInfoById(Long id);
}
