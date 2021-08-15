package ru.samgtu.monolith.activity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.samgtu.monolith.activity.model.ActivityStatus;
import ru.samgtu.monolith.activity.model.persistence.ScheduledActivity;
import ru.samgtu.monolith.activity.model.persistence.ScheduledId;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Creation date: 14.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface ScheduledActivityRepository extends JpaRepository<ScheduledActivity, ScheduledId> {
    @Query("select sa.id from ScheduledActivity sa")
    List<ScheduledId> findAllIds();

    @Query("select s.id.activityId, min(s.id.dateTime) as dt " +
            "from ScheduledActivity s " +
            "where s.id.dateTime >= ?1 " +
            "group by s.id.activityId " +
            "order by dt")
    Page<Object[]> findMinDateTimeIsGreaterThanEqualOrderByIdDateTimeAsc(LocalDateTime dateTime, Pageable pageable);

    @Query("select s from ScheduledActivity s where s.id.activityId = :id")
    Page<ScheduledActivity> findByNumericId(Long id, Pageable pageable);

    Page<ScheduledActivity> findByIdActivityIdEqualsAndStatusIsIn(Long activityId, Collection<ActivityStatus> statuses, Pageable pageable);
}
