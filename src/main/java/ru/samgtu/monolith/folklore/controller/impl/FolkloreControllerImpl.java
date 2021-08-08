package ru.samgtu.monolith.folklore.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import ru.samgtu.monolith.folklore.controller.FolkloreController;
import ru.samgtu.monolith.folklore.model.dto.BuildingDto;
import ru.samgtu.monolith.folklore.model.persistence.Building;
import ru.samgtu.monolith.folklore.service.FolkloreService;
import ru.samgtu.monolith.tag.model.dto.TagDto;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.util.List;

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
    public List<BuildingDto> getBuildingsByTags(List<TagDto> tagsDto,
                                                Pageable pageable) {
        List<Tag> tags = mapper.mapAsList(tagsDto, Tag.class);
        List<Building> buildings = service.getBuildingsByTags(tags, pageable);
        return mapper.mapAsList(buildings, BuildingDto.class);
    }

    @Override
    public List<BuildingDto> getBuildingsByName(String name,
                                                Pageable pageable) {
        List<Building> buildings = service.getBuildingsByName(name, pageable);
        return mapper.mapAsList(buildings, BuildingDto.class);
    }

    @Override
    public BuildingDto getBuildingById(Long id) {
        Building building = service.getBuildingById(id);
        return mapper.map(building, BuildingDto.class);
    }
}
