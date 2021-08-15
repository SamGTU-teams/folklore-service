package ru.samgtu.monolith.activity.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.activity.model.persistence.ScheduledActivity;
import ru.samgtu.monolith.activity.service.ActivityService;
import ru.samgtu.monolith.activity.service.ScheduledActivityService;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("romzes")
public class ActivityTest {


    @Autowired
    ScheduledActivityService scheduledActivityService;

    @Autowired
    ActivityService activityService;

    @Autowired
    EntityManager entityManager;

    @Test
    public void testScheduledInsert() {
        PageRequest pageRequest = PageRequest.of(2, 1);
        Page<Activity> result = activityService.findByDateAfterThan(LocalDateTime.now(), pageRequest);
        assertEquals(result.getNumberOfElements(), 2);
    }

    @Test
    public void testScheduledActivity(){
        Page<ScheduledActivity> activities = scheduledActivityService.findByNumericId(2L, PageRequest.of(0, 5));

    }

}
