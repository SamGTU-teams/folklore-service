package ru.samgtu.monolith.activity.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.samgtu.monolith.activity.NoSuchActivityException;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.activity.repository.ActivityRepository;
import ru.samgtu.monolith.activity.repository.ScheduledActivityRepository;
import ru.samgtu.monolith.activity.service.ActivityService;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    private final EntityManager entityManager;
    private final ScheduledActivityRepository scheduledActivityRepository;

    @Override
    public Page<Activity> findByTags(Collection<Tag> tags, Pageable pageable) {
        return activityRepository.findByTagsIn(tags, pageable);
    }

    @Override
    public Page<Activity> findByDateAfterThan(LocalDateTime from, Pageable pageable) {
        List<Activity> activities = entityManager.createNamedQuery("select_by_date_more_than", Activity.class).
                setParameter(1, Timestamp.valueOf(from)).
                setParameter(2, pageable.getPageSize())
                .setParameter(3, pageable.getOffset())
                .getResultList();

//         ToDo: create query to count total activities

        Page<Activity> page = new PageImpl<>(activities, pageable, -1);
        return page;
    }

    @Override
    public Page<Activity> findByName(String name, Pageable pageable) {
        return activityRepository
                .findByNameStartsWithIgnoreCase(name, pageable);
    }

    @Override
    public Activity findById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Activity with id = {} does not exists", id);
                    return new NoSuchActivityException("Activity does not exists");
                });
    }

    @Override
    public Page<Activity> findByBuildingId(Long id, Pageable pageable) {
        return activityRepository.findAllByBuildingId(id, pageable);
    }

    @Override
    public Page<Activity> getActivities(Pageable pageable) {
        return activityRepository.findAll(pageable);
    }

    @Override
    public Collection<Activity> getActivitiesByIds(Collection<Long> ids) {
        return activityRepository.findActivityByIdIn(ids);
    }
}
