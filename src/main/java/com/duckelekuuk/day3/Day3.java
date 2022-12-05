package com.duckelekuuk.day3;

import com.duckelekuuk.aoc.annotations.AOCDay;
import com.duckelekuuk.aoc.annotations.AOCInput;
import com.duckelekuuk.aoc.annotations.AOCPartOne;
import com.duckelekuuk.aoc.annotations.AOCPartTwo;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

@AOCDay(day = 3)
public class Day3 {


    @AOCInput
    private List<String> input;

    @AOCPartOne
    public int getPartOne() {
        return input.stream()
                .map(s -> Map.entry(s.substring(0, s.length() / 2), s.substring(s.length() / 2)))
                .map(m -> {
                    Set<String> first = Arrays.stream(m.getKey().split("")).collect(Collectors.toSet());
                    Set<String> second = Arrays.stream(m.getValue().split("")).collect(Collectors.toSet());
                    first.retainAll(second);
                    return first;
                })
                .flatMap(Collection::stream)
                .mapToInt(this::getPriority)
                .sum();
    }

    @AOCPartTwo
    public int getPartTwo() {
        return Lists.partition(input, 3).stream()
                .map(l -> {
                    Set<String> first = Arrays.stream(l.get(0).split("")).collect(Collectors.toSet());
                    for (int i = 1; i < l.size(); i++) {
                        Set<String> other = Arrays.stream(l.get(i).split("")).collect(Collectors.toSet());
                        first.retainAll(other);
                    }
                    return first;
                })
                .flatMap(Collection::stream)
                .mapToInt(this::getPriority)
                .sum();
    }

    private int getPriority(String s) {
        char character = s.toLowerCase(Locale.ROOT).charAt(0);
        boolean upperCase = Character.isUpperCase(s.charAt(0));
        return (character - 'a') + 1 + (upperCase ? 26 : 0);
    }
}
