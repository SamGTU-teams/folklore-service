package ru.samgtu.monolith.config;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@Configuration
public class OrikaConfiguration {
    private final MapperFactory factory = registerFactory();

    private MapperFactory registerFactory() {
        DefaultMapperFactory factory = new DefaultMapperFactory.Builder().build();

        factory.classMap(Tag.class, TagDto.class)
                .byDefault()
                .register();

        factory.classMap(Building.class, BuildingDto.class)
                .byDefault()
                .register();

        return factory;
    }

    @Bean
    public BoundMapperFacade<Tag, TagDto> tagMapper() {
        return factory.getMapperFacade(Tag.class, TagDto.class);
    }

    @Bean
    public BoundMapperFacade<Building, BuildingDto> buildingMapper() {
        return factory.getMapperFacade(Building.class, BuildingDto.class);
    }
}
