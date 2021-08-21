package ru.samgtu.monolith.region.model.dto;

import lombok.Data;

/**
 * Creation date: 16.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Data
public class RegionDto {
    private Long id;

    private String name;

    private String points;

    private String imageUrl;
}
