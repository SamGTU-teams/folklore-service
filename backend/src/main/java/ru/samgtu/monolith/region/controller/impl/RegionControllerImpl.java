package ru.samgtu.monolith.region.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import ru.samgtu.monolith.region.controller.RegionController;
import ru.samgtu.monolith.region.model.dto.RegionDto;
import ru.samgtu.monolith.region.model.persistence.Region;
import ru.samgtu.monolith.region.service.RegionService;

/**
 * Creation date: 16.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class RegionControllerImpl implements RegionController {
    private final RegionService service;

    private final MapperFacade mapper;

    @Override
    public RegionDto findRegionById(Long id) {
        Region region = service.findRegionById(id);
        return mapper.map(region, RegionDto.class);
    }

    @Override
    public Page<RegionDto> findRegionsByName(String name, int page, int size) {
        Pageable pageRequest = createPageRequest(page, size);
        Page<Region> regions = service.findRegionsByName(name, pageRequest);
        return mapPage(regions);
    }

    @Override
    public Page<RegionDto> getRegions(int page, int size) {
        Pageable pageRequest = createPageRequest(page, size);
        Page<Region> regions = service.getRegions(pageRequest);
        return mapPage(regions);
    }

    private Page<RegionDto> mapPage(Page<Region> page) {
        return page.map(region -> mapper.map(region, RegionDto.class));
    }

    private Pageable createPageRequest(int page, int size) {
        return PageRequest.of(page, size);
    }
}
