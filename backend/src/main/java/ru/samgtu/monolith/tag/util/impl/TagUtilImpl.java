package ru.samgtu.monolith.tag.util.impl;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.samgtu.monolith.tag.util.TagUtil;

import java.util.*;
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
        List<String> sortedTags = tags.stream().distinct().sorted(Comparator.comparingInt(o -> o.split("\\.").length)).collect(Collectors.toList());
        List<String> resulting = new ArrayList<>();
        while (sortedTags.size() > 0) {
            List<String> middle = new ArrayList<>();
            String compared = sortedTags.get(0);
            middle.add(compared);
            for (int i = 1; i < sortedTags.size(); i++) {
                String comparing = sortedTags.get(i);
                if (comparing.matches(compared + "\\..+")) {
                    middle.add(comparing);
                }
            }
            resulting.add(compared);
            for (String deleting :
                    middle) {
                sortedTags.remove(deleting);
            }
        }
        return resulting;
    }

    @Override
    public String createRegex(Collection<String> tags) {
        return tags.stream()
                .map(str -> str.replaceAll("\\.", "\\.").concat("(\\.\\S*)?"))
                .collect(Collectors.joining("|", "(", ")"));
    }
}
