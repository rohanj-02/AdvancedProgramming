import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Customer implements User{

    private Wallet wallet;
    private String name;
    private String address;
    private Order cart;
    private ArrayList<Order> pastOrders;
    private Restaurant restaurant;
    private FoodDeliveryApp app;

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

    

    public FoodDeliveryApp getApp() {
        return app;
    }

    public void setApp(FoodDeliveryApp app) {
        this.app = app;
    }

    public Customer(String name, String address, FoodDeliveryApp app) {
        this.setName(name);
        this.setAddress(address);
        this.setPastOrders(new ArrayList<>());
        this.setWallet(new Wallet(1000));
        this.setApp(app);
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
        Scanner s = new Scanner(System.in);
        this.initialiseCart();
        boolean flag = true;
        while(flag){
            System.out.println("Welcome " + this.getName());
            System.out.println("Customer Menu");
            System.out.println("1. Select Restaurant");
            System.out.println("2. Checkout cart");
            System.out.println("3. Reward won");
            System.out.println("4. Print Recent Orders");
            System.out.println("5. Exit");
            int option = s.nextInt();
            switch(option){
                case 1:
                    this.selectRestaurant();
                    break;
                case 2:
                    this.checkout();
                    break;
                case 3:
                    System.out.println("Rewards Won : " + this.getWallet().getRewardPoints());
                    break;
                case 4:
                    this.printRecentOrders();
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("Enter correct option. ");
            }
        }

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
        this.getPastOrders().add(order);
    }

    public void initialiseCart(){
        this.setCart(new Order(this, 0, 40));
        this.setRestaurant(null);
    }

    public void selectRestaurant(){
        Scanner s = new Scanner(System.in);
        ArrayList<Restaurant> restaurantList = this.getApp().getRestaurantList();
        this.getApp().showRestaurantList();
        int option = s.nextInt();

        this.setRestaurant(restaurantList.get(option - 1));
        this.getCart().setRestaurant(this.getRestaurant());

        HashMap<Integer, FoodItem> menu = this.getRestaurant().getMenu();
        this.getRestaurant().printMenu();
        System.out.println("Choose item by code");
        option = s.nextInt();
        System.out.println("Enter item quantity");
        int quantity = s.nextInt();
        FoodItem itemSelected = menu.get(option);

        this.addFoodItemToCart(new FoodItem(itemSelected.getName(), itemSelected.getPrice(), quantity, itemSelected.getCategory(), itemSelected.getOffer(), itemSelected.getRestaurant()));
        System.out.println("Items added to cart.");
    }
}
