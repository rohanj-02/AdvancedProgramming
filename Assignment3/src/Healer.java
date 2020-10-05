import java.util.ArrayList;

public class Healer extends Player{

//    @Override
//    public int specialVote(ArrayList<Player> playerList, ArrayList<Player> mafiaList) {
//        return this.vote(playerList);
//    }
public final static int INITIAL_HP = 800;

    public Healer(String name){
        super(name, INITIAL_HP);
    }

    public Healer(String name, Boolean isUser){
        super(name, isUser, INITIAL_HP);
    }

    @Override
    public Healer clone(){
        return (Healer) super.clone();
    }

    @Override
    public String toString() {
        return "Player(Healer){" +
                "healthPoints=" + this.getHealthPoints() +
                ", name='" + this.getName() + '\'' +
                ", isAlive=" + this.isAlive() +
                ", isUser=" + this.isUser() +
                '}';
    }
}


