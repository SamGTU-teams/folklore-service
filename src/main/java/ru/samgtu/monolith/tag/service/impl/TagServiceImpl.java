package ru.samgtu.monolith.tag.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.samgtu.monolith.tag.exception.NoSuchTagException;
import ru.samgtu.monolith.tag.model.persistence.Tag;
import ru.samgtu.monolith.tag.repository.TagRepository;
import ru.samgtu.monolith.tag.service.TagService;

import java.util.Collection;
import java.util.List;

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
    public Page<Tag> getTags(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Tag> getTagsByName(String name,
                                   Pageable pageable) {
        return repository.findByNameStartsWithIgnoreCase(name, pageable);
    }

    @Override
    public Tag getTagById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("Tag with id = {} does not exists", id);
                    return new NoSuchTagException("Tag does not exists");
                });
    }

    @Override
    public List<Tag> getTagsByIds(Collection<Long> ids) {
        return repository.findAllById(ids);
    }
}
