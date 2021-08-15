package ru.samgtu.monolith.tag.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.samgtu.monolith.config.JacksonViews;
import ru.samgtu.monolith.model.ExceptionInfo;
import ru.samgtu.monolith.tag.model.dto.TagDto;

import java.util.Collection;

/**
 * Creation date: 06.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RequestMapping(TagController.MAPPING)
public interface TagController {
    String MAPPING = "api/tags";

    @GetMapping
    @ApiOperation(value = "Get tags")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Page<TagDto> getTags(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size);

    @GetMapping("/search")
    @ApiOperation(value = "Get tags by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Page<TagDto> getTagsByName(@RequestParam String name,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size);

    @GetMapping("/{id}")
    @ApiOperation(value = "Get tag by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = TagDto.class),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    TagDto getTagById(@PathVariable("id") Long id);

    @PostMapping("/ids")
    @ApiOperation(value = "Get tags by ids")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = TagDto.class),
            @ApiResponse(code = 404, message = "", response = ExceptionInfo.class)
    })
    @JsonView(JacksonViews.DataWithoutLob.class)
    Collection<TagDto> getTagsByIds(@RequestBody Collection<Long> ids);
}
