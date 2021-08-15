package ru.samgtu.monolith.tag.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;
import ru.samgtu.monolith.tag.controller.TagController;
import ru.samgtu.monolith.tag.model.dto.TagDto;
import ru.samgtu.monolith.tag.model.persistence.Tag;
import ru.samgtu.monolith.tag.service.TagService;

import java.util.Collection;

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
    public Page<TagDto> getTags(int page,
                                int size) {
        PageRequest pageRequest = createPageRequest(page, size);
        Page<Tag> tags = service.getTags(pageRequest);
        return mapPage(tags);
    }

    @Override
    public Page<TagDto> findTagsByName(String name,
                                       int page,
                                       int size) {
        PageRequest pageRequest = createPageRequest(page, size);
        Page<Tag> tags = service.findTagsByName(name, pageRequest);
        return mapPage(tags);
    }

    @Override
    public TagDto findTagById(Long id) {
        Tag tag = service.findTagById(id);
        return mapper.map(tag, TagDto.class);
    }

    @Override
    public Collection<TagDto> findTagsByIds(Collection<Long> ids) {
        Collection<Tag> tags = service.findTagsByIds(ids);
        return mapper.mapAsList(tags, TagDto.class);
    }

    private PageRequest createPageRequest(int page, int size) {
        return PageRequest.of(page, size, Sort.by("id").ascending());
    }

    private Page<TagDto> mapPage(Page<Tag> page) {
        return page.map(tag -> mapper.map(tag, TagDto.class));
    }
}
