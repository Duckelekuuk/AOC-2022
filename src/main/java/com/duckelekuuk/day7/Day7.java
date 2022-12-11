package com.duckelekuuk.day7;

import com.duckelekuuk.aoc.annotations.AOCDay;
import com.duckelekuuk.aoc.annotations.AOCInput;
import com.duckelekuuk.aoc.annotations.AOCPartOne;
import com.duckelekuuk.aoc.annotations.AOCPartTwo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@AOCDay(day = 7)
public class Day7 {

    @AOCInput
    public List<String> input;

    @AOCPartOne
    public int getPartOne() {
        Directory root = index(input);

        return root.getAllDirectories()
                .stream()
                .mapToInt(Directory::getTotalFileSize)
                .filter(value -> value < 100000)
                .sum();
    }

    @AOCPartTwo
    public int getPartTwo() {
        Directory root = index(input);
        final int totalSize = 70000000;
        final int requiredSize = 30000000;

        int sizeToClean = root.getTotalFileSize() + requiredSize - totalSize;

        return root.getAllDirectories()
                .stream()
                .filter(directory -> sizeToClean < directory.getTotalFileSize())
                .min(Comparator.comparingInt(Directory::getTotalFileSize))
                .map(Directory::getTotalFileSize).orElseThrow();
    }

    private Directory index(List<String> input) {
        Directory root = new Directory();
        Directory current = root;
        for (String line : input) {
            // Command
            String[] splitMessage = line.split(" ");
            if (splitMessage[0].equals("$")) {
                if ("cd".equals(splitMessage[1])) {
                    if (splitMessage[2].equals("/")) {
                        current = root;
                    } else if (splitMessage[2].equals("..")) {
                        current = current.getParent();
                    } else {
                        current = current.getDirectories().get(splitMessage[2]);
                    }
                }
            } else {
                if (splitMessage[0].equals("dir")) {
                    current.addDirectory(splitMessage[1]);
                } else {
                    current.addFile(splitMessage[1], Integer.parseInt(splitMessage[0]));
                }
            }
        }
        return root;
    }
}
