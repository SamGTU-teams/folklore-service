package ru.samgtu.monolith.activity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.time.LocalDateTime;
import java.util.Collection;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Page<Activity> findByNameStartsWithIgnoreCase(String name, Pageable pageable);

    Page<Activity> findAllByBuildingId(Long id, Pageable pageable);

    Page<Activity> findByTagsIn(Collection<Tag> tags, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT a.* FROM activity a INNER JOIN activity_scheduled asch ON a.id = asch.activity_id where asch.date_time >= ?1 ORDER BY date_time ?#{#pageable}",
            countQuery = "SELECT count(*) FROM activity a INNER JOIN activity_scheduled asch ON a.id = asch.activity_id where asch.date_time >= ?1 ORDER BY date_time"
    )
    Page<Activity> findByDateAfterThan(LocalDateTime from, Pageable pageable);
}
