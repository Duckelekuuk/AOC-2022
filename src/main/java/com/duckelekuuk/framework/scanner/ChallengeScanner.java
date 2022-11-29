package com.duckelekuuk.framework.scanner;

import lombok.extern.log4j.Log4j2;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class ChallengeScanner {

    private final Class<?> startClass;
    private final Reflections reflections;
    private final Map<Integer, Class<? extends AbstractChallenge>> foundChallenges;

    public ChallengeScanner(Class<?> startClass) {
        this.startClass = startClass;
        this.foundChallenges = new HashMap<>();
        this.reflections = new Reflections(startClass);
    }

    public void scan() {
        reflections.getSubTypesOf(AbstractChallenge.class).forEach(day -> {
            AOCDay annotation = day.getAnnotation(AOCDay.class);
            if (annotation != null) {
                foundChallenges.put(annotation.day(), day);
            }
        });
    }

    public AbstractChallenge constructChallenge(int day, List<String> input) throws NoSuchMethodException {
        Class<? extends AbstractChallenge> challengeClass = foundChallenges.get(day);
        if (challengeClass == null) {
            throw new IllegalArgumentException("No challenge found for day " + day);
        }

        Constructor<?> constructor = challengeClass.getConstructor(List.class);
        try {
            return (AbstractChallenge) constructor.newInstance(input);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to construct challenge for day " + day, e);
        }
    }
}
