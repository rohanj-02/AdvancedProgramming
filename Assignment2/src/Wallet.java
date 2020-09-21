public class Wallet {
    private float amount;
    private int rewardPoints;

    public Wallet(float amount) {
        this.amount = amount;
        this.rewardPoints = 0;
    }

    public float getAmount() {
        return amount;
    }

    private void setAmount(float amount) {
        this.amount = amount;
    }

    public int getRewardPoints() {
        return this.rewardPoints;
    }

    private void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public void addRewardPoints(int x) {
        this.setRewardPoints(this.getRewardPoints() + x);
    }

    public float getBalance() {
        return getAmount() + getRewardPoints();
    }

    public void deductBalance(float x) {
        if (this.getRewardPoints() > x) {
            this.setRewardPoints((int) (this.getRewardPoints() - x));
        } else if (this.getRewardPoints() < x) {
            this.setAmount(this.getAmount() - (x - this.getRewardPoints()));
            this.setRewardPoints(0);
        }
    }
}
