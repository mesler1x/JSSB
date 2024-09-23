package jssb;

import java.util.Arrays;
import java.util.Random;

public enum Variants {
    ROCK,
    PAPER,
    SCISSORS;

    public static Variants injectRandom() {
        Random random = new Random();
        int difficultyIndex = random.nextInt(Variants.values().length - 1);
        return Variants.values()[difficultyIndex];
    }
}
