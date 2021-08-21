package ru.samgtu.monolith.activity.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import ru.samgtu.monolith.config.JacksonViews;
import ru.samgtu.monolith.model.Point;
import ru.samgtu.monolith.place.model.dto.PlaceDto;
import ru.samgtu.monolith.tag.model.dto.TagDto;

import java.time.Duration;
import java.util.List;

/**
 * Creation date: 08.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@ApiModel("Activity")
@Data
public class ActivityDto {
    @JsonView(JacksonViews.DataWithoutLob.class)
    private Long id;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private String name;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private Point point;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private List<TagDto> tags;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private String address;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private boolean canVisit;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private String imageUrl;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private String labelUrl;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private PlaceDto place;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private Duration duration;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private Long regionId;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private Double rating;

    @JsonView({JacksonViews.DataWithLob.class, JacksonViews.DataWithLobExcludedPlaceLob.class})
    private List<String> urls;

    @JsonView({JacksonViews.DataWithLob.class, JacksonViews.DataWithLobExcludedPlaceLob.class})
    private String description;
}
