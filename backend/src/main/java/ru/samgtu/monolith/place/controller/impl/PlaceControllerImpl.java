package ru.samgtu.monolith.place.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;
import ru.samgtu.monolith.model.PageJsonImpl;
import ru.samgtu.monolith.place.controller.PlaceController;
import ru.samgtu.monolith.place.model.dto.PlaceDto;
import ru.samgtu.monolith.place.model.persistence.Place;
import ru.samgtu.monolith.place.service.PlaceService;

import java.util.Collection;
import java.util.Set;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class PlaceControllerImpl implements PlaceController {
    private final PlaceService service;

    private final MapperFacade mapper;

    @Override
    public Page<PlaceDto> findPlacesByTags(Set<String> tags,
                                           int page,
                                           int size) {
        PageRequest pageRequest = createPageRequest(page, size);
        Page<Place> places = service.findPlacesByTags(tags, pageRequest);
        return mapPage(places);
    }

    @Override
    public Page<PlaceDto> findPlacesByName(String name,
                                           int page,
                                           int size) {
        PageRequest pageRequest = createPageRequest(page, size);
        Page<Place> places = service.findPlacesByName(name, pageRequest);
        return mapPage(places);
    }

    @Override
    public PlaceDto findPlacesById(Long id) {
        Place place = service.findPlacesById(id);
        return mapper.map(place, PlaceDto.class);
    }

    @Override
    public PlaceDto findPlacesInfoById(Long id) {
        Place place = service.findPlacesInfoById(id);
        return mapper.map(place, PlaceDto.class);
    }

    @Override
    public Collection<PlaceDto> findPlacesByIds(Collection<Long> ids) {
        Collection<Place> places = service.findPlacesByIds(ids);
        return mapper.mapAsList(places, PlaceDto.class);
    }

    @Override
    public Page<PlaceDto> findByRegionId(Long id, int page, int size) {
        PageRequest pageRequest = createPageRequest(page, size);
        return mapPage(service.findByRegionId(id, pageRequest));
    }

    private PageRequest createPageRequest(int page, int size) {
        return PageRequest.of(page, size);
    }

    private Page<PlaceDto> mapPage(Page<Place> page) {
        page = new PageJsonImpl<>(page);
        return page.map(building -> mapper.map(building, PlaceDto.class));
    }
}
