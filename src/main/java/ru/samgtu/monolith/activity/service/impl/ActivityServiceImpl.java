package ru.samgtu.monolith.activity.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.activity.repository.ActivityRepository;
import ru.samgtu.monolith.activity.service.ActivityService;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    @Override public void getByTags(){

    }

    @Override public Page<Activity> findByPars(String name,
                                     LocalDateTime from,
                                     LocalDateTime to,
                                     Pageable pageable){
        return null;
    }

}
