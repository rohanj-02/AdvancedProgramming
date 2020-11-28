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

class Employee{
    private String name;
    public Employee(){
        name = "noname";

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}