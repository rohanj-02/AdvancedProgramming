public class FastFoodRestaurant extends Restaurant{
    public FastFoodRestaurant(String name, String address, FoodDeliveryApp app) {
        super(name, address, app);
        this.setPercentDiscount(0);
        this.setDiscount(50);
        this.setRewardPointPerX(25);
        this.setRewardPointThreshold(200);
    }

    @Override
    public void showUserDetails(){
        System.out.println(this.getName() + "(FastFood), " + this.getAddress() + ", " + this.getNumberOfOrders());
    }

    @Override
    public void showUserName(){
        System.out.println(this.getName() + "(FastFood)");
    }
}
