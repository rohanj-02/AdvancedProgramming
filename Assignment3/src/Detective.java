public class Detective extends Player {

    private final static int INITIAL_HP = 800;

    public Detective(String name) {
        super(name, getInitialHp());
    }

    public Detective(String name, Boolean isUser) {
        super(name, isUser, getInitialHp());
    }

    public static int getInitialHp() {
        return INITIAL_HP;
    }

    @Override
    public Detective clone() {
        return (Detective) super.clone();
    }

}

// Code written by Rohan Jain
// 2019095
