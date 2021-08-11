package ru.samgtu.monolith.activity.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.samgtu.monolith.activity.model.dto.ActivityDto;
import ru.samgtu.monolith.activity.model.dto.ScheduledActivityDto;
import ru.samgtu.monolith.model.ExceptionInfo;
import ru.samgtu.monolith.tag.model.dto.TagDto;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Creation date: 06.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RequestMapping(ActivityController.MAPPING)
@CrossOrigin("*")
public interface ActivityController {
    String MAPPING = "activities";

    @GetMapping
    @ApiOperation(value = "Get activities by tags")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    Page<ActivityDto> getActivitiesByTags(@ApiParam(name = "tags", value = "tags")
                                          @RequestBody(required = false) Set<TagDto> tags,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size);

    @GetMapping("/search")
    @ApiOperation(value = "Get activities by time")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    Page<ActivityDto> getActivitiesByTime(@RequestParam LocalDateTime from,
                                          @RequestParam LocalDateTime to,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size);

    @GetMapping("/search")
    @ApiOperation(value = "Get activities by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    Page<ActivityDto> getActivitiesByName(@RequestParam String name,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size);

    @GetMapping("/{id}")
    @ApiOperation(value = "Get activities by building id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    Page<ActivityDto> getActivitiesByBuildingId(@PathVariable("id") Long id);

    @GetMapping("/{id}")
    @ApiOperation(value = "Get activity by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    ActivityDto getActivityById(@PathVariable("id") Long id);

    @GetMapping("/{id}/scheduled")
    @ApiOperation(value = "Get activity schedule")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    Page<ScheduledActivityDto> getActivitySchedule(@PathVariable("id") Long id,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size);
}
