package com.duckelekuuk;

import com.duckelekuuk.framework.scanner.AOCDay;
import com.duckelekuuk.framework.scanner.AbstractChallenge;

import java.util.List;

@AOCDay(day = 1)
public class DayOne extends AbstractChallenge {

    public DayOne(List<String> input) {
        super(input);
    }

    @Override
    public String getPart1() {
        List<Integer> integers = getInput().stream().mapToInt(Integer::parseInt).boxed().toList();
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

    @Override
    public String getPart2() {
        List<Integer> integers = getInput().stream().mapToInt(Integer::parseInt).boxed().toList();
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
