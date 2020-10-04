import java.util.ArrayList;

public class Mafia extends Player {

    @Override
    public int specialVote(ArrayList<Player> playerList, ArrayList<Player> mafiaList) {
        return this.vote(playerList);
    }

    public Mafia(String name){
        super(name);
    }

    public Mafia(String name, Boolean isUser){
        super(name, isUser);
    }

    @Override
    public Mafia clone(){
        return (Mafia)super.clone();
    }

    @Override
    public String toString() {
        return "Player(Mafia){" +
                "healthPoints=" + this.getHealthPoints() +
                ", name='" + this.getName() + '\'' +
                ", isAlive=" + this.isAlive() +
                ", isUser=" + this.isUser() +
                '}';
    }
}
