package com.duckelekuuk.day5;

import lombok.Data;

@Data
public class Crate {

    private final char letter;
    private Crate bottom;
    private Crate top;
}
