package ru.samgtu.monolith.place.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import ru.samgtu.monolith.config.JacksonViews;
import ru.samgtu.monolith.model.Point;
import ru.samgtu.monolith.tag.model.dto.TagDto;

import java.util.List;
import java.util.Set;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@ApiModel("Place")
@Data
public class PlaceDto {
    @JsonView(JacksonViews.DataWithoutLob.class)
    private Long id;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private String name;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private Point point;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private String address;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private String imageUrl;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private String labelUrl;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private Set<TagDto> tags;

    @JsonView(JacksonViews.DataWithLob.class)
    private List<String> urls;

    @JsonView(JacksonViews.DataWithLob.class)
    private String description;
}
