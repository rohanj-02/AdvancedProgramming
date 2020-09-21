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
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.offer = offer;
        this.restaurantName = restaurantName;
        if (id == 0) {
            this.id = COUNT;
            COUNT++;
        } else {
            this.id = id;
        }

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

    public int getId() {
        return id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    @Override
    public String toString() {
        return this.getId() + " " +
                this.getRestaurantName() + " - " +
                this.getName() + " " +
                this.getPrice() + " " +
                this.getQuantity() + " " +
                this.getOffer() + "% off " +
                this.getCategory();
    }

    public void increaseQuantity(int x) {
        this.setQuantity(this.getQuantity() + x);
    }

    public void decreaseQuantity(int x) {
        this.increaseQuantity(-1 * x);
    }
}
