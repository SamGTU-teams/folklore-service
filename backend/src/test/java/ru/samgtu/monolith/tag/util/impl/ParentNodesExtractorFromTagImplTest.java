package ru.samgtu.monolith.tag.util.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Creation date: 16.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
class ParentNodesExtractorFromTagImplTest {
    static ParentNodesExtractorFromTagImpl extractor;

    static String tag;

    static String delimiter;

    @BeforeAll
    static void setUp() {
        tag = "1.2.3.4.5.6";
        delimiter = "\\.";
        extractor = new ParentNodesExtractorFromTagImpl();
        extractor.setDelimiter(delimiter);
    }

    @Test
    void extractFrom() {
        List<String> expected = Arrays.asList("1", "1.2", "1.2.3", "1.2.3.4", "1.2.3.4.5");
        List<String> parents = extractor.extractFrom(tag);

        assertThat(parents)
                .isNotNull()
                .isEqualTo(expected);
    }

    @Test
    void testTagsSet() {
        Set<String> tags = Stream.of("1.2", "1", "1.2.3", "2.2", "2.2.1").collect(Collectors.toSet());
//        if(tags.size() > 1) {
//            int size = tags.stream().min(Comparator.comparingInt(o -> o.length())).get().length();
//            Set<String> shortiest = tags.stream().filter(o -> o.length() == size).collect(Collectors.toSet());
//            Set<Integer> indexes = tags.stream().filter(o -> o.length() == size).map(o -> tags.indexOf(o)).collect(Collectors.toSet());
//            for(int i = 0;i < tags.size();i++){
//                if(indexes.contains(i) && tags.size() >= i + 1){
//                    continue;
//                }
//
//            }
//        }


//        Set<String> result = new HashSet<>();
//        List<Set<String>> setList = new ArrayList<>();
//        for (String tag:tags){
//            Set<String> s = new HashSet<>();
//            int steps = tag.split("\\.").length;
//            for (int i = 0; i < tags.size(); i++) {
//                String comparing = tags.get(i);
//                if(tag.equals(Arrays.stream(comparing.split("\\.")).limit(steps).collect(Collectors.joining(".")))){
//                    s.add(comparing);
//                }
//            }
//            setList.add(s);
//        }
//        for (Set<String> set:
//             setList) {
//            result.add(set.stream().min(Comparator.comparingInt(String::length)).get());
//        }
//        System.out.println(result);

        List<String> sortedTags = tags.stream().sorted(Comparator.comparingInt(o -> o.split("\\.").length)).collect(Collectors.toList());

        List<List<String>> resulting = new ArrayList<>();
        while (sortedTags.size() > 0) {
            List<String> middle = new ArrayList<>();
            String compared = sortedTags.get(0);
            middle.add(compared);
            for (int j = 1; j < sortedTags.size(); j++) {
                String comparing = sortedTags.get(j);
                if (comparing.matches(compared + "\\..+")) {
                    middle.add(comparing);
                }
            }
            resulting.add(middle);
            for (String deleting :
                    middle) {
                sortedTags.remove(deleting);
            }
//            sortedTags.removeIf(o -> middle.contains(o));
        }
        List<String> finalResult = resulting.stream().map(o -> o.iterator().next()).collect(Collectors.toList());
        System.out.println(finalResult);
    }


}