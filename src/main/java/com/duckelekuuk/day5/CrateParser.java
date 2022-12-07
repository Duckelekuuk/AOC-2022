package com.duckelekuuk.day5;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrateParser {

    private Pattern INSTRUCTION_PATTERN = Pattern.compile("move (.*) from (.*) to (.*)");

    @Getter
    private final Map<Integer, Crate> topCrates = new HashMap<>();

    @Getter
    private final List<Instruction> instructions;

    public CrateParser(List<String> input) {
        int start = input.indexOf("");

        // I couldnt get this to work and running behind zo hardcode it is
        String towerNumbers = input.get(start - 1);
        int maxTowers = Integer.parseInt("" + towerNumbers.stripTrailing().charAt(towerNumbers.length() - 1));

        for (int i = 1; i <= maxTowers; i++) {
            int index = towerNumbers.indexOf("" + i);
            for (int j = start - 2; j >= 0; j--) {
                String line = input.get(j);
                if (line.length() > index && line.charAt(index) != ' ') {
                    Crate crate = new Crate(line.charAt(index));
                    topCrates.put(i, crate);
                    break;
                }
            }
        }



        this.instructions = input.subList(start + 1, input.size()).stream()
                .map(s -> INSTRUCTION_PATTERN.matcher(s))
                .filter(Matcher::find)
                .map(matcher -> new Instruction(
                        Integer.parseInt(matcher.group(1)),
                        Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3))
                )).toList();

    }
}
