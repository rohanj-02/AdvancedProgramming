interface Transporter {
    private void move() {
        System.out.println("yolo");
    }
    default void another(){
        move();
    }
}

public class Car implements Transporter{
    public void anotherfunc(){
        System.out.println("Another");
        another();
    }
    public static void main(String[] args) {
        Car x = new Car();
        x.another();
        Transporter c = x;
        c.another();
    }
}
