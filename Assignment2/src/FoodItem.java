public class FoodItem {
    static private int COUNT = 1;
    final private String restaurantName;
    final private int id;
    private String name;
    private int price;
    private int quantity;
    private String category;
    private int offer;

    public FoodItem(String name, int price, int quantity, String category, int offer, String restaurantName, int id) {
        this.setName(name);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setCategory(category);
        this.setOffer(offer);
        this.restaurantName = restaurantName;
        if (id == 0) {
            this.id = getCOUNT();
            setCOUNT(getCOUNT() + 1);
        } else {
            this.id = id;
        }

    }

    public static int getCOUNT() {
        return COUNT;
    }

    public static void setCOUNT(int COUNT) {
        FoodItem.COUNT = COUNT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public String getRestaurant() {
        return getRestaurantName();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.getId() + " " +
                this.getRestaurant() + " - " +
                this.getName() + " " +
                this.getPrice() + " " +
                this.getQuantity() + " " +
                this.getOffer() + "% off " +
                this.getCategory();
    }

    public String getRestaurantName() {
        return restaurantName;
    }
}
