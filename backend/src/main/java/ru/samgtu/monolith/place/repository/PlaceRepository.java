package ru.samgtu.monolith.place.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "SELECT p.* " +
            "FROM place p " +
            "RIGHT JOIN ( " +
            "SELECT pt.place_id " +
            "FROM place_tag pt " +
            "WHERE pt.tag_id SIMILAR TO ?1 " +
            "GROUP BY pt.place_id " +
            "HAVING COUNT(pt.tag_id) > 0 " +
            ") t " +
            "ON p.id = t.place_id " +
            "\\n– #pageable\\n",
            countQuery = "SELECT COUNT(*) " +
                    "FROM place p " +
                    "RIGHT JOIN ( " +
                    "SELECT pt.place_id " +
                    "FROM place_tag pt " +
                    "WHERE pt.tag_id SIMILAR TO ?1 " +
                    "GROUP BY pt.place_id " +
                    "HAVING COUNT(pt.tag_id) > 0 " +
                    ") t",
            nativeQuery = true)
    Page<Place> findByTagIdRegex(String regex, Pageable pageable);

    @EntityGraph(attributePaths = {"lob"})
    Optional<Place> findInfoById(Long id);
}
