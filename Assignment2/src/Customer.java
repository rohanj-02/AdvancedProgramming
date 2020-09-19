import java.util.ArrayList;

public class Customer extends User{
    private String name;
    private int wallet;
    private ArrayList<FoodItem> currentOrder;
    private ArrayList<ArrayList<FoodItem>> pastOrders;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public ArrayList<FoodItem> getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(ArrayList<FoodItem> currentOrder) {
        this.currentOrder = currentOrder;
    }

    public ArrayList<ArrayList<FoodItem>> getPastOrders() {
        return pastOrders;
    }

    public void setPastOrders(ArrayList<ArrayList<FoodItem>> pastOrders) {
        this.pastOrders = pastOrders;
    }

    public void deductMoneyFromWallet(int x){
        this.setWallet(this.getWallet() - x);
    }

    public void deductMoneyFromRewardPoints(int x){
        this.setRewardPoints(this.getRewardPoints() - x);
    }

    public Customer(String name) {
        this.name = name;
        this.wallet = 1000;
        this.rewardPoints = 0;
        this.currentOrder = new ArrayList<>();
        this.pastOrders = new ArrayList<>();
    }

    public void addFoodItemToCurrentOrder(){}
    public void checkout(){}
}
