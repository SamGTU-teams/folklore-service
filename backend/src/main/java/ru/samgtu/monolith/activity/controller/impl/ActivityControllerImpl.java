package ru.samgtu.monolith.activity.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;
import ru.samgtu.monolith.activity.controller.ActivityController;
import ru.samgtu.monolith.activity.model.dto.ActivityDto;
import ru.samgtu.monolith.activity.model.dto.ScheduledActivityDto;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.activity.model.persistence.ScheduledActivity;
import ru.samgtu.monolith.activity.service.ActivityService;
import ru.samgtu.monolith.activity.service.ScheduledActivityService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

/**
 * Creation date: 11.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ActivityControllerImpl implements ActivityController {
    private final ActivityService activityService;

    private final ScheduledActivityService scheduledActivityService;

    private final MapperFacade mapper;

    @Override
    public Page<ActivityDto> findActivitiesByTags(Set<String> tags, int page, int size) {
        PageRequest pageRequest = createPageRequestForActivities(size, page);
        Page<Activity> activities = activityService.findActivitiesByTags(tags, pageRequest);
        return mapActivityPage(activities);
    }

    @Override
    public Page<ActivityDto> findActivitiesByName(String name, int page, int size) {
        PageRequest pageRequest = createPageRequestForActivities(page, size);
        Page<Activity> activities = activityService.findActivitiesByName(name, pageRequest);
        return mapActivityPage(activities);
    }

    @Override
    public Page<ScheduledActivityDto> findActivitiesByDateTime(LocalDateTime from, int page, int size) {
        PageRequest pageRequest = createPageRequestForActivities(page, size);
        Page<ScheduledActivity> scheduledActivities = scheduledActivityService.findByDateAfterThanEqual(from, pageRequest);
        return scheduledActivities.map(activity -> mapper.map(activity, ScheduledActivityDto.class));
    }

    @Override
    public Page<ActivityDto> findActivitiesByPlaceId(Long id, int size, int page) {
        PageRequest pageRequest = createPageRequestForActivities(page, size);
        Page<Activity> activities = activityService.findActivitiesByPlaceId(id, pageRequest);
        return mapActivityPage(activities);
    }

    @Override
    public ActivityDto findActivityById(Long id) {
        Activity activity = activityService.findActivityById(id);
        return mapper.map(activity, ActivityDto.class);
    }

    @Override
    public Page<ScheduledActivityDto> findScheduledActivitiesByActivityId(Long id, int page, int size) {
        PageRequest pageRequest = createPageRequestForActivities(page, size);
        Page<ScheduledActivity> scheduledActivities = scheduledActivityService.findScheduledByActivityId(id, pageRequest);
        return mapScheduledPage(scheduledActivities);
    }

    @Override
    public ActivityDto findActivityInfoById(Long id) {
        Activity activity = activityService.findActivityInfoById(id);
        return mapper.map(activity, ActivityDto.class);
    }

    @Override
    public Collection<ActivityDto> findActivitiesByIds(Collection<Long> ids) {
        Collection<Activity> activities = activityService.findActivitiesByIds(ids);
        return mapper.mapAsList(activities, ActivityDto.class);
    }

    private PageRequest createPageRequestForActivities(int page, int size) {
        return PageRequest.of(page, size);
    }

    private Page<ActivityDto> mapActivityPage(Page<Activity> page) {
        return page.map(activity -> mapper.map(activity, ActivityDto.class));
    }

    private Page<ScheduledActivityDto> mapScheduledPage(Page<ScheduledActivity> page) {
        return page.map(activity -> mapper.map(activity, ScheduledActivityDto.class));
    }
}