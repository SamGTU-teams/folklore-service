package ru.samgtu.monolith.activity.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.samgtu.monolith.activity.model.ActivityStatus;
import ru.samgtu.monolith.activity.model.persistence.ScheduledActivity;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Creation date: 14.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface ScheduledActivityService {
    Page<ScheduledActivity> findByDateAfterThanEqual(LocalDateTime from, Pageable pageable);

    Page<ScheduledActivity> findByDateAfterThanEqual(LocalDateTime from, Set<ActivityStatus> statuses, Pageable pageable);

    Page<ScheduledActivity> findScheduledByActivityId(Long id, Pageable pageable);

    Page<ScheduledActivity> findScheduledByActivityId(Long id, Set<ActivityStatus> statuses, Pageable pageable);
}
