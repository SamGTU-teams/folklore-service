package ru.samgtu.monolith.folklore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.samgtu.monolith.folklore.exception.NoSuchBuildingException;
import ru.samgtu.monolith.folklore.model.persistence.Building;
import ru.samgtu.monolith.folklore.model.persistence.BuildingLob;
import ru.samgtu.monolith.folklore.repository.BuildingLobRepository;
import ru.samgtu.monolith.folklore.repository.BuildingRepository;
import ru.samgtu.monolith.folklore.service.FolkloreService;
import ru.samgtu.monolith.tag.model.persistence.Tag;

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
    private final BuildingLobRepository lobRepository;

    @Override
    public Page<Building> getBuildingsByTags(Set<Tag> tags,
                                             Pageable pageable) {
        return repository.findByTagsIn(tags, pageable);
    }

    @Override
    public Page<Building> getBuildingsByName(String name,
                                             Pageable pageable) {
        return repository.findByNameStartsWithIgnoreCase(name, pageable);
    }

    @Override
    public Building getBuildingById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.warn("Building with id = {} does not exists", id);
            return new NoSuchBuildingException("Building does not exists");
        });
    }

    @Override
    public BuildingLob getBuildingInfoById(Long id) {
        return lobRepository.findById(id).orElseThrow(() -> {
            log.warn("Building with id = {} does not exists", id);
            return new NoSuchBuildingException("Building does not exists");
        });
    }
}
