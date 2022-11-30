package com.duckelekuuk;

import com.duckelekuuk.framework.annotations.AOCDay;
import com.duckelekuuk.framework.annotations.AOCInput;
import com.duckelekuuk.framework.annotations.AOCPartOne;
import com.duckelekuuk.framework.annotations.AOCPartTwo;

import java.util.List;

@AOCDay(day = 1)
public class DayOne {

    @AOCInput
    private List<String> input;

    @AOCPartOne
    public String getPart1() {
        List<Integer> integers = input.stream().mapToInt(Integer::parseInt).boxed().toList();
        int previous = integers.get(0);
        int increased = 0;

        for (int i = 1; i < integers.size(); i++) {

            int current = integers.get(i);
            if (current > previous) {
                increased++;
            }
            previous = current;
        }

        return String.valueOf(increased);

    }

    @AOCPartTwo
    public String getPart2() {
        List<Integer> integers = input.stream().mapToInt(Integer::parseInt).boxed().toList();
        int previousSum = 100000000;
        int increased = 0;
        for (int i = 0; i < integers.size(); i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                if (i + j < integers.size()) {
                    sum += integers.get(i + j);
                }
            }
            if (sum > previousSum) {
                increased++;
            }

            previousSum = sum;
        }
        return String.valueOf(increased);
    }
}
