public class User {
    int rewardPoints;

    public void addRewardPoints(int x){
        this.setRewardPoints(this.getRewardPoints() + x);
    }

    public int getRewardPoints() {
        return this.rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public User(){
        this.rewardPoints = 0;
    }

}
