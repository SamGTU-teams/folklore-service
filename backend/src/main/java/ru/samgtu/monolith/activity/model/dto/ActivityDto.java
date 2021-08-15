package ru.samgtu.monolith.activity.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import ru.samgtu.monolith.config.JacksonViews;
import ru.samgtu.monolith.folklore.model.dto.BuildingDto;
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
    private Double lat;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private Double lon;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private List<TagDto> tags;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private String address;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private String imageUrl;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private String labelUrl;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private BuildingDto building;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private Duration duration;

    @JsonView(JacksonViews.DataWithLob.class)
    private List<String> urls;

    @JsonView(JacksonViews.DataWithLob.class)
    private String description;
}
