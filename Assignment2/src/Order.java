import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private Restaurant restaurant;
    private Customer customer;
    private float price;
    private int discount;
    private int deliveryCharge;
    HashMap<Integer, FoodItem> foodList;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDeliveryCharge() {
        return deliveryCharge;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public HashMap<Integer, FoodItem> getFoodList() {
        return foodList;
    }

    public void setFoodList(HashMap<Integer, FoodItem> foodList) {
        this.foodList = foodList;
    }

    public void setDeliveryCharge(int deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public Order(Restaurant restaurant, Customer customer, float price) {
        this.restaurant = restaurant;
        this.customer = customer;
        this.price = price;
        this.foodList = new HashMap<>();
    }

    public Order(){

    }

    public void addFoodItem(FoodItem item){
        this.getFoodList().put(item.getId(), item);
    }

    @Override
    public String toString() {
        return "Order{" +
                "restaurant=" + restaurant +
                ", customer=" + customer +
                ", price=" + price +
                ", deliveryCharge=" + deliveryCharge +
                ", foodList=" + foodList +
                '}';
    }

    public void printFoodList(){}

    public float getOrderValue(){return 0;}

    public void deleteFoodItem(int foodItemId){}



}
