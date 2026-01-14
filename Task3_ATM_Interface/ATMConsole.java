import java.util.Scanner;

public class ATMConsole {

    private static final Scanner scanner = new Scanner(System.in);

    // Tracks best score in a session
    private static int highestScore = 0;

    public static void main(String[] args) {

        // Taking user name
        System.out.print("Enter account holder name: ");
        String userName = scanner.nextLine();

        // Creating account with default balance
        UserAccount account = new UserAccount(5000.0);

        showWelcomeScreen(userName);

        boolean sessionActive = true;
        int sessionScore = 0;

        while (sessionActive) {

            showMenu();
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    sessionScore += handleDeposit(account);
                    break;

                case 2:
                    sessionScore += handleWithdrawal(account);
                    break;

                case 3:
                    showBalance(account);
                    break;

                case 4:
                    sessionActive = false;
                    break;

                default:
                    System.out.println("Invalid option selected. Try again.");
            }
        }

        // Updating best score
        if (sessionScore > highestScore) {
            highestScore = sessionScore;
        }

        showSessionSummary(userName, sessionScore);
        scanner.close();
    }

    // Welcome message
    private static void showWelcomeScreen(String name) {
        System.out.println("\n==================================");
        System.out.println("        SECURE ATM CONSOLE        ");
        System.out.println("==================================");
        System.out.println("User: " + name);
        System.out.println("Please choose an operation.\n");
    }

    // Menu options
    private static void showMenu() {
        System.out.println("\n1. Add Money");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit ATM");
        System.out.print("Enter choice: ");
    }

    // Deposit handler with custom limit
    private static int handleDeposit(UserAccount account) {

        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        // Custom deposit limit logic
        if (amount <= 0 || amount > 20000) {
            System.out.println("Deposit failed. Amount must be between 1 and 20000.");
            return 0;
        }

        account.addMoney(amount);
        System.out.println("Deposit successful.");
        System.out.println("Updated Balance: ₹" + account.getBalance());

        // Score logic for deposit
        return 5;
    }

    // Withdrawal handler with validation
    private static int handleWithdrawal(UserAccount account) {

        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        // Withdrawal tier logic
        if (amount <= 0 || amount > 15000) {
            System.out.println("Withdrawal limit exceeded. Max allowed is 15000.");
            return 0;
        }

        boolean success = account.removeMoney(amount);

        if (success) {
            System.out.println("Please collect your cash.");
            System.out.println("Remaining Balance: ₹" + account.getBalance());
            return 10; // higher score for successful withdrawal
        } else {
            System.out.println("Transaction failed due to insufficient balance.");
            return 0;
        }
    }

    // Balance display
    private static void showBalance(UserAccount account) {
        System.out.println("Current Account Balance: ₹" + account.getBalance());
    }

    // Final summary
    private static void showSessionSummary(String name, int score) {

        System.out.println("\n==================================");
        System.out.println("         SESSION SUMMARY          ");
        System.out.println("Account Holder : " + name);
        System.out.println("Session Score  : " + score);
        System.out.println("Best Score     : " + highestScore);
        System.out.println("==================================");
        System.out.println("Thank you for using Secure ATM.");
    }
}
