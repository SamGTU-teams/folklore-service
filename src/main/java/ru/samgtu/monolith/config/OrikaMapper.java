package ru.samgtu.monolith.config;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.samgtu.monolith.activity.model.dto.ActivityDto;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.folklore.model.dto.BuildingDto;
import ru.samgtu.monolith.folklore.model.persistence.Building;
import ru.samgtu.monolith.tag.model.dto.TagDto;
import ru.samgtu.monolith.tag.model.persistence.Tag;

/**
 * Creation date: 08.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Component
public class OrikaMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Tag.class, TagDto.class)
                .byDefault()
                .register();

        factory.classMap(Building.class, BuildingDto.class)
                .byDefault()
                .register();

        factory.classMap(Activity.class, ActivityDto.class)
                .byDefault()
                .register();
    }
}
