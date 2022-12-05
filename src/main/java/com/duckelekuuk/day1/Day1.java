package com.duckelekuuk.day1;

import com.duckelekuuk.aoc.annotations.AOCDay;
import com.duckelekuuk.aoc.annotations.AOCInput;
import com.duckelekuuk.aoc.annotations.AOCPartOne;
import com.duckelekuuk.aoc.annotations.AOCPartTwo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

@AOCDay(day = 1)
public class Day1 {

    @AOCInput
    private String input;

    @AOCPartOne
    public String getPart1() {
        Optional<Integer> max = Arrays.stream(input.split("\n\n"))
                .mapToInt(s -> Arrays.stream(s.strip().split("\n"))
                        .mapToInt(Integer::parseInt).sum()
                ).boxed().max(Comparator.naturalOrder());

        return max.get() + "";
    }

    @AOCPartTwo
    public String getPart2() {
        Optional<Integer> max = Optional.of(Arrays.stream(input.split("\n\n"))
                .mapToInt(s -> Arrays.stream(s.strip().split("\n"))
                        .mapToInt(Integer::parseInt).sum()
                ).boxed().sorted(Collections.reverseOrder()).mapToInt(s -> s).limit(3).sum());

        return max.get() + "";
    }
}
