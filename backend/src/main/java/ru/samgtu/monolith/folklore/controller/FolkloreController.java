package ru.samgtu.monolith.folklore.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.samgtu.monolith.folklore.model.dto.BuildingDto;
import ru.samgtu.monolith.model.ExceptionInfo;
import ru.samgtu.monolith.tag.model.dto.TagDto;

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
    Page<BuildingDto> getBuildingsByTags(@ApiParam(name = "tags", value = "tags")
                                         @RequestBody(required = false) Set<TagDto> tags,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size);

    @GetMapping("/search")
    @ApiOperation(value = "Get buildings by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    Page<BuildingDto> getBuildingsByName(@RequestParam String name,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size);

    @GetMapping("/{id}")
    @ApiOperation(value = "Get building by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = BuildingDto.class),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    BuildingDto getBuildingById(@PathVariable("id") Long id);

    @GetMapping("/{id}/info")
    @ApiOperation(value = "Get building info by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = BuildingDto.class),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class),
    })
    BuildingDto getBuildingInfoById(@PathVariable("id") Long id);
}