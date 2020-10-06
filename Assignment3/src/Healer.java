public class Healer extends Player {

    private final static int INITIAL_HP = 800;

    public Healer(String name) {
        super(name, getInitialHp());
    }

    public Healer(String name, Boolean isUser) {
        super(name, isUser, getInitialHp());
    }

    public static int getInitialHp() {
        return INITIAL_HP;
    }

    @Override
    public Healer clone() {
        return (Healer) super.clone();
    }

}


