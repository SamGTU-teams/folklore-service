package ru.samgtu.monolith.activity.model.persistence;

import lombok.*;
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
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Activity {
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private Double lat;

    private Double lon;

    @ToString.Exclude
    private List<TagDto> tags;

    private String address;

    private String imageUrl;

    private String labelUrl;

    private BuildingDto building;

    private Duration duration;

    private InteractionType interactionType;
}
