package ru.samgtu.monolith.tag.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.samgtu.monolith.tag.model.dto.TagDto;

/**
 * Creation date: 06.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RequestMapping(TagController.MAPPING)
public interface TagController {
    String MAPPING = "tags";

    @GetMapping
    @ApiOperation(value = "Get tags")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    Page<TagDto> getTags(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size);

    @GetMapping("/search")
    @ApiOperation(value = "Get tags by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    Page<TagDto> getTagsByName(@RequestParam String name,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size);

    @GetMapping("/{id}")
    @ApiOperation(value = "Get tag by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = TagDto.class)
    })
    TagDto getTagById(@PathVariable("id") Long id);
}
