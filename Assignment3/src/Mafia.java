import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;

public class Mafia extends Player {

//    @Override
//    public int specialVote(ArrayList<Player> playerList, ArrayList<Player> mafiaList) {
//        return this.vote(playerList);
//    }
    public final static int INITIAL_HP = 2500;
    public Mafia(String name){
        super(name, INITIAL_HP);
    }

    public Mafia(String name, Boolean isUser){
        super(name, isUser, INITIAL_HP);
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

//    @Override
    public int targetDamage(int amount){
        //returns damage done
        if(amount <= this.getHealthPoints()){
            this.decreaseHealthPoints(amount);
            return amount;
        }
        else{
            int damage = this.getHealthPoints();
            this.decreaseHealthPoints(this.getHealthPoints());
            return damage;
        }
    }

    public static int decreaseHP(int HP, HashMap<Integer, Mafia> playerList){
        boolean flag = true;
        int numberOfNonZero = 0;
        //returns damage done
        for(Mafia player : playerList.values()){
            if(player.getHealthPoints() != 0){
                numberOfNonZero++;
            }
        }
        int initialHP = HP;
        if(numberOfNonZero == 0){
            return 0;
        }
        int totalDamage = 0;
        do{
            int healthPerMafia = (int)(HP/numberOfNonZero);
            int overflow = HP;

            for(Mafia mafiaPlayer : playerList.values() ){
                if(mafiaPlayer.getHealthPoints() != 0){
                    int damage =  mafiaPlayer.targetDamage(healthPerMafia);
                    totalDamage += damage;
                    overflow -= damage;
                }
            }
            numberOfNonZero = 0;
            for(Mafia player : playerList.values()){
                if(player.getHealthPoints() != 0){
                    numberOfNonZero++;
                }
            }
            HP = overflow;
            if(numberOfNonZero == 0){
                flag = false;
            }
            if(totalDamage == initialHP){
                flag = false;
            }
        }while(flag);
        return totalDamage;
    }
}
