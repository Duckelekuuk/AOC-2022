package com.duckelekuuk.day5;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Instruction {

    private final int amount;
    private final int from;
    private final int to;
}
