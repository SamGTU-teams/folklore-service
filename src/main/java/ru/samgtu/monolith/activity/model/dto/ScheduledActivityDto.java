package ru.samgtu.monolith.activity.model.dto;

import lombok.Data;
import ru.samgtu.monolith.activity.model.ActivityStatus;

import java.time.LocalDateTime;

/**
 * Creation date: 08.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Data
public class ScheduledActivityDto {
    private ActivityDto activityDto;

    private LocalDateTime dateTime;

    private ActivityStatus status;
}
