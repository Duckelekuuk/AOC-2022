package com.duckelekuuk.dayone;

import com.duckelekuuk.aoc.annotations.AOCDay;
import com.duckelekuuk.aoc.annotations.AOCInput;
import com.duckelekuuk.aoc.annotations.AOCPartOne;
import com.duckelekuuk.aoc.annotations.AOCPartTwo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@AOCDay(day = 1)
public class DayOne {

    @AOCInput
    private List<String> input;

    @AOCPartOne
    public String getPart1() {
        int maxCalories = 0;
        int currentCalories = 0;
        for (String line : input) {
            if (line.isBlank()) {
                if (currentCalories > maxCalories) {
                    maxCalories = currentCalories;
                }
                currentCalories = 0;
                continue;
            }
            int calories = Integer.parseInt(line);
            currentCalories += calories;
        }
        return String.valueOf(maxCalories);
    }

    @AOCPartTwo
    public String getPart2() {
        List<Integer> caloriesList = new ArrayList<>();
        int currentCalories = 0;
        for (String line : input) {
            if (line.isBlank()) {
                caloriesList.add(currentCalories);
                currentCalories = 0;
                continue;
            }
            int calories = Integer.parseInt(line);
            currentCalories += calories;
        }

        return String.valueOf(caloriesList
                .stream()
                .sorted(Comparator.reverseOrder())
                .mapToInt(value -> value)
                .limit(3)
                .sum());
    }
}
