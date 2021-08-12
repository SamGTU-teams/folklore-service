package ru.samgtu.monolith.activity.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.samgtu.monolith.activity.model.persistence.Activity;

import java.time.LocalDateTime;

/**
 * Creation date: 12.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface ActivityService {
    void getByTags();

    Page<Activity> findByPars(String name,
                              LocalDateTime from,
                              LocalDateTime to,
                              Pageable pageable);
}
