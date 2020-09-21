import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FoodDeliveryApp app = new FoodDeliveryApp("Zotato");
        app.populate();
        boolean appOpen = true;
        Scanner s = new Scanner(System.in);
        while (appOpen) {
            System.out.println("Welcome to " + app.getName() + ":");
            System.out.println("1. Enter as Restaurant Owner");
            System.out.println("2. Enter as Customer");
            System.out.println("3. Check User Details");
            System.out.println("4. Company Account Details");
            System.out.println("5. Exit");
            int option = s.nextInt();
            switch (option) {
                case 1:
                    app.showUser(app.selectRestaurant());
                    break;
                case 2:
                   app.showUser(app.selectCustomer());
                    break;
                case 3:
                    app.printUserDetails();
                    break;
                case 4:
                    System.out.println("Total Company Balance - INR " + app.getAccountBalance() + "/- ");
                    System.out.println("Total Delivery Charges Collected - INR " + app.getDeliveryCharges() + "/-");
                    break;
                case 5:
                    System.out.println("Thank you for using " + app.getName());
                    appOpen = false;
                    break;
            }
        }
    }
}
