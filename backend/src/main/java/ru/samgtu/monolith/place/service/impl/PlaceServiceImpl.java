package ru.samgtu.monolith.place.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.samgtu.monolith.place.exception.NoSuchPlaceException;
import ru.samgtu.monolith.place.model.persistence.Place;
import ru.samgtu.monolith.place.repository.PlaceRepository;
import ru.samgtu.monolith.place.service.PlaceService;
import ru.samgtu.monolith.tag.util.TagUtil;

import java.util.Collection;

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

    private final ParentNodesExtractorFromTag extractor;

    @Override
    public Page<Place> getPlaces(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Place> findPlacesByTags(Collection<String> tags,
                                        Pageable pageable) {
//        TODO: remove this
//        tags = tags.stream()
//                .flatMap(s -> {
//                    List<String> nodes = extractor.extractFrom(s);
//                    nodes.add(s);
//                    return nodes.stream();
//                })
//                .collect(Collectors.toSet());
//        return repository.findByTagsIdIn(tags, pageable);

//        FIXME: created example regex
//        "1(\\.\\S*)?" find all places with tags started with
//        TODO: optimize regex

        String regex = tags.stream()
                .map(str -> str.replaceAll("\\.", "\\.").concat("(\\.\\S*)?"))
                .collect(Collectors.joining("|", "(", ")"));

        return repository.findByTagIdRegex(regex, pageable);
    }

    @Override
    public Page<Place> findPlacesByName(String name,
                                        Pageable pageable) {
        return repository.findByNameStartsWithIgnoreCase(name, pageable);
    }

    @Override
    public Place findPlacesById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.warn("Place with id = {} does not exists", id);
            return new NoSuchPlaceException("Building does not exists");
        });
    }

    @Override
    public Place findPlacesInfoById(Long id) {
        return repository.findInfoById(id).orElseThrow(() -> {
            log.warn("Place with id = {} does not exists", id);
            return new NoSuchPlaceException("Building does not exists");
        });
    }

    @Override
    public Collection<Place> findPlacesByIds(Collection<Long> ids) {
        return repository.findAllById(ids);
    }
}
