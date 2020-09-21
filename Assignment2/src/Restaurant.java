import java.util.HashMap;
import java.util.Scanner;

public class Restaurant implements User {
    private final String name;
    private final String address;
    private final HashMap<Integer, FoodItem> menu;
    private int numberOfOrders;
    private int rewardPoints;
    private float balance;
    private int discount;
    private int rewardPointPerX;
    private int rewardPointThreshold;
    private int percentDiscount;

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        this.menu = new HashMap<>();
        this.numberOfOrders = 0;
        this.percentDiscount = 0;
        this.discount = 0;
        this.rewardPointPerX = 5;
        this.rewardPointThreshold = 100;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    protected int getNumberOfOrders() {
        return numberOfOrders;
    }

    private void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    private int getRewardPoints() {
        return rewardPoints;
    }

    private void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    private float getBalance() {
        return balance;
    }

    private void setBalance(float balance) {
        this.balance = balance;
    }

    public HashMap<Integer, FoodItem> getMenu() {
        return menu;
    }

    public int getDiscount() {
        return discount;
    }

    protected void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getPercentDiscount() {
        return percentDiscount;
    }

    protected void setPercentDiscount(int percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    private int getRewardPointPerX() {
        return rewardPointPerX;
    }

    protected void setRewardPointPerX(int rewardPointPerX) {
        this.rewardPointPerX = rewardPointPerX;
    }

    private int getRewardPointThreshold() {
        return rewardPointThreshold;
    }

    protected void setRewardPointThreshold(int rewardPointThreshold) {
        this.rewardPointThreshold = rewardPointThreshold;
    }

    private void addFoodItemToMenu() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter item name: ");
        String name = s.next();
        name += s.nextLine();
        System.out.println("Enter item price: ");
        int price = s.nextInt();
        System.out.println("Enter item quantity: ");
        int quantity = s.nextInt();
        System.out.println("Enter item category: ");
        String category = s.next();
        category += s.nextLine();
        System.out.println("Enter item offer: ");
        int offer = s.nextInt();
        FoodItem newFood = new FoodItem(name, price, quantity, category, offer, this.getName(), 0);
        this.getMenu().put(newFood.getId(), newFood);
        System.out.println(newFood);
    }

    private void editFoodItem() {
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
                newName += s.nextLine();
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
                newCategory += s.nextLine();
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

    private void printRewards() {
        System.out.println("Reward Points: " + this.getRewardPoints());
    }

    public void printMenu() {
        for (FoodItem item : getMenu().values()) {
            System.out.println(item);
        }
    }

    protected void setDiscountOnOverallBill() {
        System.out.println("You cannot set a discount. Please upgrade your restaurant type to set discount.");
    }

    @Override
    public void showUserName() {
        System.out.println(this.getName());
    }

    @Override
    public void showUserDetails() {
        System.out.println(this.getName() + ", " + this.getAddress() + ", " + this.getNumberOfOrders());
    }

    @Override
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

    @Override
    public void addRewardPoints(int x) {
        this.setRewardPoints(this.getRewardPoints() + x);
    }

    public void decreaseQuantity(HashMap<Integer, FoodItem> hash) {
        for (FoodItem item : hash.values()) {
            this.getMenu().get(item.getId()).decreaseQuantity(item.getQuantity());
        }
    }
}
