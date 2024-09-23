package jssb;


public class Main {
    public static void main(String[] args) {
        Player bot = new Player();
        Player alex = new Player(Variants.SCISSORS, "Alex");
        System.out.println(bot.whoWins(bot, alex));
    }
}