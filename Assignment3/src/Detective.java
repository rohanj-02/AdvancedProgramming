import java.util.ArrayList;

public class Detective extends Player{

//    @Override
//    public int specialVote(ArrayList<Player> playerList, ArrayList<Player> mafiaList) {
//        return 0;
////        return this.vote(playerList);
//    }

    public Detective(String name){
        super(name);
    }

    public Detective(String name, Boolean isUser){
        super(name, isUser);
    }

    @Override
    public Detective clone(){
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
