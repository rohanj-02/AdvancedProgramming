public class SpecialCustomer extends Customer {
    public SpecialCustomer(String name, String address, FoodDeliveryApp app) {
        super(name, address, app);
        this.setDeliveryCharge(20);
        this.setCustomerDiscount(25);
    }

    @Override
    public void showUserName() {
        System.out.println(this.getName() + " (Special)");
    }

    @Override
    public void showUserDetails() {
        System.out.println(this.getName() + " (Special), " + this.getAddress() + ", " + this.getWallet().getAmount() + "/-");
    }

}

