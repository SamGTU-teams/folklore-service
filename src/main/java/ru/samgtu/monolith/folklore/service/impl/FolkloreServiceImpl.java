package ru.samgtu.monolith.folklore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.samgtu.monolith.folklore.model.persistence.Building;
import ru.samgtu.monolith.folklore.repository.BuildingRepository;
import ru.samgtu.monolith.folklore.service.FolkloreService;
import ru.samgtu.monolith.tag.model.persistence.Tag;
import ru.samgtu.monolith.tag.service.TagService;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FolkloreServiceImpl implements FolkloreService {
    private final BuildingRepository repository;

    @Override
    public Set<Building> getBuildingsByTags(Set<Tag> tags,
                                            Pageable pageable) {
        List<Building> content = repository.findByTagsIn(tags, pageable).getContent();
        return new HashSet<>(content);
    }

    @Override
    public Set<Building> getBuildingsByName(String name,
                                             Pageable pageable) {
        List<Building> content = repository.findByNameStartsWithIgnoreCase(name, pageable).getContent();
        return new HashSet<>(content);
    }

    @Override
    public Building getBuildingById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.warn("Building with id = {} does not exists", id);
            return new NoSuchElementException("Building does not exists");
        });
    }
}
