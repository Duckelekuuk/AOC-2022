package com.duckelekuuk.day4;

import com.duckelekuuk.aoc.annotations.AOCDay;
import com.duckelekuuk.aoc.annotations.AOCInput;
import com.duckelekuuk.aoc.annotations.AOCPartOne;
import com.duckelekuuk.aoc.annotations.AOCPartTwo;
import com.duckelekuuk.aoc.utils.Pair;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AOCDay(day = 4)
public class Day4 {

    @AOCInput
    public List<String> input;

    @AOCPartOne
    public Object getPartOne() {
        return input.stream().filter(line -> {
            String[] split = line.split(",");
            String[] left = split[0].split("-");
            String[] right = split[1].split("-");

            Pair<Integer, Integer> from = new Pair<>(Integer.parseInt(left[0]), Integer.parseInt(left[1]));
            Pair<Integer, Integer> to = new Pair<>(Integer.parseInt(right[0]), Integer.parseInt(right[1]));

            return from.getLeft() <= to.getLeft() && from.getRight() >= to.getRight() ||
                    to.getLeft() <= from.getLeft() && to.getRight() >= from.getRight();
        }).count();
    }

    @AOCPartTwo
    public Object getPartTwo() {
        return input.stream().filter(line -> {
            String[] split = line.split(",");
            String[] left = split[0].split("-");
            String[] right = split[1].split("-");

            Pair<Integer, Integer> from = new Pair<>(Integer.parseInt(left[0]), Integer.parseInt(left[1]));
            Pair<Integer, Integer> to = new Pair<>(Integer.parseInt(right[0]), Integer.parseInt(right[1]));

            return from.getLeft() <= to.getRight() && from.getRight() >= to.getLeft();
        }).count();
    }
}
