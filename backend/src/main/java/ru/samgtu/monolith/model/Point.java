package ru.samgtu.monolith.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.samgtu.monolith.config.JacksonViews;

/**
 * Creation date: 16.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Data
public class Point {
    @JsonView(JacksonViews.DataWithoutLob.class)
    private double lat;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private double lon;
}
