package ru.samgtu.monolith.activity.model.dto;

import lombok.Data;
import ru.samgtu.monolith.activity.model.InteractionType;
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
@Data
public class ActivityDto {
    private Long id;

    private String name;

    private Double lat;

    private Double lon;

    private List<TagDto> tags;

    private String address;

    private String imageUrl;

    private String labelUrl;

    private BuildingDto building;

    private Duration duration;

    private InteractionType interactionType;

    private List<String> urls;

    private String description;
}
