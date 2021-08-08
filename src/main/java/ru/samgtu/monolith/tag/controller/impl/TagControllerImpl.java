package ru.samgtu.monolith.tag.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.BoundMapperFacade;
import org.springframework.web.bind.annotation.RestController;
import ru.samgtu.monolith.tag.controller.TagController;
import ru.samgtu.monolith.tag.model.dto.TagDto;
import ru.samgtu.monolith.tag.model.persistence.Tag;
import ru.samgtu.monolith.tag.service.TagService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class TagControllerImpl implements TagController {
    private final TagService service;

    private final BoundMapperFacade<Tag, TagDto> mapper;

    @Override
    public List<TagDto> getTags(int page,
                                int size) {
        List<Tag> tags = service.getTags(page, size);
        return mapTags(tags);
    }

    @Override
    public List<TagDto> getTagsByName(String name,
                                      int page,
                                      int size) {
        List<Tag> tags = service.getTagsByName(name, page, size);
        return mapTags(tags);
    }

    @Override
    public TagDto getTagById(Long id) {
        Tag tag = service.getTagById(id);
        return mapper.map(tag);
    }

    private List<TagDto> mapTags(List<Tag> tags) {
        return tags.stream().map(mapper::map).collect(Collectors.toList());
    }
}
