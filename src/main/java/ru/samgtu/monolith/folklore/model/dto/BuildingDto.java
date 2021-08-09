package ru.samgtu.monolith.folklore.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import ru.samgtu.monolith.tag.model.dto.TagDto;

import java.util.List;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@ApiModel("Tag")
@Data
public class BuildingDto {
    private Long id;

    private String name;

    private Double lat;

    private Double lon;

    private List<TagDto> tags;

    private String address;

    private String imageUrl;

    private String labelUrl;
}
