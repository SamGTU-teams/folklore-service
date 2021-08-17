package ru.samgtu.monolith.activity.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.samgtu.monolith.activity.NoSuchActivityException;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.activity.repository.ActivityRepository;
import ru.samgtu.monolith.activity.service.ActivityService;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.util.Collection;

@Service
@AllArgsConstructor
@Slf4j
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    private final ScheduledActivityRepository scheduledActivityRepository;

    @Override
    public Page<Activity> findActivitiesByTags(Collection<String> tags, Pageable pageable) {
        return activityRepository.findByTagsIn(tags, pageable);
    }

    @Override
    public Page<Activity> findActivitiesByName(String name, Pageable pageable) {
        return activityRepository
                .findByNameStartsWithIgnoreCase(name, pageable);
    }

    @Override
    public Activity findActivityById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Activity with id = {} does not exists", id);
                    return new NoSuchActivityException("Activity does not exists");
                });
    }

    @Override
    public Page<Activity> findActivitiesByPlaceId(Long id, Pageable pageable) {
        return activityRepository.findAllByPlaceId(id, pageable);
    }

    @Override
    public Page<Activity> getActivities(Pageable pageable) {
        return activityRepository.findAll(pageable);
    }

    @Override
    public Collection<Activity> findActivitiesByIds(Collection<Long> ids) {
        return activityRepository.findActivityByIdIn(ids);
    }

    @Override
    public Activity findActivityInfoById(Long id) {
        return activityRepository.findInfoById(id).orElseThrow(() -> {
            log.warn("Activity with id = {} does not exists", id);
            return new NoSuchActivityException("No activity found");
        });
    }
}
