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
    private final Multimap<Integer, Character> crates = Multimaps.newListMultimap(new HashMap<>(), LinkedList::new);

    @Getter
    private final List<Instruction> instructions;

    public CrateParser(List<String> input) {
        int start = input.indexOf("");

        String towerNumbers = input.get(start - 1);
        int maxTowers = Integer.parseInt("" + towerNumbers.stripTrailing().charAt(towerNumbers.length() - 1));

        for (int i = start - 2; i >= 0; i--) {
            String row = input.get(i);
            crates.put(0, row.charAt(1));

            for (int tower = 1; tower < maxTowers - 1; tower++) {
                crates.put(tower, row.charAt(tower * 4));
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
