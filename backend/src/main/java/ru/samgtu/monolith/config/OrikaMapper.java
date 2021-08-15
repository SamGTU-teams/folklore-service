package ru.samgtu.monolith.config;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.samgtu.monolith.activity.model.dto.ActivityDto;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.activity.model.persistence.ActivityLob;
import ru.samgtu.monolith.place.model.dto.PlaceDto;
import ru.samgtu.monolith.place.model.persistence.Place;
import ru.samgtu.monolith.place.model.persistence.PlaceLob;
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

        factory.classMap(Place.class, PlaceDto.class)
                .byDefault()
                .register();

        factory.classMap(Activity.class, ActivityDto.class)
                .byDefault()
                .register();

        factory.classMap(PlaceLob.class, PlaceDto.class)
                .customize(new CustomMapper<PlaceLob, PlaceDto>() {
                    @Override
                    public void mapAtoB(PlaceLob building, PlaceDto placeDto, MappingContext context) {
                        String clob = building.getMediaUrls();
                        List<String> urls = splitUrls(clob);
                        placeDto.setUrls(urls);
                    }
                })
                .field("place.id", "id")
                .field("place.name", "name")
                .field("place.lon", "lon")
                .field("place.lat", "lat")
                .field("place.address", "address")
                .field("place.imageUrl", "imageUrl")
                .field("place.labelUrl", "labelUrl")
                .field("place.tags", "tags")
                .byDefault()
                .register();

        factory.classMap(ActivityLob.class, ActivityDto.class)
                .customize(new CustomMapper<ActivityLob, ActivityDto>() {
                    @Override
                    public void mapAtoB(ActivityLob activityLob, ActivityDto activityDto, MappingContext context) {
                        String clob = activityLob.getMediaUrls();
                        List<String> urls = splitUrls(clob);
                        activityDto.setUrls(urls);
                    }
                })
                .field("activity.id", "id")
                .field("activity.name", "name")
                .field("activity.lon", "lon")
                .field("activity.lat", "lat")
                .field("activity.address", "address")
                .field("activity.duration", "duration")
                .field("activity.imageUrl", "imageUrl")
                .field("activity.labelUrl", "labelUrl")
                .field("activity.tags", "tags")
                .field("activity.place", "place")
                .byDefault()
                .register();
    }

    private List<String> splitUrls(String clob) {
        return (isNull(clob) || (clob = clob.trim()).isEmpty()) ?
                Collections.emptyList() : Arrays.asList(clob.split(urlSeparator));
    }
}
