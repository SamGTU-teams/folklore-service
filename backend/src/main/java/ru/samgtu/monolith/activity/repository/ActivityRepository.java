package ru.samgtu.monolith.activity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.util.Collection;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Page<Activity> findByNameStartsWithIgnoreCase(String name, Pageable pageable);

    Page<Activity> findAllByPlaceId(Long id, Pageable pageable);

    Page<Activity> findByTagsIn(Collection<Tag> tags, Pageable pageable);

    Collection<Activity> findActivityByIdIn(Collection<Long> ids);

    @EntityGraph(attributePaths = "lob")
    Optional<Activity> findInfoById(Long id);

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
                    "FROM activity a " +
                    "RIGHT JOIN ( " +
                    "SELECT at.activity_id " +
                    "FROM activity_tag at " +
                    "WHERE at.tag_id SIMILAR TO ?1 " +
                    "GROUP BY at.activity_id " +
                    "HAVING COUNT(at.tag_id) > 0 " +
                    ") t",
            nativeQuery = true)
    Page<Activity> findByTagIdRegex(String regex, Pageable pageable);
}
