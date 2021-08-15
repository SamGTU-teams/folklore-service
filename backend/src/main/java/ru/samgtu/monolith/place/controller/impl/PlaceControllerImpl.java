package ru.samgtu.monolith.place.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;
import ru.samgtu.monolith.place.controller.PlaceController;
import ru.samgtu.monolith.place.model.dto.PlaceDto;
import ru.samgtu.monolith.place.model.persistence.Place;
import ru.samgtu.monolith.place.model.persistence.PlaceLob;
import ru.samgtu.monolith.place.service.PlaceService;
import ru.samgtu.monolith.tag.model.dto.TagDto;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.util.Collection;
import java.util.Set;

import static java.util.Objects.isNull;

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
    public Page<PlaceDto> findPlacesByTags(Set<TagDto> tagsDto,
                                           int page,
                                           int size) {
        Page<Place> places;
        PageRequest pageRequest = createPageRequest(page, size);
        if (isNull(tagsDto)) {
            places = service.getPlaces(pageRequest);
        } else {
            Set<Tag> tags = mapper.mapAsSet(tagsDto, Tag.class);
            places = service.findPlacesByTags(tags, pageRequest);
        }
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
        PlaceLob place = service.findPlacesInfoById(id);
        return mapper.map(place, PlaceDto.class);
    }

    @Override
    public Collection<PlaceDto> findPlacesByIds(Collection<Long> ids) {
        Collection<Place> places = service.findPlacesByIds(ids);
        return mapper.mapAsList(places, PlaceDto.class);
    }

    private PageRequest createPageRequest(int page, int size) {
        return PageRequest.of(page, size);
    }

    private Page<PlaceDto> mapPage(Page<Place> page) {
        return page.map(building -> mapper.map(building, PlaceDto.class));
    }
}
