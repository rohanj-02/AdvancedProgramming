import java.util.ArrayList;
import java.util.Scanner;

public class FoodDeliveryApp {

    private final String name;
    private final ArrayList<User> restaurantList;
    private final ArrayList<User> customerList;
    private float accountBalance;
    private int deliveryCharges;

    public FoodDeliveryApp(String name) {
        this.name = name;
        this.setAccountBalance(0);
        this.setDeliveryCharges(0);
        this.restaurantList = new ArrayList<>();
        this.customerList = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<User> getRestaurantList() {
        return restaurantList;
    }

    public ArrayList<User> getCustomerList() {
        return customerList;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    private void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getDeliveryCharges() {
        return deliveryCharges;
    }

    private void setDeliveryCharges(int deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public void addToBalance(float x) {
        this.setAccountBalance(this.getAccountBalance() + x);
    }

    public void addToDeliveryCharge(int x) {
        this.setDeliveryCharges(this.getDeliveryCharges() + x);
    }

    public void populate() {
//        this.getCustomerList().add(new EliteCustomer("Rohan", "Delhi", this));
//        this.getCustomerList().add(new SpecialCustomer("Customer 2", "Pune", this));
//        this.getCustomerList().add(new Customer("Dhruv", "Delhi", this));
//        this.getCustomerList().add(new Customer("John Doe", "Mumbai", this));
//        this.getCustomerList().add(new EliteCustomer("Jane Doe", "Kolkata", this));
//        this.getRestaurantList().add(new AuthenticRestaurant("Faasos", "Delhi", this));
//        this.getRestaurantList().add(new Restaurant("Theobroma", "Delhi", this));
//        this.getRestaurantList().add(new Restaurant("Bikanervala", "Delhi", this));
//        this.getRestaurantList().add(new FastFoodRestaurant("InstaPizza", "Delhi", this));
//        this.getRestaurantList().add(new FastFoodRestaurant("Dominos", "India", this));

        this.getCustomerList().add(new EliteCustomer("Ram", "Delhi", this));
        this.getCustomerList().add(new EliteCustomer("Sam", "Pune", this));
        this.getCustomerList().add(new SpecialCustomer("Tim", "Delhi", this));
        this.getCustomerList().add(new Customer("Kim", "Mumbai", this));
        this.getCustomerList().add(new Customer("Jim", "Kolkata", this));
        this.getRestaurantList().add(new AuthenticRestaurant("Shah", "Delhi", this));
        this.getRestaurantList().add(new Restaurant("Ravi's", "Delhi", this));
        this.getRestaurantList().add(new AuthenticRestaurant("The Chinese", "Delhi", this));
        this.getRestaurantList().add(new FastFoodRestaurant("Wang's", "Delhi", this));
        this.getRestaurantList().add(new Restaurant("Paradise", "India", this));


    }

    public void showUserList(ArrayList<User> userList){
        int i = 1;
        for(User user: userList){
            System.out.print(i + ". ");
            i++;
            user.showUserName();
        }
    }

    public void showUser(User user) {
        user.showUserMenu();
    }

    public void printUserDetails() {
        Scanner s = new Scanner(System.in);
        System.out.println("1. Customer List");
        System.out.println("2. Restaurant List");
        int choice = s.nextInt();
        if (choice == 1) {
            this.showUserList(this.getCustomerList());
            choice = s.nextInt();
            this.getCustomerList().get(choice - 1).showUserDetails();
        } else if (choice == 2) {
            this.showUserList(this.getRestaurantList());
            choice = s.nextInt();
            this.getRestaurantList().get(choice - 1).showUserDetails();
        }
    }

    public Restaurant selectRestaurant(){
        Scanner s = new Scanner(System.in);
        this.showUserList(this.getRestaurantList());
        int restaurantSelected = s.nextInt();
        return (Restaurant) this.getRestaurantList().get(restaurantSelected - 1);
    }

    public Customer selectCustomer(){
        Scanner s = new Scanner(System.in);
        this.showUserList(this.getCustomerList());
        int customerSelected = s.nextInt();
        return (Customer) this.getCustomerList().get(customerSelected - 1);
    }
}
