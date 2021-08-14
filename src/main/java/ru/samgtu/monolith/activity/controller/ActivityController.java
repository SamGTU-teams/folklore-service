package ru.samgtu.monolith.activity.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.samgtu.monolith.activity.model.dto.ActivityDto;
import ru.samgtu.monolith.activity.model.dto.ScheduledActivityDto;
import ru.samgtu.monolith.config.JacksonViews;
import ru.samgtu.monolith.model.ExceptionInfo;
import ru.samgtu.monolith.tag.model.dto.TagDto;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

/**
 * Creation date: 06.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RequestMapping(ActivityController.MAPPING)
@CrossOrigin("${settings.cors_origin}")
public interface ActivityController {
    String MAPPING = "activities";

    @GetMapping
    @ApiOperation(value = "Get activities by tags")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
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
    @JsonView(JacksonViews.DataWithoutLob.class)
    Page<ActivityDto> getActivitiesByName(@RequestParam String name,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size);

    @GetMapping("/building/{id}")
    @ApiOperation(value = "Get activities by building id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Page<ActivityDto> getActivitiesByBuildingId(@PathVariable("id") Long id, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "0") int page);

    @GetMapping("/{id}")
    @ApiOperation(value = "Get activity by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    ActivityDto getActivityById(@PathVariable("id") Long id);

    @GetMapping("/{id}/info")
    @ApiOperation(value = "Get activity info by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithLob.class)
    ActivityDto getActivityInfoById(@PathVariable("id") Long id);


    @GetMapping("/{id}/scheduled")
    @ApiOperation(value = "Get activity schedule")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Page<ScheduledActivityDto> getActivitySchedule(@PathVariable("id") Long id,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size);

    @GetMapping("/search/date")
    @ApiOperation(value = "Get activity by date")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Page<ScheduledActivityDto> getActivitiesByDateTime(@RequestParam LocalDateTime from, int page, int size);


    @PostMapping("/ids")
    @ApiOperation(value = "Get activities by ids")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Collection<ActivityDto> getActivitiesByIds(@RequestBody Collection<Long> ids);
}