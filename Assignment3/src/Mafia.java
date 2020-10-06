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

    @Override
    public Mafia clone() {
        return (Mafia) super.clone();
    }


    public float targetDamage(float amount) {
        if (amount <= this.getHealthPoints()) {
            this.decreaseHealthPoints(amount);
            return amount;
        } else {
            float damage = this.getHealthPoints();
            this.decreaseHealthPoints(this.getHealthPoints());
            return damage;
        }
    }

    public static float decreaseHP(float HP, HashMap<Integer, Mafia> playerList) {
        boolean flag = true;
        int numberOfNonZero = 0;
        for (Mafia player : playerList.values()) {
            if (player.getHealthPoints() != 0) {
                numberOfNonZero++;
            }
        }
        float initialHP = HP;
        if (numberOfNonZero == 0) {
            return 0;
        }
        float totalDamage = 0;
        do {
            float healthPerMafia = HP / numberOfNonZero;
            float overflow = HP;
                for (Mafia mafiaPlayer : playerList.values()) {
                    if (mafiaPlayer.getHealthPoints() != 0) {
                        float damage = mafiaPlayer.targetDamage(healthPerMafia);
                        totalDamage += damage;
                        overflow -= damage;
                    }
                }
            numberOfNonZero = 0;
            for (Mafia player : playerList.values()) {
                if (player.getHealthPoints() != 0) {
                    numberOfNonZero++;
                }
            }
            HP = overflow;
            if (numberOfNonZero == 0 || (totalDamage <= initialHP + 1e-6 && totalDamage >= initialHP - 1e-6) || (overflow >= 0 - 1e-6 && overflow <= 0 + 1e-6)) {
                flag = false;
            }
        } while (flag);
        return totalDamage;
    }

}
