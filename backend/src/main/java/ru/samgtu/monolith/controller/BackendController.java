package ru.samgtu.monolith.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Creation date: 13.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class BackendController {
    @RequestMapping(value = "{backend.mapping:^(?!index\\.html|api).+$}")
    public String redirectApi() {
        log.info("URL entered directly into the Browser, so we need to redirect...");
        return "forward:/";
    }
}
