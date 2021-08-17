package ru.samgtu.monolith.tag.util.impl;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.samgtu.monolith.tag.util.TagUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Creation date: 16.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
@Setter
public class TagUtilImpl implements TagUtil {
    @Value("${tag.node.delimiter:\\.}")
    private String delimiter;

    @Override
    public List<String> extractFrom(String tag) {
        String[] nodes = tag.split(delimiter);
        int countParents = nodes.length - 1;
        List<String> tags = new ArrayList<>(countParents);
        if (countParents != 0) {
            StringBuilder nodeBuilder = new StringBuilder(nodes[0]);
            tags.add(nodeBuilder.toString());
            for (int nodeIndex = 1; nodeIndex < countParents; nodeIndex++) {
                nodeBuilder.append('.').append(nodes[nodeIndex]);
                tags.add(nodeBuilder.toString());
            }
        }
        return tags;
    }

    @Override
    public Collection<String> optimizeTags(Collection<String> tags) {
        return tags;
    }

    @Override
    public String createRegex(Collection<String> tags) {
        return tags.stream()
                .map(str -> str.replaceAll("\\.", "\\.").concat("(\\.\\S*)?"))
                .collect(Collectors.joining("|", "(", ")"));
    }
}
