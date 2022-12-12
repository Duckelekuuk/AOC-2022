package com.duckelekuuk.day5;

import com.duckelekuuk.aoc.annotations.AOCDay;
import com.duckelekuuk.aoc.annotations.AOCInput;
import com.duckelekuuk.aoc.annotations.AOCPartOne;
import com.duckelekuuk.aoc.annotations.AOCPartTwo;

import java.util.*;
import java.util.stream.Collectors;

@AOCDay(day = 5)
public class Day5 {

    @AOCInput
    private List<String> input;

    @AOCPartOne
    public String getPartOne() {
        CrateParser crateParser = new CrateParser(input);
        Map<Integer, Deque<Character>> towers = crateParser.getTowers();
        for (Instruction instruction : crateParser.getInstructions()) {
            for (int i = 0; i < instruction.amount(); i++) {
                Character crate = towers.get(instruction.from()).removeLast();
                towers.get(instruction.to()).addLast(crate);
            }
        }

        return towers.values().stream()
                .map(Deque::peekLast)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    @AOCPartTwo
    public String getPartTwo() {
        CrateParser crateParser = new CrateParser(input);
        Map<Integer, Deque<Character>> towers = crateParser.getTowers();
        for (Instruction instruction : crateParser.getInstructions()) {
            Deque<Character> tempTower = new ArrayDeque<>();

            for (int i = 0; i < instruction.amount(); i++) {
                Character crate = towers.get(instruction.from()).removeLast();
                tempTower.addFirst(crate);
            }

            towers.get(instruction.to()).addAll(tempTower);
        }
        return towers.values().stream()
                .map(Deque::peekLast)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}