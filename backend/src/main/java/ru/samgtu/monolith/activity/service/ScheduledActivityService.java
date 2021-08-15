package ru.samgtu.monolith.activity.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.samgtu.monolith.activity.model.persistence.ScheduledActivity;

import java.time.LocalDateTime;

/**
 * Creation date: 14.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface ScheduledActivityService {
    Page<ScheduledActivity> findByDateAfterThanEqual(LocalDateTime from, Pageable pageable);

    Page<ScheduledActivity> findScheduledByActivityId(Long id, Pageable pageable);
}
