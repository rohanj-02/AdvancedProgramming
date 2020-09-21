import java.util.ArrayList;
import java.util.Scanner;

public class FoodDeliveryApp {

    private final String name;
    private ArrayList<Restaurant> restaurantList;
    private ArrayList<Customer> customerList;
    private float accountBalance;
    private int deliveryCharges;

    public FoodDeliveryApp(String name) {
        this.name = name;
        this.setAccountBalance(0);
        this.setDeliveryCharges(0);
        this.setRestaurantList(new ArrayList<>());
        this.setCustomerList(new ArrayList<>());
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(ArrayList<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(int deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
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

    public void showRestaurantList() {
        int i = 1;
        for (Restaurant restaurant : getRestaurantList()) {
            System.out.print(i + ". ");
            i++;
            restaurant.showUserName();
        }
    }

    public void showCustomerList() {
        int i = 1;
        for (Customer customer : getCustomerList()) {
            System.out.print(i + ". ");
            i++;
            customer.showUserName();
        }
    }

    public void addToBalance(float x) {
        this.setAccountBalance(this.getAccountBalance() + x);
    }

    public void addToDeliveryCharge(int x) {
        this.setDeliveryCharges(this.getDeliveryCharges() + x);
    }

    public void showUser(User user) {
        user.showUserMenu();
    }

    public void printUserDetails(User user) {
        user.showUserDetails();
    }

    public void showWelcomeScreen() {
        boolean appOpen = true;
        Scanner s = new Scanner(System.in);
        while (appOpen) {
            System.out.println("Welcome to " + this.getName() + ":");
            System.out.println("1. Enter as Restaurant Owner");
            System.out.println("2. Enter as Customer");
            System.out.println("3. Check User Details");
            System.out.println("4. Company Account Details");
            System.out.println("5. Exit");
            int option = s.nextInt();
            switch (option) {
                case 1:
                    this.showRestaurantList();
                    int restaurantSelected = s.nextInt();
                    this.showUser(this.getRestaurantList().get(restaurantSelected - 1));
                    break;
                case 2:
                    this.showCustomerList();
                    int customerSelected = s.nextInt();
                    this.showUser(this.getCustomerList().get(customerSelected - 1));
                    break;
                case 3:
                    System.out.println("1. Customer List");
                    System.out.println("2. Restaurant List");
                    int choice = s.nextInt();
                    if (choice == 1) {
                        this.showCustomerList();
                        choice = s.nextInt();
                        this.printUserDetails(this.getCustomerList().get(choice - 1));
                    } else if (choice == 2) {
                        this.showRestaurantList();
                        choice = s.nextInt();
                        this.printUserDetails(this.getRestaurantList().get(choice - 1));
                    }
                    break;
                case 4:
                    System.out.println("Total Company Balance - INR " + this.getAccountBalance() + "/- ");
                    System.out.println("Total Delivery Charges Collected - INR " + this.getDeliveryCharges() + "/-");
                    break;
                case 5:
                    System.out.println("Thank you for using " + this.getName());
                    appOpen = false;
                    break;
            }
        }

    }
}
