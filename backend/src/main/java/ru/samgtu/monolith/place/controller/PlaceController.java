package ru.samgtu.monolith.place.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.samgtu.monolith.config.JacksonViews;
import ru.samgtu.monolith.model.ExceptionInfo;
import ru.samgtu.monolith.place.model.dto.PlaceDto;
import ru.samgtu.monolith.tag.model.dto.TagDto;

import java.util.Collection;
import java.util.Set;

/**
 * Creation date: 06.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RequestMapping(PlaceController.MAPPING)
public interface PlaceController {
    String MAPPING = "api/places";

    @PostMapping
    @ApiOperation(value = "Get places by tags",
            notes = "If tags is null returns all places")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Page<PlaceDto> findPlacesByTags(@ApiParam(name = "tags", value = "tags")
                                    @RequestBody(required = false) Set<TagDto> tags,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size);

    @GetMapping("/search")
    @ApiOperation(value = "Get places by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Page<PlaceDto> findPlacesByName(@RequestParam String name,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size);

    @GetMapping("/{id}")
    @ApiOperation(value = "Get place by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = PlaceDto.class),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    PlaceDto findPlacesById(@PathVariable("id") Long id);

    @GetMapping("/{id}/info")
    @ApiOperation(value = "Get place info by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = PlaceDto.class),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class),
    })
    @JsonView(JacksonViews.DataWithLob.class)
    PlaceDto findPlacesInfoById(@PathVariable("id") Long id);

    @PostMapping("/ids")
    @ApiOperation(value = "Get places by ids")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Collection<PlaceDto> findPlacesByIds(@RequestBody Collection<Long> ids);
}
