import java.util.ArrayList;
import java.util.Scanner;

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

    public void showUserMenu(){
        this.initialiseCart();

    }

    public void checkout(){
        System.out.println("Items in Cart - ");
        this.getCart().printFoodList();
        System.out.println("Delivery Charge - " + this.getCart().getDeliveryCharge() + "/-");
        System.out.println("Total order value - INR " + this.getCart().getOrderValue());
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        while(this.getCart().getOrderValue() > this.getWallet().getBalance()){
            System.out.println("Balance Low \nDelete items from cart - ");
            this.getCart().printFoodList();
            int option = s.nextInt();
            this.getCart().deleteFoodItem(option);
            flag = false;
        }
        if(!flag){
            this.getCart().printFoodList();
            System.out.println("Delivery Charge - " + this.getCart().getDeliveryCharge() + "/-");
            System.out.println("Total order value - INR " + this.getCart().getOrderValue());
        }
        System.out.println("1. Proceed to Checkout");
        int next = s.nextInt();
        if(next == 1){
            System.out.println("Successfully placed order for INR " + this.getCart().getOrderValue() + "/-");
            this.getWallet().deductBalance(this.getCart().getOrderValue());
            this.addToPastOrders(this.getCart());
            this.setCart(null);
        }
    }

    public void addToPastOrders(Order order){
        //TODO
    }

    public void initialiseCart(){
        this.cart = new Order();
    }
}
