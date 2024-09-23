package jssb;

import java.util.Map;
import java.util.Random;

import java.util.HashMap;
import java.util.Map;

public class Player {
    Variants variant;
    String name;

    private static final Map<Variants, Variants> winRules = new HashMap<>();

    static {
        winRules.put(Variants.ROCK, Variants.SCISSORS);
        winRules.put(Variants.SCISSORS, Variants.PAPER);
        winRules.put(Variants.PAPER, Variants.ROCK);
    }

    public Player() {
        variant = Variants.injectRandom();
        name = "Bot";
    }

    public Player(Variants variant, String name) {
        this.variant = variant;
        this.name = name;
    }

    public String whoWins(Player player1, Player player2) {
        Variants variant1 = player1.variant;
        Variants variant2 = player2.variant;
        String winMessage = "Победил игрок с именем: ";

        if (variant1 == variant2) {
            return "Ничья";
        }

        if (winRules.get(variant1) == variant2) {
            return winMessage + player1.name;
        } else {
            return winMessage + player2.name;
        }
    }
}
