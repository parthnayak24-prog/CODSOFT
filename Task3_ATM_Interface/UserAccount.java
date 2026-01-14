public class UserAccount {

    // Stores balance of user
    private double balance;

    public UserAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Adds money to account
    public void addMoney(double amount) {
        balance += amount;
    }

    // Removes money if possible
    public boolean removeMoney(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // Returns current balance
    public double getBalance() {
        return balance;
    }
}
