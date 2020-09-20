import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Restaurant implements User {
    private String name;
    private String address;
    private int numberOfOrders;
    private Wallet wallet;
    private int discount;
    private HashMap<Integer, FoodItem> menu;

    public String getName() {
        return this.name;
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

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public HashMap<Integer, FoodItem> getMenu() {
        return menu;
    }

    public void setMenu(HashMap<Integer, FoodItem> menu) {
        this.menu = menu;
    }

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        this.menu = new HashMap<>();
        this.numberOfOrders = 0;
        this.wallet = new Wallet(0);
    }

    public void addFoodItemToMenu() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter item name: ");
        String name = s.next();
        System.out.println("Enter item price: ");
        int price = s.nextInt();
        System.out.println("Enter item quantity: ");
        int quantity = s.nextInt();
        System.out.println("Enter item category: ");
        String category = s.next();
        System.out.println("Enter item offer: ");
        int offer = s.nextInt();
        FoodItem newFood = new FoodItem(name, price, quantity, category, offer, this.getName());
        this.getMenu().put(newFood.getId(), newFood);
    }

    public void editFoodItem() {
        this.printMenu();
        Scanner s = new Scanner(System.in);
        int option = s.nextInt();
        FoodItem selectedItem = this.getMenu().get(option);
        System.out.println("Choose an attribute to edit: ");
        System.out.println("1. Name");
        System.out.println("2. Price");
        System.out.println("3. Quantity");
        System.out.println("4. Category");
        System.out.println("5. Offer");
        option = s.nextInt();
        switch (option) {
            case 1:
                System.out.println("Enter new name: ");
                String newName = s.next();
                selectedItem.setName(newName);
                break;
            case 2:
                System.out.println("Enter new price: ");
                int newPrice = s.nextInt();
                selectedItem.setPrice(newPrice);
                break;
            case 3:
                System.out.println("Enter new quantity: ");
                int newQuantity = s.nextInt();
                selectedItem.setQuantity(newQuantity);
                break;
            case 4:
                System.out.println("Enter new category: ");
                String newCategory = s.next();
                selectedItem.setCategory(newCategory);
                break;
            case 5:
                System.out.println("Enter new offer: ");
                int newOffer = s.nextInt();
                selectedItem.setOffer(newOffer);
                break;
        }

    }

    public void printRewards() {
        System.out.println("Reward Points: " + this.getWallet().getRewardPoints());
    }

    private void printMenu() {
        for (FoodItem item : menu.values()) {
            System.out.println(item);
        }
    }

    public void setDiscountOnOverallBill() {
        System.out.println("You cannot set a discount. Please upgrade your restaurant type to set discount.");
    }

    public void showUserName(){}

    public void showUserMenu(){
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            int option = s.nextInt();
            switch(option){
                case 1:
                    this.addFoodItemToMenu();
                    break;
                case 2:
                    this.editFoodItem();
                    break;
                case 3:
                    this.printRewards();
                    break;
                case 4:
                    this.setDiscountOnOverallBill();
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("Enter valid input!");
            }

        }
    }

}
