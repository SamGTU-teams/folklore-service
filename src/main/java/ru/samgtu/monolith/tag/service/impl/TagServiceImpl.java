package ru.samgtu.monolith.tag.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.samgtu.monolith.tag.model.persistence.Tag;
import ru.samgtu.monolith.tag.repository.TagRepository;
import ru.samgtu.monolith.tag.service.TagService;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TagServiceImpl implements TagService {
    private final TagRepository repository;

    @Override
    public List<Tag> getTags(Pageable pageable) {
        Page<Tag> tags = repository.findAll(pageable);
        return tags.getContent();
    }

    @Override
    public List<Tag> getTagsByName(String name,
                                   Pageable pageable) {
        Page<Tag> tags = repository.findByNameIgnoreCase(name, pageable);
        return tags.getContent();
    }

    @Override
    public Tag getTagById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("Tag with id = {} does not exists", id);
                    return new NoSuchElementException("Tag does not exists");
                });
    }
}
