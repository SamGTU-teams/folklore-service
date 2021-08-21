package ru.samgtu.monolith.region.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.samgtu.monolith.region.exception.NoSuchRegionException;
import ru.samgtu.monolith.region.model.persistence.Region;
import ru.samgtu.monolith.region.repository.RegionRepository;
import ru.samgtu.monolith.region.service.RegionService;

/**
 * Creation date: 16.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RegionServiceImpl implements RegionService {
    private final RegionRepository repository;

    @Override
    public Region findRegionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Region with id = {} does not exists", id);
                    return new NoSuchRegionException("Region does not exists");
                });
    }

    @Override
    public Page<Region> findRegionsByName(String name, Pageable pageable) {
        return repository.findByNameStartsWithIgnoreCase(name, pageable);
    }

    @Override
    public Page<Region> getRegions(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
