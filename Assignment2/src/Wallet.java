public class Wallet {
    private float balance;
    private float rewardPoints;

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void deductBalance(float x) {
        this.setBalance(this.getBalance() - x);
    }

    public float getRewardPoints() {
        return this.rewardPoints;
    }

    public void addBalance(float x) {
        this.setBalance(this.getBalance() + x);
    }

    public Wallet(float balance) {
        this.balance = balance;
    }
}
