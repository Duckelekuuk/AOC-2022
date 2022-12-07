package com.duckelekuuk.day5;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Table;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedList;
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
//        String towerNumbers = input.get(start - 1);
//        int maxTowers = Integer.parseInt("" + towerNumbers.stripTrailing().charAt(towerNumbers.length() - 1));
//
//        for (int i = 0; i < maxTowers; i++) {
//            crates.put(i, new LinkedList<>());
//        }
//
//        for (int i = start - 2; i >= 0; i--) {
//            // First tower
//            String row = input.get(i);
//            crates.get(0).add(row.charAt(1));
//
//            // Rest of the towers
//            for (int tower = 1; tower < maxTowers; tower++) {
//                char c = row.charAt(tower * 4 + 1);
//                if (c == ' ') continue;
//                crates.get(tower).add(c);
//            }
//        }

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
