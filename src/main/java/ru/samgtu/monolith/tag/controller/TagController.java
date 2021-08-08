package ru.samgtu.monolith.tag.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.samgtu.monolith.tag.model.dto.TagDto;

import java.util.List;

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
    @ApiOperation(value = "Get tags", responseContainer = "List", response = TagDto.class)
    List<TagDto> getTags(@PageableDefault Pageable pageable);

    @GetMapping("/search")
    @ApiOperation(value = "Get tags by name", response = TagDto.class)
    List<TagDto> getTagsByName(@RequestParam String name,
                               @PageableDefault Pageable pageable);

    @GetMapping("/{id}")
    @ApiOperation(value = "Get tag by id", response = TagDto.class)
    TagDto getTagById(@PathVariable("id") Long id);
}
