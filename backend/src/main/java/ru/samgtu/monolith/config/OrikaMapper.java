package ru.samgtu.monolith.config;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.samgtu.monolith.folklore.model.dto.BuildingDto;
import ru.samgtu.monolith.folklore.model.persistence.Building;
import ru.samgtu.monolith.folklore.model.persistence.BuildingLob;
import ru.samgtu.monolith.tag.model.dto.TagDto;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * Creation date: 08.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Component
public class OrikaMapper extends ConfigurableMapper {
    @Value("${url.separator:\\s+}")
    private String urlSeparator;

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Tag.class, TagDto.class)
                .byDefault()
                .register();

        factory.classMap(Building.class, BuildingDto.class)
                .byDefault()
                .register();

        factory.classMap(BuildingLob.class, BuildingDto.class)
                .customize(new CustomMapper<BuildingLob, BuildingDto>() {
                    @Override
                    public void mapAtoB(BuildingLob building, BuildingDto buildingDto, MappingContext context) {
                        String clob = building.getMediaUrls();
                        List<String> urls = (isNull(clob) || (clob = clob.trim()).isEmpty()) ?
                                Collections.emptyList() : Arrays.asList(clob.split(urlSeparator));
                        buildingDto.setUrls(urls);
                    }
                })
                .field("building.id", "id")
                .field("building.name", "name")
                .field("building.lon", "lon")
                .field("building.lat", "lat")
                .field("building.address", "address")
                .field("building.imageUrl", "imageUrl")
                .field("building.labelUrl", "labelUrl")
                .field("building.tags", "tags")
                .byDefault()
                .register();
    }
}
