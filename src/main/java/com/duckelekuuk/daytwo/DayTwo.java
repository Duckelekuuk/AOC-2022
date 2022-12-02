package com.duckelekuuk.daytwo;

import com.duckelekuuk.aoc.annotations.AOCDay;
import com.duckelekuuk.aoc.annotations.AOCInput;
import com.duckelekuuk.aoc.annotations.AOCPartOne;
import com.duckelekuuk.aoc.annotations.AOCPartTwo;

import java.util.List;
import java.util.Map;

@AOCDay(day = 2)
public class DayTwo {

    private final int POINTS_WIN = 6;
    private final int POINTS_DRAW = 3;
    private final int POINTS_LOSE = 0;

    @AOCInput
    private List<String> input;

    @AOCPartOne
    public String partOne() {
        return String.valueOf(input.stream().mapToInt(line -> {
            int theirPick = line.charAt(0) - 'A';
            int ourPick = line.charAt(2) - 'X';
            int result = (ourPick - theirPick + 4) % 3;

            return (ourPick + 1) + result * 3;
        }).sum());
    }


    /*
    0 0 = 2
    0 1 = 0
    0 2 = 1

    1 0 = 0
    1 1 = 1
    1 2 = 2

    2 0 = 1
    2 1 = 2
    2 2 = 0

     */
    @AOCPartTwo
    public String partTwo() {
        return String.valueOf(input.stream().mapToInt(line -> {
            int theirPick = line.charAt(0) - 'A';
            int ourPick = (theirPick + (line.charAt(2) - 'X') + 2) % 3;
            int result = (ourPick - theirPick + 4) % 3;
            return (ourPick + 1) + result * 3;
        }).sum());
    }

}
