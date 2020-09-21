public class EliteCustomer extends Customer {
    public EliteCustomer(String name, String address, FoodDeliveryApp app) {
        super(name, address, app);
        this.setDeliveryCharge(0);
        this.setCustomerDiscount(50);
    }

    @Override
    public void showUserName() {
        System.out.println(this.getName() + " (Elite)");
    }

    @Override
    public void showUserDetails() {
        System.out.println(this.getName() + " (Elite), " + this.getAddress() + ", " + this.getWallet().getAmount() + "/-");
    }

}

