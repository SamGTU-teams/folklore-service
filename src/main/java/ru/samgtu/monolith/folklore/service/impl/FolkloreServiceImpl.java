package ru.samgtu.monolith.folklore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.samgtu.monolith.folklore.model.persistence.Building;
import ru.samgtu.monolith.folklore.service.FolkloreService;
import ru.samgtu.monolith.tag.model.persistence.Tag;
import ru.samgtu.monolith.tag.service.TagService;

import java.util.ArrayList;
import java.util.List;

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
    private final TagService tagService;

    @Override
    public List<Building> getBuildingsByTags(List<Tag> tags,
                                             int page,
                                             int size) {
        ArrayList<Building> list = new ArrayList<>();
        list.add(getBuildingById(0L));
        return list;
    }

    @Override
    public List<Building> getBuildingsByName(String name,
                                             int page,
                                             int size) {
        return getBuildingsByTags(null, page, size);
    }

    @Override
    public Building getBuildingById(Long id) {
        Building building = new Building();
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(tagService.getTagById(0L));
        building.setTags(tags);
        building.setName("default name");
        building.setId(id);
        building.setAddress("default address");
        building.setImageUrl("defaultImageUrl");
        building.setLabelUrl("defaultLabelUrl");
        building.setLat(0d);
        building.setLon(0d);
        return building;
    }
}
