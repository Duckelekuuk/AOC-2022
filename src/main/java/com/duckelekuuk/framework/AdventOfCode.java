package com.duckelekuuk.framework;

import com.duckelekuuk.framework.annotations.AOCProject;
import com.duckelekuuk.framework.cli.ArgumentOptions;
import com.duckelekuuk.framework.configuration.AOCConfig;
import com.duckelekuuk.framework.scanner.ChallengeScanner;
import com.duckelekuuk.framework.utils.InputFetcher;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Log4j2
public class AdventOfCode {

    private final Class<?> mainClass;
    private final AOCConfig aocConfig;

    public AdventOfCode(Class<?> mainClass, AOCConfig config) {
        this.mainClass = mainClass;
        this.aocConfig = config;
    }

    public void start() throws Exception {
        AOCProject projectAnnotation = mainClass.getDeclaredAnnotation(AOCProject.class);
        if (projectAnnotation == null) {
            throw new IllegalArgumentException("No AOCProject annotation found on class " + mainClass.getName());
        }
        int year = projectAnnotation.year();
        int day;

        Calendar instance = Calendar.getInstance(TimeZone.getDefault());
        if (aocConfig.getDaySelector() == AOCConfig.DaySelector.AUTO && instance.get(Calendar.MONTH) == Calendar.DECEMBER) {
            day = instance.get(Calendar.DAY_OF_MONTH);
        } else {
            day = aocConfig.getDay();
        }

        log.info("Starting Advent of Code {} Day {}", year, day);

        List<String> input = InputFetcher.getInput(aocConfig.getSession(), day, year);

        // Scan for challenges
        ChallengeScanner challengeScanner = new ChallengeScanner(mainClass);
        challengeScanner.scan();

        AOCChallenge challenge = challengeScanner.constructChallenge(day);
        challenge.setInput(input);

        String resultPartOne = challenge.runPartOne();
        challenge.resetInput();
        String resultPartTwo = challenge.runPartTwo();

        log.info("Part 1: {}", resultPartOne);
        log.info("Part 2: {}", resultPartTwo);
    }

    public static void start(Class<?> mainClass, String[] args) throws Exception {
        ArgumentOptions argumentOptions = new ArgumentOptions(args);

        AOCConfig.AOCConfigBuilder builder = AOCConfig.builder();

        // If no day is specified, use the current day
        if (argumentOptions.containsKey("day")) {
            builder.day(Integer.parseInt(argumentOptions.get("day")));
        }

        String session;

        if (argumentOptions.containsKey("session")) {
            session = argumentOptions.get("session");
        } else {
            session = System.getenv().get("AOC_SESSION");
        }

        if (session == null) {
            log.error("AOC_SESSION environment variable is not set");
            throw new IllegalStateException("AOC_SESSION environment variable is not set");
        }

        builder.session(session);
        AdventOfCode adventOfCode = new AdventOfCode(mainClass, builder.build());
        adventOfCode.start();
    }
}
