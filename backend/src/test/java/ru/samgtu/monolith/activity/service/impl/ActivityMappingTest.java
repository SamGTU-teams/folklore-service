package ru.samgtu.monolith.activity.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import ru.samgtu.monolith.activity.controller.ActivityController;
import ru.samgtu.monolith.activity.model.dto.ActivityDto;

@SpringBootTest
@ActiveProfiles("romzes")
public class ActivityMappingTest {

    @Autowired
    ActivityController controller;

    @Test
    void testDtoMapping() {
        Page<ActivityDto> activityDtos = controller.findActivitiesByName(null, 1, 2);
        System.out.println();
    }

}
