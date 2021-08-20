package ru.samgtu.monolith.region.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;

import java.util.ArrayList;

import static com.google.common.base.Predicates.or;

/**
 * Creation date: 08.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Configuration
public class RegionConfiguration {
    @Bean
    public Predicate<RequestHandler> activityApis() {
        ArrayList<Predicate<RequestHandler>> apis = new ArrayList<>();

        apis.add(RequestHandlerSelectors.basePackage("ru.samgtu.monolith.region.controller"));
        apis.add(RequestHandlerSelectors.basePackage("ru.samgtu.monolith.region.model.dto"));

        return or(apis);
    }
}
