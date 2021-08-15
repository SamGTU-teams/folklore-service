package ru.samgtu.monolith.activity.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.activity.model.persistence.ActivityLob;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Creation date: 12.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface ActivityService {
    Page<Activity> findActivitiesByTags(Collection<Tag> tags, Pageable pageable);

    Page<Activity> findByDateAfterThan(LocalDateTime from, Pageable pageable);

    Page<Activity> findActivitiesByName(String name, Pageable pageable);

    Activity findActivityById(Long id);

    Page<Activity> findActivitiesByPlaceId(Long id, Pageable pageable);

    Collection<Activity> findActivitiesByIds(Collection<Long> ids);

    ActivityLob findActivityInfoById(Long id);

    Page<Activity> getActivities(Pageable pageable);
}
