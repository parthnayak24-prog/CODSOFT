import java.util.Random;
import java.util.Scanner;

public class NumberGuessArena {

    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);

    // Random number generator
    private static final Random random = new Random();

    // To store best score across rounds
    private static int bestScore = 0;

    public static void main(String[] args) {

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        displayWelcome(playerName);

        boolean continueGame = true;
        int roundsPlayed = 0;
        int totalScore = 0;

        while (continueGame) {
            roundsPlayed++;

            int roundScore = playSession(roundsPlayed);
            totalScore += roundScore;

            // Updating best score
            if (roundScore > bestScore) {
                bestScore = roundScore;
            }

            continueGame = askForReplay();
        }

        displayFinalResult(playerName, roundsPlayed, totalScore);
        scanner.close();
    }

    // Welcome message
    private static void displayWelcome(String name) {
        System.out.println("\n==================================");
        System.out.println("   WELCOME TO NUMBER GUESS ARENA   ");
        System.out.println("==================================");
        System.out.println("Player: " + name);
        System.out.println("Try to guess the number smartly!\n");
    }

    // One full game round
    private static int playSession(int roundNo) {

        System.out.println("--- Round " + roundNo + " ---");

        int maxRange = selectDifficulty();
        int secretNumber = random.nextInt(maxRange) + 1;

        int maxAttempts = calculateChances(maxRange);
        int attemptsUsed = 0;
        boolean success = false;

        while (attemptsUsed < maxAttempts) {

            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attemptsUsed++;

            if (guess == secretNumber) {
                System.out.println("Correct guess! Well done.");
                success = true;
                break;
            }

            giveHintFeedback(guess, secretNumber, maxRange);
            System.out.println("Attempts remaining: " + (maxAttempts - attemptsUsed));
        }

        return computeScore(success, maxAttempts, attemptsUsed);
    }

    // Difficulty selection
    private static int selectDifficulty() {

        System.out.println("Select Difficulty:");
        System.out.println("1. Easy (1 - 40)");
        System.out.println("2. Medium (1 - 90)");
        System.out.println("3. Hard (1 - 180)");
        System.out.print("Your choice: ");

        int choice = scanner.nextInt();

        if (choice == 1) return 40;
        if (choice == 2) return 90;
        if (choice == 3) return 180;

        System.out.println("Invalid input. Medium difficulty selected.");
        return 90;
    }

    // Calculating attempts based on range
    private static int calculateChances(int range) {
        return (int) Math.ceil(Math.log(range) / Math.log(2)) + 1;
    }

    // Personalized smart hint logic
    private static void giveHintFeedback(int userGuess, int actual, int range) {

        int gap = Math.abs(userGuess - actual);

        if (gap <= range * 0.05) {
            System.out.println("Hint: Almost there!");
        } else if (gap <= range * 0.20) {
            System.out.println("Hint: Getting closer.");
        } else {
            System.out.println("Hint: You are quite far.");
        }

        if (userGuess > actual) {
            System.out.println("Try guessing a smaller number.");
        } else {
            System.out.println("Try guessing a bigger number.");
        }
    }

    // Score calculation
    private static int computeScore(boolean win, int totalAttempts, int usedAttempts) {

        if (!win) {
            System.out.println("No more attempts left. Round over.");
            return 0;
        }

        int score = (totalAttempts - usedAttempts + 1) * 15;
        System.out.println("Score this round: " + score);
        return score;
    }

    // Asking user to replay
    private static boolean askForReplay() {
        System.out.print("\nDo you want to play another round? (y/n): ");
        char choice = scanner.next().toLowerCase().charAt(0);
        scanner.nextLine(); // clear buffer
        return choice == 'y';
    }

    // Final summary
    private static void displayFinalResult(String name, int rounds, int score) {

        System.out.println("\n==================================");
        System.out.println("           FINAL SUMMARY          ");
        System.out.println("Player Name   : " + name);
        System.out.println("Rounds Played : " + rounds);
        System.out.println("Total Score   : " + score);
        System.out.println("Best Round    : " + bestScore);
        System.out.println("==================================");
        System.out.println("Thanks for playing Number Guess Arena!");
    }
}
