package efs.task.syntax;

import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {
    private final int M;
    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        //TODO: Implement the constructor
        try {
            M = Integer.parseInt(argument);
        } catch (Exception e) {
            System.out.println("'" + argument + "' to NIEPOPRAWNY ARGUMENT - to nie liczba");
            throw new IllegalArgumentException();
        }

        if (M < 1 || M > 400) {
            System.out.println(argument + " to NIEPOPRAWNY ARGUMENT - jest spoza zakresu <1,400>");
            throw new IllegalArgumentException();
        }
    }

    public void play() {
        //TODO: Implement the method that executes the game session
        int randomNumber = new Random().nextInt(M) + 1;
        int limit = (int) Math.ceil(Math.log(M)/Math.log(2)) + 1;
        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu <1," + M + ">");

        try (Scanner scanner = new Scanner(System.in)) {
            int trials = 1;
            while (trials <= limit) {
                System.out.print("Twoje próby: [");
                for (int i = 0; i < trials; i++) {
                    System.out.print("*");
                }
                for (int i = trials; i < limit; i++) {
                    System.out.print(".");
                }
                System.out.println("]");
                trials++;

                System.out.println("PODAJ liczbę :");
                int trial = 0;
                try {
                    trial = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Hmm, '" + trial + "' to NIE LICZBA");
                    continue;
                }
                if (trial == randomNumber) {
                    System.out.println("TAK");
                    System.out.println("GRATULACJE, " + trials + " - tyle prób zajęło Ci odgadnięcie liczby " + randomNumber);
                    return;
                } else if (trial > randomNumber) {
                    System.out.println("To ZBYT WIELE");
                } else {
                    System.out.println("To NIE WYSTARCZY");
                }
            }
            System.out.println("NIESTETY, wyczerpałeś limit prób (" + limit + ") do odgadnięcia liczby " + randomNumber);
        }
    }
}
