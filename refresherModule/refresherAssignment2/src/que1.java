public class que1 {
    public static void main(String[] args) {
        Student[] data = new Student[10];
        for (int i = 0; i < 5; i++) {
            data[i] = new Student();
        }
        data[5] = new Student("Rohan", 19, "2019095", "CSE");
        data[6] = new Student("Unknown1", 19, "2019050", "CSAM");
        data[7] = new Student("Unknown2", 19, "2019366", "ECE");
        data[8] = new Student("Unknown3", 19, "2019256", "CSD");
        data[9] = new Student("Unknown4", 19, "2019183", "CSB");
        for(int i = 0; i < 10; ++i){
            data[i].display();
            System.out.println(Student.getLatest_roll_number());
        }
    }
}

class Student {
    private String name;
    private int age;
    private String roll_number;
    private String branch;
    static String latest_roll_number;

    public Student() {
        this.name = "John Doe";
        this.age = 20;
        this.roll_number = "222xxx";
        this.branch = "CSE";
        latest_roll_number = "222xxx";
    }

    public Student(String name, int age, String roll_number, String branch) {
        this.name = name;
        this.age = age;
        this.roll_number = roll_number;
        this.branch = branch;
        latest_roll_number = roll_number;
    }

    public void display() {
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + Integer.toString(this.age));
        System.out.println("Roll Number: " + this.roll_number);
        System.out.println("Branch: " + this.branch);
    }

    public static String getLatest_roll_number() {
        return latest_roll_number;
    }
}
