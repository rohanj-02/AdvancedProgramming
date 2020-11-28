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
