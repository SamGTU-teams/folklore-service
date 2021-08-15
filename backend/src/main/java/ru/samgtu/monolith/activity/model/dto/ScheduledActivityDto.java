package ru.samgtu.monolith.activity.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import ru.samgtu.monolith.activity.model.ActivityStatus;
import ru.samgtu.monolith.config.JacksonViews;

import java.time.LocalDateTime;

/**
 * Creation date: 08.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@ApiModel("ScheduledActivity")
@Data
public class ScheduledActivityDto {
    @JsonView(JacksonViews.DataWithoutLob.class)
    private ActivityDto activityDto;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private LocalDateTime dateTime;

    @JsonView(JacksonViews.DataWithoutLob.class)
    private ActivityStatus status;
}
