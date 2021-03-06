package ru.samgtu.monolith.activity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.samgtu.monolith.activity.model.ActivityStatus;
import ru.samgtu.monolith.activity.model.persistence.ScheduledActivity;
import ru.samgtu.monolith.activity.model.persistence.ScheduledId;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

/**
 * Creation date: 14.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface ScheduledActivityRepository extends JpaRepository<ScheduledActivity, ScheduledId> {


    Page<ScheduledActivity> findAllByActivityIdAndStatusIn(Long activityId, Set<ActivityStatus> statuses, Pageable pageable);

    @Query("select sa.id from ScheduledActivity sa")
    Page<ScheduledId> findAllIds(Pageable pageable);


    @Query("select s.id.activityId, min(s.id.dateTime) as dt " +
            "from ScheduledActivity s " +
            "where s.id.dateTime >= ?1 " +
            "group by s.id.activityId " +
            "order by dt")
    Page<Object[]> findMinDateTimeIsGreaterThanEqualOrderByIdDateTimeAsc(LocalDateTime dateTime, Pageable pageable);

    @Query("select s.id.activityId, min(s.id.dateTime) as dt " +
            "from ScheduledActivity s " +
            "where s.id.dateTime >= ?1 and s.status in ?2 " +
            "group by s.id.activityId " +
            "order by dt")
    Page<Object[]> findMinDateTimeIsGreaterThanEqualOrderByIdDateTimeAsc(LocalDateTime dateTime, Set<ActivityStatus> statuses, Pageable pageable);

    Page<ScheduledActivity> findByActivityId(Long activityId, Pageable pageable);

    Page<ScheduledActivity> findByIdActivityIdEqualsAndStatusIsIn(Long activityId, Collection<ActivityStatus> statuses, Pageable pageable);
}
