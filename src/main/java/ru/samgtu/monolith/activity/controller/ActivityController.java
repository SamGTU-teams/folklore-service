package ru.samgtu.monolith.activity.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.samgtu.monolith.activity.model.dto.ActivityDto;
import ru.samgtu.monolith.activity.model.dto.ScheduledActivityDto;
import ru.samgtu.monolith.tag.model.dto.TagDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Creation date: 06.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RequestMapping(ActivityController.MAPPING)
public interface ActivityController {
    String MAPPING = "activities";

    @GetMapping
    @ApiOperation(value = "Get activities by tags")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    Page<ActivityDto> getActivitiesByTags(@RequestBody List<TagDto> tagsDto,
                                          @PageableDefault Pageable pageable);

    @GetMapping("/search")
    @ApiOperation(value = "Get activities by time")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    Page<ActivityDto> getActivitiesByTime(@RequestParam LocalDateTime from,
                                          @RequestParam LocalDateTime to,
                                          @PageableDefault Pageable pageable);

    @GetMapping("/search")
    @ApiOperation(value = "Get activities by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    Page<ActivityDto> getActivitiesByName(@RequestParam String name,
                                          @PageableDefault Pageable pageable);

    @GetMapping("/{id}")
    @ApiOperation(value = "Get activities by building id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    Page<ActivityDto> getActivitiesByBuildingId(@PathVariable("id") Long id);

    @GetMapping("/{id}")
    @ApiOperation(value = "Get activity by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = ActivityDto.class)
    })
    ActivityDto getActivityById(@PathVariable("id") Long id);

    @GetMapping("/{id}/scheduled")
    @ApiOperation(value = "Get activity schedule")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = ActivityDto.class)
    })
    Page<ScheduledActivityDto> getActivitySchedule(@PathVariable("id") Long id,
                                                   @PageableDefault Pageable pageable);
}
