import java.util.ArrayList;

abstract public class Player implements Cloneable{
    public final static int INITIAL_HP = 500;
    private int healthPoints;
    private final String name;
    private boolean isAlive;
    private final boolean isUser;

    public Player(String name) {
        this(name, false);
    }

    public Player(String name, boolean isUser){
        this.healthPoints = INITIAL_HP;
        this.isAlive = true;
        this.name = name;
        this.isUser = isUser;
    }

    public static int getInitialHp() {
        return INITIAL_HP;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isUser() {
        return isUser;
    }

    protected int getHealthPoints() {
        return healthPoints;
    }

    protected void setHealthPoints(int healthPoints) {
        if(healthPoints < 0){
            this.healthPoints = 0;
            this.isAlive = false;
            System.out.println("HP cannot go below 0! Setting HP to 0");
        }
        else{
            this.healthPoints = healthPoints;
        }
    }

    public int vote(ArrayList<? extends Player> playerList){
        int max = playerList.size();
        return (int) (Math.random() * max + 1);
    }

    public void addHealthPoints(int recovery){
        if(recovery > 0){
            this.setHealthPoints(this.getHealthPoints() + recovery);
        }
        else{
            System.out.println("Error: Cannot recover with negative HP");
        }
    }

    public void decreaseHealthPoints(int damage){
        if(damage > 0){
            this.setHealthPoints(this.getHealthPoints() - damage);
        }
        else{
            System.out.println("Error: Cannot decrease HP by a negative number");
        }
    }
    abstract public int specialVote(ArrayList<Player> playerArrayList, ArrayList<Player> specialList);

    public Player clone(){
        try{
            return (Player)super.clone();
        }
        catch(CloneNotSupportedException e){
            return null;
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "healthPoints=" + healthPoints +
                ", name='" + name + '\'' +
                ", isAlive=" + isAlive +
                ", isUser=" + isUser +
                '}';
    }
}
