package ru.samgtu.monolith.activity.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import ru.samgtu.monolith.activity.controller.ActivityController;
import ru.samgtu.monolith.activity.model.dto.ActivityDto;
import ru.samgtu.monolith.activity.model.dto.ScheduledActivityDto;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.activity.model.persistence.ScheduledActivity;
import ru.samgtu.monolith.activity.service.ActivityService;
import ru.samgtu.monolith.activity.service.ScheduledActivityService;
import ru.samgtu.monolith.tag.model.dto.TagDto;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Creation date: 11.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public class ActivityControllerImpl implements ActivityController {
    private final ActivityService activityService;

    private final ScheduledActivityService scheduledActivityService;

    private final MapperFacade mapper;

    @Override
    public Page<ActivityDto> getActivitiesByTags(Set<TagDto> tagsDto, int page, int size) {
        Page<Activity> activities;
        PageRequest pageRequest = createPageRequestForActivities(size, page);
        if (isNull(tagsDto)) {
            activities = activityService.getActivities(pageRequest);
        } else {
            Set<Tag> tags = mapper.mapAsSet(tagsDto, Tag.class);
            activities = activityService.findByTags(tags, pageRequest);
        }
        return mapPage(activities);
    }

    @Override
    public Page<ActivityDto> getActivitiesByName(String name, LocalDateTime from, int page, int size) {
        PageRequest pageRequest = createPageRequestForActivities(page, size);
        if (nonNull(name)) {
            Page<Activity> activities = activityService.findByName(name, pageRequest);
            return mapPage(activities);
        }
//        ToDo: extracted to getActivitiesByBuildingId
        if (nonNull(from)) {
            Page<Activity> activities = activityService.findByDateAfterThan(from, pageRequest);
            return mapPage(activities);
        }
        throw new IllegalArgumentException("At least one parameter(from or name) must be in");
    }

    public Page<ScheduledActivityDto> getActivitiesByDateTime(LocalDateTime from, int page, int size) {
//        ToDo: create endpoint
        PageRequest pageRequest = createPageRequestForActivities(page, size);
        Page<ScheduledActivity> scheduledActivities = scheduledActivityService.findByDateAfterThan(from, pageRequest);
        return scheduledActivities.map(activity -> mapper.map(activity, ScheduledActivityDto.class));
    }

    @Override
    public Page<ActivityDto> getActivitiesByBuildingId(Long id, int size, int page) {

        PageRequest pageRequest = createPageRequestForActivities(page, size);
        Page<Activity> activities = activityService.findByBuildingId(id, pageRequest);
        return mapPage(activities);
    }

    @Override
    public ActivityDto getActivityById(Long id) {
        Activity activity = activityService.findById(id);
        return mapper.map(activity, ActivityDto.class);
    }

    @Override
    public Page<ScheduledActivityDto> getActivitySchedule(Long id, int page, int size) {
        PageRequest pageRequest = createPageRequestForActivities(page, size);
        return null;
    }

    @Override
    public ActivityDto getActivityInfoById(Long id) {
        return null;
    }

    @Override
    public Collection<ActivityDto> getActivitiesByIds(Collection<Long> ids) {
        return null;
    }

    private PageRequest createPageRequestForActivities(int page, int size) {
        return PageRequest.of(page, size, Sort.by("id").ascending());
    }

    private Page<ActivityDto> mapPage(Page<Activity> page) {
        return page.map(activity -> mapper.map(activity, ActivityDto.class));
    }
}