import java.util.ArrayList;
import java.util.Scanner;

public class FoodDeliveryApp {

    private ArrayList<User> userList;
    private Wallet account;
    private String name;

    public String getName(){
        return this.name;
    }

    public void populate() {
    }

    public void showWelcomeScreen() {
        System.out.println("Welcome to " + this.getName()+ ":");
        System.out.println("1. Enter as Restaurant Owner");
        System.out.println("2. Enter as Customer");
        System.out.println("3. Check User Details");
        System.out.println("4. Company Account Details");
        System.out.println("5. Exit");
    }

//    public void startApp(){
//        Scanner s = new Scanner(System.in);
//        while(1){
//            showWelcomeScreen();
//            int optionSelected = s.nextInt();
//            switch(optionSelected){
//                case 1:
//                    ArrayList<User> restaurantList = this.showRestaurantList();
//                    int restaurantSelected = s.nextInt();
//                    this.showUser(restaurantList.get(restaurantSelected - 1));
//                    break;
//                case 2:
//                    ArrayList<User> customerList = this.showCustomerList();
//                    int customerSelected = s.nextInt();
//                    this.showUser(customerList.get(customerSelected - 1));
//                    break;
//                case 3:
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
}
