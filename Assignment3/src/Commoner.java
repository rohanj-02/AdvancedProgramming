import java.util.ArrayList;

public class Commoner extends Player {

    public Commoner(String name, Boolean isUser){
        super(name, isUser);
    }
    public Commoner(String name){
        super(name);
    }

    @Override
    public int specialVote(ArrayList<Player> playerArrayList, ArrayList<Player> specialList) {
        return 0;
    }

    @Override
    public String toString() {
        return "Player(Commoner){" +
                "healthPoints=" + this.getHealthPoints() +
                ", name='" + this.getName() + '\'' +
                ", isAlive=" + this.isAlive() +
                ", isUser=" + this.isUser() +
                '}';
    }
}
