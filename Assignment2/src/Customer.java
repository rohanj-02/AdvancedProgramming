import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Customer implements User {

    private final Wallet wallet;
    private final String name;
    private final String address;
    private Order cart;
    private final ArrayList<Order> pastOrders;
    private final FoodDeliveryApp app;
    private int customerDiscount;
    private int deliveryCharge;

    public Customer(String name, String address, FoodDeliveryApp app) {
        this.name = name;
        this.address = address;
        this.pastOrders = new ArrayList<>();
        this.wallet = new Wallet(1000);
        this.app = app;
        this.deliveryCharge = 40;
        this.customerDiscount = 0;
    }

    protected Wallet getWallet() {
        return wallet;
    }

    public String getName() {
        return name;
    }

    protected String getAddress() {
        return address;
    }

    private Order getCart() {
        return cart;
    }

    private void setCart(Order cart) {
        this.cart = cart;
    }

    private ArrayList<Order> getPastOrders() {
        return pastOrders;
    }

    private int getCustomerDiscount() {
        return customerDiscount;
    }

    protected void setCustomerDiscount(int customerDiscount) {
        this.customerDiscount = customerDiscount;
    }

    private int getDeliveryCharge() {
        return deliveryCharge;
    }

    protected void setDeliveryCharge(int deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    private FoodDeliveryApp getApp() {
        return app;
    }

    private Restaurant getRestaurant(){
        return this.getCart().getRestaurant();
    }

    private void addFoodItemToCart(FoodItem newItem) {
        this.getCart().addFoodItem(newItem);
    }

    private void addToPastOrders(Order order) {
        this.getPastOrders().add(order);
    }

    private void initialiseCart() {
        this.setCart(new Order(this, this.getCustomerDiscount(), this.getDeliveryCharge()));
    }

    public void showUserName() {
        System.out.println(this.getName());
    }

    public void showUserDetails() {
        System.out.println(this.getName() + ", " + this.getAddress() + ", " + this.getWallet().getAmount() + "/-");
    }

    public void showUserMenu() {
        Scanner s = new Scanner(System.in);
        this.initialiseCart();
        boolean flag = true;
        while (flag) {
            System.out.println("Welcome " + this.getName());
            System.out.println("Customer Menu");
            System.out.println("1. Select Restaurant");
            System.out.println("2. Checkout cart");
            System.out.println("3. Reward won");
            System.out.println("4. Print Recent Orders");
            System.out.println("5. Exit");
            int option = s.nextInt();
            switch (option) {
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

    private void selectRestaurant() {
        Scanner s = new Scanner(System.in);
        if(this.getRestaurant() == null){
            System.out.println("Choose a restaurant: ");
            this.getCart().setRestaurant(this.getApp().selectRestaurant());
        }
        HashMap<Integer, FoodItem> menu = this.getRestaurant().getMenu();
        this.getRestaurant().printMenu();
        System.out.println("Choose item by code");
        int option = s.nextInt();
        System.out.println("Enter item quantity");
        int quantity = s.nextInt();
        FoodItem itemSelected = menu.get(option);

        this.addFoodItemToCart(new FoodItem(itemSelected.getName(), itemSelected.getPrice(), quantity, itemSelected.getCategory(), itemSelected.getOffer(), itemSelected.getRestaurantName(), itemSelected.getId()));
        System.out.println("Items added to cart.");
    }

    private void checkout() {
        System.out.println("Items in Cart - ");
        this.getCart().printFoodList();
        System.out.println("Delivery Charge - " + this.getCart().getDeliveryCharge() + "/-");
        System.out.println("Total order value - INR " + (this.getCart().getOrderValue() + this.getCart().getDeliveryCharge()));
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        while (this.getCart().getOrderValue() + this.getCart().getDeliveryCharge() > this.getWallet().getBalance()) {
            System.out.println("Balance Low \nDelete items from cart - ");
            this.getCart().printFoodList();
            int option = s.nextInt();
            this.getCart().deleteFoodItem(option);
            flag = false;
        }
        if (this.getCart().getFoodList().size() > 0) {
            if (!flag) {
                this.getCart().printFoodList();
                System.out.println("Delivery Charge - " + this.getCart().getDeliveryCharge() + "/-");
                System.out.println("Total order value - INR " + (this.getCart().getOrderValue() + this.getCart().getDeliveryCharge()));
            }
            System.out.println("1. Proceed to Checkout");
            System.out.println("2. Exit");
            int next = s.nextInt();
            if (next == 1) {
                System.out.println("Successfully placed order for INR " + (this.getCart().getOrderValue() + this.getCart().getDeliveryCharge()) + "/-");
                this.getWallet().deductBalance((this.getCart().getOrderValue() + this.getCart().getDeliveryCharge()));
                int rewards = this.getRestaurant().calculateRewardValue(this.getCart().getOrderValue());
                this.getRestaurant().checkout(this.getCart().getOrderValue());
                this.getWallet().addRewardPoints(rewards);
                //1 % payment to restaurant
                this.getApp().addToDeliveryCharge(this.getCart().getDeliveryCharge());
                this.addToPastOrders(this.getCart());
                this.initialiseCart();
            }
        } else {
            System.out.println("Empty Cart! Please add items to continue. ");
        }
    }

    private void printRecentOrders() {
        int i = 0;
        for (Order order : this.getPastOrders()) {
            i++;
            if (i < 10) System.out.println(order);
        }
    }

}
