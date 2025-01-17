import java.util.Scanner;

public class FastFoodRestaurant extends Restaurant {
    public FastFoodRestaurant(String name, String address) {
        super(name, address);
        this.setPercentDiscount(0);
        this.setDiscount(0);
        this.setRewardPointPerX(10);
        this.setRewardPointThreshold(150);
    }

    @Override
    public void showUserDetails() {
        System.out.println(this.getName() + " (FastFood), " + this.getAddress() + ", " + this.getNumberOfOrders());
    }

    @Override
    public void showUserName() {
        System.out.println(this.getName() + " (FastFood)");
    }

    @Override
    public void setDiscountOnOverallBill() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter overall discount to apply : ");
        int next = s.nextInt();
        this.setPercentDiscount(next);
    }

}
