public class AuthenticRestaurant extends Restaurant{

    public AuthenticRestaurant(String name, String address, FoodDeliveryApp app) {
        super(name, address, app);
        this.setPercentDiscount(0);
        this.setDiscount(50);
        this.setRewardPointPerX(25);
        this.setRewardPointThreshold(200);
    }
}
