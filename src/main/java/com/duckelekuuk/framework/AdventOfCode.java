package com.duckelekuuk.framework;

import com.duckelekuuk.framework.annotations.AOCProject;
import com.duckelekuuk.framework.cli.ArgumentOptions;
import com.duckelekuuk.framework.scanner.ChallengeScanner;
import com.duckelekuuk.framework.utils.InputFetcher;
import lombok.extern.log4j.Log4j2;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Log4j2
public class AdventOfCode {

    private AdventOfCode() {
    }

    public static void start(Class<?> mainClass, String[] args) throws Exception {
        ArgumentOptions argumentOptions = new ArgumentOptions(args);

        Calendar instance = Calendar.getInstance(TimeZone.getDefault());
        int day;

        AOCProject projectAnnotation = mainClass.getDeclaredAnnotation(AOCProject.class);
        if (projectAnnotation == null) {
            throw new IllegalArgumentException("No AOCProject annotation found on class " + mainClass.getName());
        }
        int year = projectAnnotation.year();

        // If no day is specified, use the current day
        if (argumentOptions.containsKey("day")) {
            day = Integer.parseInt(argumentOptions.get("day"));
        } else if (instance.get(Calendar.MONTH) == Calendar.DECEMBER) {
            day = instance.get(Calendar.DAY_OF_MONTH);
        } else {
            day = 1;
        }

        log.info("Starting Advent of Code {} Day {}", year, day);

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

        List<String> input = InputFetcher.getInput(session, day, year);

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
}
