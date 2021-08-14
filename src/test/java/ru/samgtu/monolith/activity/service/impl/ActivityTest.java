package ru.samgtu.monolith.activity.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.activity.model.persistence.ScheduledActivity;
import ru.samgtu.monolith.activity.service.ActivityService;

import javax.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@ActiveProfiles("romzes")
public class ActivityTest {

    @Autowired
    ActivityService activityService;

    @Autowired
    EntityManager entityManager;

    @Test
    public void testScheduledInsert(){
        LocalDateTime ld = LocalDateTime.parse("2010-10-10T12:00");
        System.out.println();
        List<Activity> result = activityService.findByDateAfterThan(LocalDateTime.now(), 2, 1);
        assertEquals(result.size(), 2);
    }

    @Test
    public void testScheduledActivity(){
        List<ScheduledActivity> activities = activityService.findScheduledById(1L, PageRequest.of(0, 10)).toList();
        System.out.println();
    }

}
