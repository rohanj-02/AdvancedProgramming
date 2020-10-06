public class Commoner extends Player {
    private final static int INITIAL_HP = 1000;

    public Commoner(String name, Boolean isUser) {
        super(name, isUser, getInitialHp());
    }

    public Commoner(String name) {
        super(name, getInitialHp());
    }

    public static int getInitialHp() {
        return INITIAL_HP;
    }

    @Override
    public Commoner clone() {
        return (Commoner) super.clone();
    }

}
