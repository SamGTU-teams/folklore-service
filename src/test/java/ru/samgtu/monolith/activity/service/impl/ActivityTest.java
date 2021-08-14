package ru.samgtu.monolith.activity.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.activity.service.ActivityService;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("romzes")
public class ActivityTest {

    @Autowired
    ActivityService activityService;

    @Autowired
    EntityManager entityManager;

    @Test
    public void testScheduledInsert() {
        LocalDateTime ld = LocalDateTime.parse("2010-10-10T12:00");
        System.out.println();
        PageRequest pageRequest = PageRequest.of(2, 1);
        List<Activity> result = activityService.findByDateAfterThan(LocalDateTime.now(), pageRequest);
        assertEquals(result.size(), 2);
    }

}
