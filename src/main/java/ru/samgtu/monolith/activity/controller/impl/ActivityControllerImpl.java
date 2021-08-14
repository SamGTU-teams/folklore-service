package ru.samgtu.monolith.activity.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import java.util.List;
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

    private final MapperFacade mapperFacade;

    @Override
    public List<ActivityDto> getActivitiesByTags(Set<TagDto> tagsDto, int page, int size) {
        List<Activity> activities;
        PageRequest pageRequest = PageRequest.of(page, size);
        if (isNull(tagsDto)) {
            activities = activityService.getActivities(pageRequest).getContent();
        } else {
            Set<Tag> tags = mapperFacade.mapAsSet(tagsDto, Tag.class);
            activities = activityService.findByTags(tags, pageRequest);
        }
        return mapperFacade.mapAsList(activities, ActivityDto.class);
    }

    @Override
    public List<ActivityDto> getActivitiesByParams(String name, LocalDateTime from, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (nonNull(name)) {
            List<Activity> activities = activityService.findByName(name, pageRequest);
            return mapperFacade.mapAsList(activities, ActivityDto.class);
        }
        if (nonNull(from)) {
            List<Activity> activities = activityService.findByDateAfterThan(from, pageRequest);
            return mapperFacade.mapAsList(activities, ActivityDto.class);
        }
        throw new IllegalArgumentException("At least one parameter(from or name) must be in");
    }

    @Override
    public List<ActivityDto> getActivitiesByBuildingId(Long id, int size, int page) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Activity> activities = activityService.findByBuildingId(id, pageRequest);
        return mapperFacade.mapAsList(activities.toList(), ActivityDto.class);
    }

    @Override
    public ActivityDto getActivityById(Long id) {
        Activity activity = activityService.findById(id);
        return mapperFacade.map(activity, ActivityDto.class);
    }

    @Override
    public Page<ScheduledActivityDto> getActivitySchedule(Long id, int page, int size) {
        return null;
    }

    @Override
    public ActivityDto getActivityInfoById(Long id) {
        return null;
    }
}