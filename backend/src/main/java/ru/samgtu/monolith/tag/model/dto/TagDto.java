package ru.samgtu.monolith.tag.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import ru.samgtu.monolith.config.JacksonViews;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@ApiModel("Tag")
@Data
public class TagDto {
    @JsonView(JacksonViews.DataWithoutLob.class)
    private String id;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private String name;
}
