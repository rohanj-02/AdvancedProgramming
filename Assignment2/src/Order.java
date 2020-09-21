import java.util.HashMap;

public class Order {
    private static int COUNT = 1;
    private final int ID;
    private final String customerName;
    private final int customerDiscount;
    private final int deliveryCharge;
    private final HashMap<Integer, FoodItem> foodList;
    private Restaurant restaurant;
    private float price;
    private int restaurantPercentDiscount;
    private int restaurantDiscount;

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

    private String getCustomerName() {
        return customerName;
    }

    private float getPrice() {
        return price;
    }

    private void setPrice(float price) {
        this.price = price;
    }

    public int getDeliveryCharge() {
        return deliveryCharge;
    }

    public HashMap<Integer, FoodItem> getFoodList() {
        return foodList;
    }

    private int getCustomerDiscount() {
        return customerDiscount;
    }

    private int getRestaurantPercentDiscount() {
        return restaurantPercentDiscount;
    }

    private void setRestaurantPercentDiscount(int restaurantPercentDiscount) {
        this.restaurantPercentDiscount = restaurantPercentDiscount;
    }

    private int getRestaurantDiscount() {
        return restaurantDiscount;
    }

    private void setRestaurantDiscount(int restaurantDiscount) {
        this.restaurantDiscount = restaurantDiscount;
    }

    public void addFoodItem(FoodItem item) {
        if (this.getFoodList().containsKey(item.getId())) {
            this.getFoodList().get(item.getId()).increaseQuantity(item.getQuantity());
        } else {
            this.getFoodList().put(item.getId(), item);
        }
    }

    @Override
    public String toString() {
        StringBuilder orderSummary = new StringBuilder("Order Summary : \nOrder ID: " + this.getID() + "\n");
        orderSummary.append("Restaurant: ").append(this.getRestaurant().getName()).append("\nCustomer: ").append(this.getCustomerName());
        orderSummary.append("\nOrder Price: ").append(this.getPrice()).append("/-\nDelivery Charge: ").append(this.getDeliveryCharge()).append("/-\n");
        orderSummary.append("Food items ordered: \n");
        for (FoodItem food : this.getFoodList().values()) {
            orderSummary.append(food).append("\n");
        }
        return orderSummary.toString();
    }

    private float calculatePrice() {
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

    public int getNumberOfItems() {
        int number = 0;
        for (FoodItem item : this.getFoodList().values()) {
            number += item.getQuantity();
        }
        return number;
    }

}
