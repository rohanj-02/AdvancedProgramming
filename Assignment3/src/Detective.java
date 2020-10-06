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

    @Override
    public String toString() {
        return "Player(Detective){" +
                "healthPoints=" + this.getHealthPoints() +
                ", name='" + this.getName() + '\'' +
                ", isAlive=" + this.isAlive() +
                ", isUser=" + this.isUser() +
                '}';
    }
}
