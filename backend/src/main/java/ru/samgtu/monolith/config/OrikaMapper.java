package ru.samgtu.monolith.config;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.samgtu.monolith.activity.model.dto.ActivityDto;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.model.persistence.DescriptionAndUrlsLob;
import ru.samgtu.monolith.place.model.dto.PlaceDto;
import ru.samgtu.monolith.place.model.persistence.Place;
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
                .customize(new CustomMapper<Place, PlaceDto>() {
                    @Override
                    public void mapAtoB(Place place, PlaceDto placeDto, MappingContext context) {
                        DescriptionAndUrlsLob lob = place.getLob();
                        if (isNull(lob)) {
                            return;
                        }
                        placeDto.setDescription(lob.getDescription());
                        List<String> urls = splitUrls(lob.getMediaUrls());
                        placeDto.setUrls(urls);
                    }
                })
                .field("lat", "point.lat")
                .field("lon", "point.lon")
                .byDefault()
                .register();

        factory.classMap(Activity.class, ActivityDto.class)
                .byDefault()
                .register();

//        factory.classMap(ActivityLob.class, ActivityDto.class)
//                .customize(new CustomMapper<ActivityLob, ActivityDto>() {
//                    @Override
//                    public void mapAtoB(ActivityLob activityLob, ActivityDto activityDto, MappingContext context) {
//                        String clob = activityLob.getMediaUrls();
//                        List<String> urls = splitUrls(clob);
//                        activityDto.setUrls(urls);
//                    }
//                })
//                .field("activity.id", "id")
//                .field("activity.name", "name")
//                .field("activity.lon", "lon")
//                .field("activity.lat", "lat")
//                .field("activity.address", "address")
//                .field("activity.duration", "duration")
//                .field("activity.imageUrl", "imageUrl")
//                .field("activity.labelUrl", "labelUrl")
//                .field("activity.tags", "tags")
//                .field("activity.place", "place")
//                .byDefault()
//                .register();
    }

    private List<String> splitUrls(String clob) {
        return (isNull(clob) || (clob = clob.trim()).isEmpty()) ?
                Collections.emptyList() : Arrays.asList(clob.split(urlSeparator));
    }
}
