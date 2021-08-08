package ru.samgtu.monolith.folklore.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.samgtu.monolith.folklore.model.dto.BuildingDto;
import ru.samgtu.monolith.tag.model.dto.TagDto;

import java.util.List;

/**
 * Creation date: 06.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RequestMapping(FolkloreController.MAPPING)
public interface FolkloreController {
    String MAPPING = "folklores";

    @GetMapping
    @ApiOperation(value = "Get buildings by tags")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", responseContainer = "List", response = BuildingDto.class)
    })
    List<BuildingDto> getBuildingsByTags(@RequestBody List<TagDto> tags,
                                         @PageableDefault Pageable pageable);

    @GetMapping("/search")
    @ApiOperation(value = "Get buildings by tags")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", responseContainer = "List", response = BuildingDto.class)
    })
    List<BuildingDto> getBuildingsByName(@RequestParam String name,
                                         @PageableDefault Pageable pageable);

    @GetMapping("/{id}")
    @ApiOperation(value = "Get building by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get building by id", response = BuildingDto.class)
    })
    BuildingDto getBuildingById(@PathVariable("id") Long id);
}
