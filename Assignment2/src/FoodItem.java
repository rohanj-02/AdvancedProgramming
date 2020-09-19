public class FoodItem {
    private String name;
    private int price;
    private int quantity;
    private String category;
    private int offer;
    final private Restaurant restaurant;
    final private int id;
    static private int COUNT = 0;

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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.getId() + " " +
                this.getRestaurant().getName() + " - " +
                this.getName() + " " +
                this.getPrice() + " " +
                this.getQuantity() + " " +
                this.getOffer() + "% off " +
                this.getCategory();
    }

    public FoodItem(String name, int price, int quantity, String category, int offer, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.offer = offer;
        this.restaurant = restaurant;
        this.id = COUNT;
        COUNT++;
    }
}
