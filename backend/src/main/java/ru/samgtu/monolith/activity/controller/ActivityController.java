package ru.samgtu.monolith.activity.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.samgtu.monolith.activity.model.ActivityStatus;
import ru.samgtu.monolith.activity.model.dto.ActivityDto;
import ru.samgtu.monolith.activity.model.dto.ScheduledActivityDto;
import ru.samgtu.monolith.config.JacksonViews;
import ru.samgtu.monolith.model.ExceptionInfo;

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
public interface ActivityController {
    String MAPPING = "api/activities";

    @PostMapping
    @ApiOperation(value = "Get activities by tags",
            notes = "If tags is null returns all activities")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Page<ActivityDto> findActivitiesByTags(@ApiParam(name = "tags", value = "tags")
                                           @RequestBody(required = false) Set<String> tags,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size);

    @GetMapping("/search")
    @ApiOperation(value = "Get activities by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Page<ActivityDto> findActivitiesByName(@RequestParam String name,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size);

    @GetMapping("/place/{id}")
    @ApiOperation(value = "Get activities by place id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Page<ActivityDto> findActivitiesByPlaceId(@PathVariable("id") Long id,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue = "0") int page);

    @GetMapping("/{id}")
    @ApiOperation(value = "Get activity by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    ActivityDto findActivityById(@PathVariable("id") Long id);

    @GetMapping("/{id}/info")
    @ApiOperation(value = "Get activity info by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithLob.class)
    ActivityDto findActivityInfoById(@PathVariable("id") Long id);


    @GetMapping("/{id}/scheduled")
    @ApiOperation(value = "Get scheduled activities by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Page<ScheduledActivityDto> findScheduledActivitiesByActivityId(@PathVariable("id") Long id,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size,
                                                                   @RequestBody(required = false) Set<String> statuses);

    @GetMapping("/search/date")
    @ApiOperation(value = "Get activity by date")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Page<ScheduledActivityDto> findActivitiesByDateTime(@RequestParam LocalDateTime from, 
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestBody(required = false) Set<String> statuses);


    @PostMapping("/ids")
    @ApiOperation(value = "Get activities by ids")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Collection<ActivityDto> findActivitiesByIds(@RequestBody Collection<Long> ids);

    @GetMapping("/region/{id}")
    @ApiOperation(value = "Get activities by region id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Page<ActivityDto> findByRegionId(@PathVariable("id") Long regionId,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size);

}
