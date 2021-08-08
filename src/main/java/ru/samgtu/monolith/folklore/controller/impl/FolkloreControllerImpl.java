package ru.samgtu.monolith.folklore.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.BoundMapperFacade;
import org.springframework.web.bind.annotation.RestController;
import ru.samgtu.monolith.folklore.controller.FolkloreController;
import ru.samgtu.monolith.folklore.model.dto.BuildingDto;
import ru.samgtu.monolith.folklore.model.persistence.Building;
import ru.samgtu.monolith.folklore.service.FolkloreService;
import ru.samgtu.monolith.tag.model.dto.TagDto;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.util.List;
import java.util.stream.Collectors;

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

    private final BoundMapperFacade<Building, BuildingDto> buildingMapper;

    private final BoundMapperFacade<Tag, TagDto> tagMapper;

    @Override
    public List<BuildingDto> getBuildingsByTags(List<TagDto> tagsDto,
                                                int page,
                                                int size) {
        List<Tag> tags = tagsDto.stream().map(tagMapper::mapReverse).collect(Collectors.toList());
        List<Building> buildings = service.getBuildingsByTags(tags, page, size);
        return mapBuilding(buildings);
    }

    @Override
    public List<BuildingDto> getBuildingsByName(String name,
                                                int page,
                                                int size) {
        List<Building> buildings = service.getBuildingsByName(name, page, size);
        return mapBuilding(buildings);
    }

    @Override
    public BuildingDto getBuildingById(Long id) {
        Building building = service.getBuildingById(id);
        return buildingMapper.map(building);
    }

    private List<BuildingDto> mapBuilding(List<Building> buildings) {
        return buildings.stream().map(buildingMapper::map).collect(Collectors.toList());
    }
}
