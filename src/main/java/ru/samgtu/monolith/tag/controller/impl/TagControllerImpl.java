package ru.samgtu.monolith.tag.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFacade;
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

    private final MapperFacade mapper;

    @Override
    public List<TagDto> getTags(int page,
                                int size) {
        List<Tag> tags = service.getTags(page, size);
        return mapper.mapAsList(tags, TagDto.class);
    }

    @Override
    public List<TagDto> getTagsByName(String name,
                                      int page,
                                      int size) {
        List<Tag> tags = service.getTagsByName(name, page, size);
        return mapper.mapAsList(tags, TagDto.class);
    }

    @Override
    public TagDto getTagById(Long id) {
        Tag tag = service.getTagById(id);
        return mapper.map(tag, TagDto.class);
    }
}
