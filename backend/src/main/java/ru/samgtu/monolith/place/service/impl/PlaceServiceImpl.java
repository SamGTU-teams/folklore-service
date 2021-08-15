package ru.samgtu.monolith.place.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.samgtu.monolith.place.exception.NoSuchPlaceException;
import ru.samgtu.monolith.place.model.persistence.Place;
import ru.samgtu.monolith.place.model.persistence.PlaceLob;
import ru.samgtu.monolith.place.repository.PlaceLobRepository;
import ru.samgtu.monolith.place.repository.PlaceRepository;
import ru.samgtu.monolith.place.service.PlaceService;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.util.Collection;
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
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository repository;

    private final PlaceLobRepository lobRepository;

    @Override
    public Page<Place> getPlaces(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Place> findPlacesByTags(Set<Tag> tags,
                                        Pageable pageable) {
        return repository.findByTagsIn(tags, pageable);
    }

    @Override
    public Page<Place> findPlacesByName(String name,
                                        Pageable pageable) {
        return repository.findByNameStartsWithIgnoreCase(name, pageable);
    }

    @Override
    public Place findPlacesById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.warn("Building with id = {} does not exists", id);
            return new NoSuchPlaceException("Building does not exists");
        });
    }

    @Override
    public PlaceLob findPlacesInfoById(Long id) {
        return lobRepository.findById(id).orElseThrow(() -> {
            log.warn("Building with id = {} does not exists", id);
            return new NoSuchPlaceException("Building does not exists");
        });
    }

    @Override
    public Collection<Place> findPlacesByIds(Collection<Long> ids) {
        return repository.findAllById(ids);
    }
}
