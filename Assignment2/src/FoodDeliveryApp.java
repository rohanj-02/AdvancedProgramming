import java.util.ArrayList;
import java.util.Scanner;

public class FoodDeliveryApp {

    private ArrayList<Restaurant> restaurantList;
    private ArrayList<Customer> customerList;
    private float accountBalance;
    private int deliveryCharges;
    private final String name;

    public String getName(){
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

    public FoodDeliveryApp(String name) {
        this.name = name;
    }

    public void populate() {
    }

    public void showRestaurantList(){
        for(Restaurant restaurant: restaurantList){
            restaurant.showUserName();
        }
    }
    public void showCustomerList(){
        for(Customer customer: customerList){
            customer.showUserName();
        }
    }

    public void showUser(User user){
        //user menu
        //TODO See this
    }

    public void printCustomerDetails(Customer customer){
        //TODO
    }

    public void printRestaurantDetails(Restaurant restaurant){
        //TODO
    }

    public void showWelcomeScreen() {
        boolean appOpen = true;
        Scanner s = new Scanner(System.in);
        while(appOpen){
            System.out.println("Welcome to " + this.getName()+ ":");
            System.out.println("1. Enter as Restaurant Owner");
            System.out.println("2. Enter as Customer");
            System.out.println("3. Check User Details");
            System.out.println("4. Company Account Details");
            System.out.println("5. Exit");
            int option = s.nextInt();
            switch (option){
                case 1:
                    this.showRestaurantList();
                    int restaurantSelected = s.nextInt();
                    this.showUser(this.restaurantList.get(restaurantSelected - 1));
                    break;
                case 2:
                    this.showCustomerList();
                    int customerSelected = s.nextInt();
                    this.showUser(this.customerList.get(customerSelected - 1));
                    break;
                case 3:
                    int choice = s.nextInt();
                    if(choice == 1){
                        this.showCustomerList();
                        choice = s.nextInt();
                        this.printCustomerDetails(this.customerList.get(choice - 1));
                    }else if(choice == 2){
                        this.showRestaurantList();
                        choice = s.nextInt();
                        this.printRestaurantDetails(this.restaurantList.get(choice - 1));
                    }

                case 4:
                    System.out.println("Total Company Balance - INR " + this.getAccountBalance() + "/- " );
                    System.out.println("Total Delivery Charges Collected - INR " + this.getDeliveryCharges() + "/-");
                case 5:
                    System.out.println("Thank you for using " + this.getName());
                    appOpen = false;
                    break;
            }
        }

    }
}
