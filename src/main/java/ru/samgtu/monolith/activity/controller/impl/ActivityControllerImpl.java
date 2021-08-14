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
import ru.samgtu.monolith.activity.service.ActivityService;
import ru.samgtu.monolith.tag.model.dto.TagDto;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.time.LocalDateTime;
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
    public Page<ActivityDto> getActivitiesByParams(String name, LocalDateTime from, int page, int size) {
        PageRequest pageRequest = createPageRequestForActivities(size, page);
        if (nonNull(name)) {
            Page<Activity> activities = activityService.findByName(name, pageRequest);
            return mapPage(activities);
        }
        if (nonNull(from)) {
            Page<Activity> activities = activityService.findByDateAfterThan(from, pageRequest);
            return mapPage(activities);
        }
        throw new IllegalArgumentException("At least one parameter(from or name) must be in");
    }

    @Override
    public Page<ActivityDto> getActivitiesByBuildingId(Long id, int size, int page) {
        PageRequest pageRequest = createPageRequestForActivities(size, page);
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
        return null;
    }

    @Override
    public ActivityDto getActivityInfoById(Long id) {
        return null;
    }

    private PageRequest createPageRequestForActivities(int page, int size) {
        return PageRequest.of(page, size, Sort.by("id").ascending());
    }

    private Page<ActivityDto> mapPage(Page<Activity> page) {
        return page.map(activity -> mapper.map(activity, ActivityDto.class));
    }
}