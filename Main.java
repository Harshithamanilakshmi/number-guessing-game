import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static int readNumber(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                int num = sc.nextInt();
                if (num >= min && num <= max) {
                    return num;
                }
                System.out.println("Enter a number between " + min + " and " + max);
            } else {
                System.out.println("Invalid input! Enter a number.");
                sc.next();
            }
        }
    }

    static boolean playRound(int range, int maxAttempts, Random rand) {
        int secret = rand.nextInt(range) + 1;
        int attempts = 0;

        System.out.println("Guess the number (1-" + range + "). You have " + maxAttempts + " attempts.");

        while (attempts < maxAttempts) {
            int guess = readNumber("Enter guess: ", 1, range);
            attempts++;

            if (guess < secret) {
                System.out.println("Too low! Attempts left: " + (maxAttempts - attempts));
            } else if (guess > secret) {
                System.out.println("Too high! Attempts left: " + (maxAttempts - attempts));
            } else {
                System.out.println("Correct! You took " + attempts + " attempts.");
                return true;
            }
        }
        System.out.println("You lose! The number was " + secret);
        return false;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int wins = 0;
        int losses = 0;
        char playAgain;

        do {
            System.out.println("\nChoose level: 1. Easy (1-50, 10 attempts)  2. Medium (1-100, 7 attempts)  3. Hard (1-200, 5 attempts)");
            int level = readNumber("Level: ", 1, 3);

            int range = 100;
            int maxAttempts = 7;
            if (level == 1) {
                range = 50;
                maxAttempts = 10;
            } else if (level == 3) {
                range = 200;
                maxAttempts = 5;
            }

            if (playRound(range, maxAttempts, rand)) {
                wins++;
            } else {
                losses++;
            }

            System.out.println("Wins: " + wins + " | Losses: " + losses);
            System.out.print("Play again? (y/n): ");
            playAgain = sc.next().charAt(0);
        } while (playAgain == 'y' || playAgain == 'Y');

        System.out.println("Thanks for playing!");
        sc.close();
    }
}
