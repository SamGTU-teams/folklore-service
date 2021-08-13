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
    public List<ActivityDto> getActivitiesByTags(Set<TagDto> tags, int page, int size) {
        Set<Tag> input = mapperFacade.mapAsSet(tags, Tag.class);
        return mapperFacade.mapAsList(activityService.findByTags(input, PageRequest.of(page, size)), ActivityDto.class);
    }

    @Override
    public List<ActivityDto> getActivitiesByParams(String name, LocalDateTime from, int page, int size) {
        if(name != null){
            return mapperFacade.mapAsList(activityService.findByName(name), ActivityDto.class);
        }
        if(from != null) {
            return mapperFacade.mapAsList(activityService.findByDateAfterThan(from, size, page), ActivityDto.class);
        }
        throw new IllegalArgumentException("At least one parameter(from or name) must be in");
    }

    @Override
    public List<ActivityDto> getActivitiesByBuildingId(Long id, int size, int page) {
        return mapperFacade.mapAsList(activityService.
                findByBuildingId(id, PageRequest.of(page, size)).toList(), ActivityDto.class);
    }

    @Override
    public ActivityDto getActivityById(Long id) {
        return mapperFacade.map(activityService.findById(id).orElse(new Activity()), ActivityDto.class);
    }

    @Override
    public Page<ScheduledActivityDto> getActivitySchedule(Long id, int page, int size) {
        return null;
    }
}