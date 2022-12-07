package com.duckelekuuk.day5;

import com.duckelekuuk.aoc.annotations.AOCDay;
import com.duckelekuuk.aoc.annotations.AOCInput;
import com.duckelekuuk.aoc.annotations.AOCPartOne;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@AOCDay(day = 5)
public class Day5 {

    @AOCInput
    private List<String> input;

    @AOCPartOne
    public int getPartOne() {
        CrateParser crateParser = new CrateParser(input);

        for (Instruction instruction : crateParser.getInstructions()) {
            System.out.println("Moving " + instruction.getAmount() + " crates from " + instruction.getFrom() + " to " + instruction.getTo());
        }

        return 0;
    }
}