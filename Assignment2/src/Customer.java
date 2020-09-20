import java.util.ArrayList;

public class Customer implements User{

    private Wallet wallet;
    private String name;
    private String address;
    private Order cart;
    private ArrayList<Order> pastOrders;
    private Restaurant restaurant;

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Order getCart() {
        return cart;
    }

    public void setCart(Order cart) {
        this.cart = cart;
    }

    public ArrayList<Order> getPastOrders() {
        return pastOrders;
    }

    public void setPastOrders(ArrayList<Order> pastOrders) {
        this.pastOrders = pastOrders;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Customer(String name, String address) {
        this.setName(name);
        this.setAddress(address);
        this.setPastOrders(new ArrayList<>());
        this.setWallet(new Wallet(1000));
        this.setRestaurant(null);
//        this.setCurrentOrder(null);
    }

    public void addFoodItemToCart(FoodItem newItem){
        this.getCart().addFoodItem(newItem);
    }
    public void printRecentOrders(){
        for(Order order: this.getPastOrders()){
            System.out.println(order);
        }
    }

    public void showUserName(){}
}
