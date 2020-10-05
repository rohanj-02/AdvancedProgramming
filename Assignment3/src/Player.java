import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

abstract public class Player implements Cloneable{
    private int healthPoints;
    private final String name;
    private boolean isAlive;
    private final boolean isUser;

    public Player(String name, int INITIAL_HP) {
        this(name, false, INITIAL_HP);
    }

    public Player(String name, boolean isUser, int INITIAL_HP){
        this.healthPoints = INITIAL_HP;
        this.isAlive = true;
        this.name = name;
        this.isUser = isUser;
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

    public int getIntegerInputInRange(ArrayList<Integer> range, String inputMsg, String errorMsg){
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        int input = 0;
        do {
            System.out.println(inputMsg);
            try {
                input = Integer.parseInt(s.next());
                for(Integer i : range){
                    if (input == i) {
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    System.out.println(errorMsg);
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("That was not a number.  Please try again.");
                flag = true;
            }
        }while (flag);
        return input;
    }


    public int vote(Set<Integer> playerKeys){
        ArrayList<Integer> playerIDs = new ArrayList<>(playerKeys);
        if(this.isUser()){
            String inputMsg = "Select a player to vote out: ";
            String errorMsg = "The selected player does not exist!";
            return this.getIntegerInputInRange(playerIDs, inputMsg, errorMsg);
        }
        else{
            int max = playerIDs.size();
            return playerIDs.get((int) (Math.random() * max));
        }
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
//    abstract public int specialVote(ArrayList<Player> playerArrayList, ArrayList<Player> specialList);

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
