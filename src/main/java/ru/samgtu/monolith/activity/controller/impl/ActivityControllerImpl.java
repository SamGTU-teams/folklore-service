package ru.samgtu.monolith.activity.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import ru.samgtu.monolith.activity.controller.ActivityController;
import ru.samgtu.monolith.activity.model.dto.ActivityDto;
import ru.samgtu.monolith.activity.model.dto.ScheduledActivityDto;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.activity.service.ActivityService;
import ru.samgtu.monolith.tag.model.dto.TagDto;

import java.time.LocalDateTime;
import java.util.List;
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

    @Override
    public Page<ActivityDto> getActivitiesByTags(Set<TagDto> tags, int page, int size) {
        return null;
    }

    @Override
    public Page<ActivityDto> getActivitiesByParams(String name, LocalDateTime from, LocalDateTime to, int page, int size) {

    }

    @Override
    public Page<ActivityDto> getActivitiesByBuildingId(Long id) {
        return null;
    }

    @Override
    public ActivityDto getActivityById(Long id) {
        return null;
    }

    @Override
    public Page<ScheduledActivityDto> getActivitySchedule(Long id, int page, int size) {
        return null;
    }
}