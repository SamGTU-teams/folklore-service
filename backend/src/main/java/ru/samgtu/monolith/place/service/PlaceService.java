package ru.samgtu.monolith.place.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.samgtu.monolith.place.model.persistence.Place;

import java.util.Collection;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface PlaceService {
    Page<Place> getPlaces(Pageable pageable);

    Page<Place> findPlacesByTags(Collection<String> tags,
                                 Pageable pageable);

    Page<Place> findPlacesByName(String name,
                                 Pageable pageable);

    Place findPlacesById(Long id);

    Place findPlacesInfoById(Long id);

    Collection<Place> findPlacesByIds(Collection<Long> ids);

    Page<Place> findByRegionId(Long id, Pageable pageable);
}
