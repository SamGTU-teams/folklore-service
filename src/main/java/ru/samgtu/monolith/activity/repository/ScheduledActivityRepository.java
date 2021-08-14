package ru.samgtu.monolith.activity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.samgtu.monolith.activity.model.persistence.ScheduledActivity;
import ru.samgtu.monolith.activity.model.persistence.ScheduledId;

import java.util.List;

/**
 * Creation date: 14.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface ScheduledActivityRepository extends JpaRepository<ScheduledActivity, ScheduledId> {
    @Query("select sa.id from ScheduledActivity sa")
    List<ScheduledId> findAllIds();
}
