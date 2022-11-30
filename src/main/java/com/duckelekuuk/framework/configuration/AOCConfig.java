package com.duckelekuuk.framework.configuration;

import lombok.Builder;
import lombok.Getter;

@Builder
public class AOCConfig {

    @Getter
    private String session;

    @Getter
    private DaySelector daySelector = DaySelector.AUTO;

    @Getter
    private int day = 0;

    public enum DaySelector {
        AUTO,
        OVERRIDE
    }
}
