public class SpecialCustomer extends Customer{
    public SpecialCustomer(String name, String address, FoodDeliveryApp app) {
        super(name, address, app);
        this.setDeliveryCharge(20);
        this.setCustomerDiscount(25);
    }

}

