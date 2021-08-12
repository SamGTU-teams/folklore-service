package ru.samgtu.monolith.activity.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.activity.repository.ActivityRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    public void getByTags(){

    }

    public Page<Activity> findByPars(String name,
                                     LocalDateTime from,
                                     LocalDateTime to,
                                     Pageable pageable){
        return null;
    }

}
