import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

abstract public class Player implements Cloneable, Comparable<Player> {
    private final String name;
    private final boolean isUser;
    private final int ID;
    private float healthPoints;
    private boolean isAlive;

    public Player(String name, int INITIAL_HP) {
        this(name, false, INITIAL_HP);
    }

    public Player(String name, boolean isUser, int INITIAL_HP) {
        this.setHealthPoints(INITIAL_HP);
        this.setAlive(true);
        this.name = name;
        this.isUser = isUser;
        String temp = name.substring(6);
        this.ID = Integer.parseInt(temp);
    }

    public int getID() {
        return ID;
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

    protected float getHealthPoints() {
        return healthPoints;
    }

    protected void setHealthPoints(float healthPoints) {
        if (healthPoints < 0) {
            this.healthPoints = 0;
            this.setAlive(false);
            System.out.println("HP cannot go below 0! Setting HP to 0");
        } else {
            this.healthPoints = healthPoints;
        }
    }

    @Override
    public String toString() {
        // Development
//        return "Player{" +
//                "healthPoints=" + getHealthPoints() +
//                ", name='" + getName() + '\'' +
//                ", isAlive=" + isAlive() +
//                ", isUser=" + isUser() +
//                '}';
        // Submission
        String s = this.getName();
        if (this.isUser()) {
            s += "[User]";
        }
        return s;
    }

    @Override
    public int compareTo(Player obj) {
        return (int) (this.getHealthPoints() - obj.getHealthPoints());
    }

    @Override
    public Player clone() {
        try {
            return (Player) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public int vote(Set<Integer> playerKeys) {
        ArrayList<Integer> playerIDs = new ArrayList<>(playerKeys);
        if (this.isUser()) {
            String inputMsg = "Select a player to vote out: ";
            String errorMsg = "The selected player does not exist or is already dead!\n";
            String errorMsgInKeys = "You cannot vote yourself!\n";
            IntegerInput in = new IntegerInput();
            HashSet<Integer> temp = new HashSet<>();
            temp.add(this.getID());
            return in.getIntegerInputInRange(playerIDs, temp, inputMsg, errorMsg, errorMsgInKeys);
        } else {
            int max = playerIDs.size();
            return playerIDs.get((int) (Math.random() * max));
        }
    }

    public void addHealthPoints(float recovery) {
        if (recovery >= 0) {
            this.setHealthPoints(this.getHealthPoints() + recovery);
        } else {
            System.out.println("Error: Cannot recover with negative HP");
        }
    }

    public void decreaseHealthPoints(float damage) {
        if (damage >= 0) {
            this.setHealthPoints(this.getHealthPoints() - damage);
        } else {
            System.out.println("Error: Cannot decrease HP by a negative number");
        }
    }

    public void kill() {
        this.setAlive(false);
        this.setHealthPoints(0);
    }
}

// Code written by Rohan Jain
// 2019095
