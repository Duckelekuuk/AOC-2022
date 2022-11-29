package com.duckelekuuk.framework.scanner;

import java.util.List;

public abstract class AbstractChallenge {

    private final List<String> input;

    public AbstractChallenge(List<String> input) {
        this.input = input;
    }

    public abstract String getPart1();
    public abstract String getPart2();

    public void reset() {}

    protected List<String> getInput() {
        return input;
    }
}
