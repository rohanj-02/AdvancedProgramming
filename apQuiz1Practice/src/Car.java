
public class Car{
    public static void main(String[] args) {
//        Car2 c1 = new Car2(2);
//        Car2 c2 = new Car2(2);
//        String s1 = "Hello";
//        String s2 = "Hello";
//        System.out.println(s1.equals(s2));
//        System.out.println(s1 == s2);
//        s2 = new String("Hello");
//        System.out.println(s1.equals(s2));
//        System.out.println(s1 == s2);

        SUV S1 = new SUV();
        Vehicle V1 = (Vehicle)S1;
        System.out.println(S1.getClass() == V1.getClass());
//        System.out.println(c1.equals(c2));
    }
}

//class Car2{
//    int test;
//    Car2(int x){
//        this.test = x;
//        System.out.println("Hello Car");
//    }
//    @Override
//    public boolean equals(Object o){
//        if(o == this){
//            return true;
//        }
//        if(!(o.getClass() == this.getClass())){
//            return false;
//        }
//        Car2 c = (Car2)o;
//        return c.test == this.test;
//
//    }
//}


class Vehicle{
    int x;
}

class SUV extends Vehicle{
    int y;
}