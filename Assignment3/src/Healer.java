import java.util.ArrayList;

public class Healer extends Player{

//    @Override
//    public int specialVote(ArrayList<Player> playerList, ArrayList<Player> mafiaList) {
//        return this.vote(playerList);
//    }

    public Healer(String name){
        super(name);
    }

    public Healer(String name, Boolean isUser){
        super(name, isUser);
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


