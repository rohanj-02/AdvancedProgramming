public class Wallet {
    private float amount;
    private int rewardPoints;

    public float getBalance() {
        return getAmount() + getRewardPoints();
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public void deductBalance(float x) {
        if(this.getRewardPoints() > x){
            this.setRewardPoints((int)(this.getRewardPoints() - x));
        }
        else if(this.getRewardPoints() < x){
            this.setAmount(this.getAmount() - (x - this.getRewardPoints()));
            this.setRewardPoints(0);
        }
    }

    ///BALANCE REFERS TO THE WHOLE AMOUNT

    public int getRewardPoints() {
        return this.rewardPoints;
    }

    public void addRewardPoints(int x){
        this.setRewardPoints(this.getRewardPoints() + x);
    }

    public Wallet(float amount) {
        this.setAmount(amount);
        this.setRewardPoints(0);
    }
}
