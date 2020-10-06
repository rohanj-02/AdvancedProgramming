import java.util.HashMap;

public class Mafia extends Player {

    private final static int INITIAL_HP = 2500;

    public Mafia(String name) {
        super(name, getInitialHp());
    }

    public Mafia(String name, Boolean isUser) {
        super(name, isUser, getInitialHp());
    }

    public static int getInitialHp() {
        return INITIAL_HP;
    }

    public static int decreaseHP(int HP, HashMap<Integer, Mafia> playerList) {
        boolean flag = true;
        int numberOfNonZero = 0;
        for (Mafia player : playerList.values()) {
            if (player.getHealthPoints() != 0) {
                numberOfNonZero++;
            }
        }
        int initialHP = HP;
        if (numberOfNonZero == 0) {
            return 0;
        }
        int totalDamage = 0;
        do {
            int healthPerMafia = HP / numberOfNonZero;
            int overflow = HP;
            if(HP < numberOfNonZero){
                for(Mafia mafiaPlayer: playerList.values()){
                    if(totalDamage < initialHP){
                        int damage = mafiaPlayer.targetDamage(1);
                        totalDamage += damage;
                        overflow -= damage;
                    }
                    else if(totalDamage >= initialHP){
                        break;
                    }
                }
            }
            else{
                for (Mafia mafiaPlayer : playerList.values()) {
                    if (mafiaPlayer.getHealthPoints() != 0) {
                        int damage = mafiaPlayer.targetDamage(healthPerMafia);
                        totalDamage += damage;
                        overflow -= damage;
                    }
                }
            }
            numberOfNonZero = 0;
            for (Mafia player : playerList.values()) {
                if (player.getHealthPoints() != 0) {
                    numberOfNonZero++;
                }
            }
            HP = overflow;
            if (numberOfNonZero == 0 || totalDamage == initialHP || overflow == 0) {
                flag = false;
            }
        } while (flag);
        return totalDamage;
    }

    @Override
    public Mafia clone() {
        return (Mafia) super.clone();
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

    public int targetDamage(int amount) {
        if (amount <= this.getHealthPoints()) {
            this.decreaseHealthPoints(amount);
            return amount;
        } else {
            int damage = this.getHealthPoints();
            this.decreaseHealthPoints(this.getHealthPoints());
            return damage;
        }
    }
}
