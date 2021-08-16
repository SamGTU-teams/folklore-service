package ru.samgtu.monolith.region.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.samgtu.monolith.model.ExceptionInfo;
import ru.samgtu.monolith.region.model.dto.RegionDto;

/**
 * Creation date: 16.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RequestMapping(RegionController.MAPPING)
public interface RegionController {
    String MAPPING = "api/regions";

    @GetMapping("/{id}")
    @ApiOperation(value = "Get region by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = RegionDto.class),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    RegionDto findRegionById(@PathVariable("id") Long id);

    @GetMapping("/search")
    @ApiOperation(value = "Get regions by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = RegionDto.class),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    Page<RegionDto> findRegionsByName(@RequestParam String name,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size);

    @GetMapping
    @ApiOperation(value = "Get regions")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = RegionDto.class),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    Page<RegionDto> getRegions(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size);
}
