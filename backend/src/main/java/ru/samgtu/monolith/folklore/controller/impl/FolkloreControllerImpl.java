package ru.samgtu.monolith.folklore.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;
import ru.samgtu.monolith.folklore.controller.FolkloreController;
import ru.samgtu.monolith.folklore.model.dto.BuildingDto;
import ru.samgtu.monolith.folklore.model.persistence.Building;
import ru.samgtu.monolith.folklore.model.persistence.BuildingLob;
import ru.samgtu.monolith.folklore.service.FolkloreService;
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
public class FolkloreControllerImpl implements FolkloreController {
    private final FolkloreService service;

    private final MapperFacade mapper;

    @Override
    public Page<BuildingDto> findBuildingsByTags(Set<TagDto> tagsDto,
                                                 int page,
                                                 int size) {
        Page<Building> buildings;
        PageRequest pageRequest = createPageRequest(page, size);
        if (isNull(tagsDto)) {
            buildings = service.getBuildings(pageRequest);
        } else {
            Set<Tag> tags = mapper.mapAsSet(tagsDto, Tag.class);
            buildings = service.findBuildingsByTags(tags, pageRequest);
        }
        return mapPage(buildings);
    }

    @Override
    public Page<BuildingDto> findBuildingsByName(String name,
                                                 int page,
                                                 int size) {
        PageRequest pageRequest = createPageRequest(page, size);
        Page<Building> buildings = service.findBuildingsByName(name, pageRequest);
        return mapPage(buildings);
    }

    @Override
    public BuildingDto findBuildingById(Long id) {
        Building building = service.findBuildingById(id);
        return mapper.map(building, BuildingDto.class);
    }

    @Override
    public BuildingDto findBuildingInfoById(Long id) {
        BuildingLob building = service.findBuildingInfoById(id);
        return mapper.map(building, BuildingDto.class);
    }

    @Override
    public Collection<BuildingDto> findBuildingsByIds(Collection<Long> ids) {
        Collection<Building> buildings = service.findBuildingsByIds(ids);
        return mapper.mapAsList(buildings, BuildingDto.class);
    }

    private PageRequest createPageRequest(int page, int size) {
        return PageRequest.of(page, size, Sort.by("id").ascending());
    }

    private Page<BuildingDto> mapPage(Page<Building> page) {
        return page.map(building -> mapper.map(building, BuildingDto.class));
    }
}
