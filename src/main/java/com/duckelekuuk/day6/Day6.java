package com.duckelekuuk.day6;

import com.duckelekuuk.aoc.annotations.AOCDay;
import com.duckelekuuk.aoc.annotations.AOCInput;
import com.duckelekuuk.aoc.annotations.AOCPartOne;
import com.duckelekuuk.aoc.annotations.AOCPartTwo;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@AOCDay(day = 6)
public class Day6 {

    @AOCInput
    private String input;

    @AOCPartOne
    public int partOne() {
        return getMarker(input.split(""), 4);
    }

    @AOCPartTwo
    public int partTwo() {
        return getMarker(input.split(""), 14);
    }

    private Integer getMarker(String[] splitInput, int uniqueChars) {
        outer:
        for (int i = 0; i < splitInput.length; i++) {
            if (i + uniqueChars > input.length()) {
                break;
            }

            boolean found = true;
            Collection<String> currentChars = new HashSet<>();
            for (int j = i; j < i + uniqueChars; j++) {
                String foundChar = splitInput[j];
                if (currentChars.contains(foundChar)) {
                    found = false;
                    break;
                }
                currentChars.add(foundChar);
            }

            if (found) {
                return i + uniqueChars;
            }
        }
        return -1;
    }
}
