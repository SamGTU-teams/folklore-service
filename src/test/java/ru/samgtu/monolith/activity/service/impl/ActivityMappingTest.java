package ru.samgtu.monolith.activity.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.samgtu.monolith.activity.controller.ActivityController;
import ru.samgtu.monolith.activity.model.dto.ActivityDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
@ActiveProfiles("romzes")
public class ActivityMappingTest {

    @Autowired
    ActivityController controller;

    @Test
    void testDtoMapping(){
        List<ActivityDto> activityDtos = controller.
                getActivitiesByParams(null, LocalDateTime.of(LocalDate.of(2021, 1, 1), LocalTime.of(11, 0)), 1, 2);
        System.out.println();
    }

}
