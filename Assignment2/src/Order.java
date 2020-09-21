import java.util.HashMap;

public class Order {
    private Restaurant restaurant;
    private int ID;
    private static int COUNT = 1;
    private Customer customer;
    private float price;
    private int customerDiscount;
    private int restaurantPercentDiscount;
    private int restaurantDiscount;
    private int deliveryCharge;
    private HashMap<Integer, FoodItem> foodList;

    public Order(Customer customer, int customerDiscount, int deliveryCharge) {
        this.setCustomer(customer);
        this.setCustomerDiscount(customerDiscount);
        this.setDeliveryCharge(deliveryCharge);
        this.setFoodList(new HashMap<>());
        this.setPrice(0);
        this.ID = COUNT;
        COUNT++;
    }

    public Order() {
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.setRestaurantPercentDiscount(restaurant.getPercentDiscount());
        this.setRestaurantDiscount(restaurant.getDiscount());
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public void setDeliveryCharge(int deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public HashMap<Integer, FoodItem> getFoodList() {
        return foodList;
    }

    public void setFoodList(HashMap<Integer, FoodItem> foodList) {
        this.foodList = foodList;
    }

    public int getCustomerDiscount() {
        return customerDiscount;
    }

    public void setCustomerDiscount(int customerDiscount) {
        this.customerDiscount = customerDiscount;
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
        this.getFoodList().put(item.getId(), item);
    }

    @Override
    public String toString() {
        String orderSummary = "Order Summary : \nOrder ID: " + this.getID() + "\n";
        orderSummary += "Restaurant: " + this.getRestaurant().getName() + "\nCustomer: " + this.getCustomer().getName();
        orderSummary += "\nOrder Price: " + this.getPrice() + "/-\nDelivery Charge: " + this.getDeliveryCharge() + "/-\n";
        orderSummary += "Food items ordered: \n";
        for(FoodItem food: this.getFoodList().values()){
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
        this.price = foodPrice;
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


}
