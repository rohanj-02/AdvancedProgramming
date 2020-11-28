import java.util.ArrayList;

public class Car{
    public static void func1(){
            try{
                func2();
            }
            catch(SomeException e){
                System.out.println("YOLO");
            }

    }

    public static void func2 ()throws SomeException{
        func3();
    }

    public static void func3 () throws SomeException{
        throw new SomeException("Hello");
    }

    public static void main(String[] args) {
//    func1();
        ArrayList<String> arr;
//        System.out.println(arr);

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

class SomeException extends RuntimeException{
    public SomeException(String msg){
        super(msg);
    }
}
/*

1 line  2
2 2 3
3 2
    1a)
        Line 2 satisfies the 1st condition as a new object is being created so Java will not thing x.clone() == x; It also satisfies the third condition by typecasting the returned result.
        Line 2 also satisfies the second condition by initialising all the other variables in Point3D to the same value as in the this object.
        Line 3 satisfies the second condition by initialising the z variable of the new Point3D object to the z value of this object.
        Line 4 satisfies 1.

    1b) This implementation of clone does not violate all the requirements stated. However, this implementation is wrong because there should be deepcopy when we are making a clone. In this question, there will not be a copy of the List<String> in the object. Only reference will be passed. To make this correct, the clone() method should make a new List<String> of employeeNames with values same as original employeeNames.
        Can be done by:
        Organization copy = (Organization)super.clone();
        copy.setEmployeeNames(new ArrayList<String>(employeeNames));
        return copy;

    2a)
        10 [John, Mike] 100.02
        java.lang.NullPointerException
            at Organization.toString(Organization.java:33)
            at Organization.main(Organization.java:9)

    2b)
        After deserialization, deserializedOrg has values orgID = 10, employeeNames = null and profit= 0.00.
        This is because the fields employeeNames and profit are marked transient so they are not serialized. The employeeNames is given a default value which is null and profit is also given a default value which is 0.00.

    2c)
        There will be a NullPointerException because the value of employeeNames in deserializedOrg is null and we try to access its value in toString function on line 33 as shown in output in 2a).

    3) The diamond problem exists when there is a class A and 2 classes inherit A, say class B and class C. Now, suppose a class C inherits both B and C. If there is a function in class A, suppose display() and if class B and C override the method display for their own implementation. THen, there is a confusion on which display() method should class D call. This is known as the diamond problem. This is why Java does not support multiple inheritance.

    4)

public final class MakeThisImmutable {

    private final int id;
    private final String name;
    private final Employee emp;

    public MakeThisImmutable(int id, String name, Employee emp) {
        this.id = id;
        this.name = name;
        this.emp = emp;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

    5)

import java.util.ArrayList;

public class University {
    public void teachCourse() throws NullPointerException{
        try{
            this.enrollIntoCourse(); //Line 9
        }
        catch(NullPointerException e){
            e.printStackTrace();
            throw e;
        }
    }

    public void enrollIntoCourse() throws NullPointerException{
        try{
            this.getCourses(); //Line 17
        }
        catch(NullPointerException e){
            e.printStackTrace();
            throw e;
        }
    }

    public void getCourses() throws NullPointerException{

            ArrayList<Integer> arr = null;
            arr.get(20); //Line 27

    }
    public static void main(String[] args) {
        University obj = new University();
        try{
            obj.teachCourse(); //Line 37
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }

    }
}



 */
