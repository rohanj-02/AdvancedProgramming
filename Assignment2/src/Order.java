import java.util.HashMap;

public class Order {
    private static int COUNT = 1;
    private Restaurant restaurant;
    private final int ID;
    private final String customerName;
    private float price;
    private final int customerDiscount;
    private int restaurantPercentDiscount;
    private int restaurantDiscount;
    private final int deliveryCharge;
    private final HashMap<Integer, FoodItem> foodList;

    public Order(Customer customer, int customerDiscount, int deliveryCharge) {
        this.customerName = customer.getName();
        this.customerDiscount = customerDiscount;
        this.deliveryCharge = deliveryCharge;
        this.foodList = new HashMap<>();
        this.price = 0;
        this.ID = COUNT;
        this.restaurant = null;
        COUNT++;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.setRestaurantPercentDiscount(restaurant.getPercentDiscount());
        this.setRestaurantDiscount(restaurant.getDiscount());
    }

    private int getID() {
        return ID;
    }

    public String getCustomerName() {
        return customerName;
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

    public HashMap<Integer, FoodItem> getFoodList() {
        return foodList;
    }

    public int getCustomerDiscount() {
        return customerDiscount;
    }

    public int getRestaurantPercentDiscount() {
        return restaurantPercentDiscount;
    }

    public void setRestaurantPercentDiscount(int restaurantPercentDiscount) {
        this.restaurantPercentDiscount = restaurantPercentDiscount;
    }

    public int getRestaurantDiscount() {
        return restaurantDiscount;
    }

    public void setRestaurantDiscount(int restaurantDiscount) {
        this.restaurantDiscount = restaurantDiscount;
    }

    public void addFoodItem(FoodItem item) {
        if(this.getFoodList().containsKey(item.getId())){
            this.getFoodList().get(item.getId()).increaseQuantity(item.getQuantity());
        }else{
            this.getFoodList().put(item.getId(), item);
        }
    }

    @Override
    public String toString() {
        String orderSummary = "Order Summary : \nOrder ID: " + this.getID() + "\n";
        orderSummary += "Restaurant: " + this.getRestaurant().getName() + "\nCustomer: " + this.getCustomerName();
        orderSummary += "\nOrder Price: " + this.getPrice() + "/-\nDelivery Charge: " + this.getDeliveryCharge() + "/-\n";
        orderSummary += "Food items ordered: \n";
        for (FoodItem food : this.getFoodList().values()) {
            orderSummary += food + "\n";
        }
        return orderSummary;
    }

    public float calculatePrice() {
        float price = 0;
        for (FoodItem item : getFoodList().values()) {
            float priceTemp = item.getPrice() * (100 - (float) item.getOffer()) / 100;
            priceTemp *= item.getQuantity();
            price += priceTemp;
        }
        this.setPrice(price);
        return price;
    }

    public float getOrderValue() {
        float foodPrice = this.calculatePrice();
        foodPrice = foodPrice * (100 - this.getRestaurantPercentDiscount()) / 100;
        if (foodPrice > 100) {
            foodPrice -= this.getRestaurantDiscount();
        }
        if (foodPrice > 200) {
            foodPrice -= this.getCustomerDiscount();
        }
        this.setPrice(foodPrice);
        return foodPrice;
    }

    public void deleteFoodItem(int foodItemId) {
        this.getFoodList().remove(foodItemId);
    }

    public void printFoodList() {
        for (FoodItem item : this.getFoodList().values()) {
            System.out.println(item);
        }
    }

    public int getNumberOfItems(){
        int number = 0;
        for(FoodItem item : this.getFoodList().values()){
            number += item.getQuantity();
        }
        return number;
    }

}
