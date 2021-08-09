package ru.samgtu.monolith.tag.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@ApiModel("Tag")
@Data
public class TagDto {
    private Long id;

    private String name;
}
