import java.util.HashMap;
import java.util.Scanner;

public class Restaurant implements User {
    private String name;
    private String address;
    private int numberOfOrders;
    private int rewardPoints;
    private float balance;
    private FoodDeliveryApp app;
    private int discount;
    private int rewardPointPerX;
    private int rewardPointThreshold;
    private int percentDiscount;

    private HashMap<Integer, FoodItem> menu;

    public Restaurant(String name, String address, FoodDeliveryApp app) {
        this.setName(name);
        this.setAddress(address);
        this.setMenu(new HashMap<>());
        this.setNumberOfOrders(0);
        this.setPercentDiscount(0);
        this.setDiscount(0);
        this.setApp(app);
        this.setRewardPointPerX(5);
        this.setRewardPointThreshold(100);
    }

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

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public HashMap<Integer, FoodItem> getMenu() {
        return menu;
    }

    public void setMenu(HashMap<Integer, FoodItem> menu) {
        this.menu = menu;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getPercentDiscount() {
        return percentDiscount;
    }

    public void setPercentDiscount(int percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    public FoodDeliveryApp getApp() {
        return app;
    }

    public void setApp(FoodDeliveryApp app) {
        this.app = app;
    }

    public int getRewardPointPerX() {
        return rewardPointPerX;
    }

    public void setRewardPointPerX(int rewardPointPerX) {
        this.rewardPointPerX = rewardPointPerX;
    }

    public int getRewardPointThreshold() {
        return rewardPointThreshold;
    }

    public void setRewardPointThreshold(int rewardPointThreshold) {
        this.rewardPointThreshold = rewardPointThreshold;
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
        FoodItem newFood = new FoodItem(name, price, quantity, category, offer, this.getName(), 0);
        this.getMenu().put(newFood.getId(), newFood);
        System.out.println(newFood);
    }

    public void editFoodItem() {
        this.printMenu();
        System.out.println("Enter code of food item to edit");
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
        System.out.println(selectedItem);

    }

    public void printRewards() {
        System.out.println("Reward Points: " + this.getRewardPoints());
    }

    public void printMenu() {
        for (FoodItem item : getMenu().values()) {
            System.out.println(item);
        }
    }

    public void setDiscountOnOverallBill() {
        System.out.println("You cannot set a discount. Please upgrade your restaurant type to set discount.");
    }

    public void showUserName() {
        System.out.println(this.getName());
    }

    public void showUserDetails() {
        System.out.println(this.getName() + ", " + this.getAddress() + ", " + this.getNumberOfOrders());
    }

    public void showUserMenu() {
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Welcome " + this.getName());
            System.out.println("1. Add Item");
            System.out.println("2. Edit Item");
            System.out.println("3. Print Rewards");
            System.out.println("4. Discount on Bill Value ");
            System.out.println("5. Exit");
            int option = s.nextInt();
            switch (option) {
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

    public void increaseNumberOfOrders() {
        this.setNumberOfOrders(this.getNumberOfOrders() + 1);
    }

    public int calculateRewardValue(float orderValue) {
        return (int) (orderValue / this.getRewardPointThreshold()) * this.getRewardPointPerX();
    }

    public void addBalance(float orderValue) {
        this.setBalance(this.getBalance() + orderValue);
    }

    public void payApp(float charges) {
        this.getApp().addToBalance(charges);
    }

    public void checkout(float orderValue) {
        this.setRewardPoints(this.getRewardPoints() + this.calculateRewardValue(orderValue));
        this.increaseNumberOfOrders();
        //TODO DELETE FOOD ITEMS and choose item quantity less than specified
        this.addBalance(orderValue);
        this.payApp((float) (orderValue * 0.01));
    }
}
