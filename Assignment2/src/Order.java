import java.util.ArrayList;

public class Order {
    private Restaurant restaurant;
    private Customer customer;
    private float price;
    private int deliveryCharge;
    ArrayList<FoodItem> foodList;

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

    public ArrayList<FoodItem> getFoodList() {
        return foodList;
    }

    public void setFoodList(ArrayList<FoodItem> foodList) {
        this.foodList = foodList;
    }

    public Order(Restaurant restaurant, Customer customer, float price) {
        this.restaurant = restaurant;
        this.customer = customer;
        this.price = price;
        this.foodList = new ArrayList<>();
    }

    public void addFoodItem(FoodItem item){
        this.getFoodList().add(item);
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
}
