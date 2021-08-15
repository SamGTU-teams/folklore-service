package ru.samgtu.monolith.folklore.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.samgtu.monolith.config.JacksonViews;
import ru.samgtu.monolith.folklore.model.dto.BuildingDto;
import ru.samgtu.monolith.model.ExceptionInfo;
import ru.samgtu.monolith.tag.model.dto.TagDto;

import java.util.Collection;
import java.util.Set;

/**
 * Creation date: 06.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RequestMapping(FolkloreController.MAPPING)
public interface FolkloreController {
    String MAPPING = "api/folklores";

    @PostMapping
    @ApiOperation(value = "Get buildings by tags",
            notes = "If tags is null returns all buildings")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Page<BuildingDto> findBuildingsByTags(@ApiParam(name = "tags", value = "tags")
                                         @RequestBody(required = false) Set<TagDto> tags,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size);

    @GetMapping("/search")
    @ApiOperation(value = "Get buildings by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Page<BuildingDto> findBuildingsByName(@RequestParam String name,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size);

    @GetMapping("/{id}")
    @ApiOperation(value = "Get building by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = BuildingDto.class),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    BuildingDto findBuildingById(@PathVariable("id") Long id);

    @GetMapping("/{id}/info")
    @ApiOperation(value = "Get building info by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = BuildingDto.class),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class),
    })
    @JsonView(JacksonViews.DataWithLob.class)
    BuildingDto findBuildingInfoById(@PathVariable("id") Long id);

    @PostMapping("/ids")
    @ApiOperation(value = "Get buildings by ids")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Collection<BuildingDto> findBuildingsByIds(@RequestBody Collection<Long> ids);
}
