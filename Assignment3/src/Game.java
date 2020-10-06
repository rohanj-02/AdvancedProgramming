import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Game {
    private static final float HEAL_AMOUNT = 500;
    private final Controller<Mafia> MafiaController;
    private final Controller<Healer> HealerController;
    private final Controller<Detective> DetectiveController;
    private final Controller<Commoner> CommonerController;
    private final HashMap<Integer, Player> players;

    private int toHeal;
    private int toKill;
    private int toTest;
    private int toVote;
    private int userID;

    public Game() {
        this.players = new HashMap<>();
        this.MafiaController = new Controller<>();
        this.HealerController = new Controller<>();
        this.DetectiveController = new Controller<>();
        this.CommonerController = new Controller<>();
    }

    public static float getHealAmount() {
        return HEAL_AMOUNT;
    }

    public Controller<Mafia> getMafiaController() {
        return MafiaController;
    }

    public Controller<Healer> getHealerController() {
        return HealerController;
    }

    public Controller<Detective> getDetectiveController() {
        return DetectiveController;
    }

    public Controller<Commoner> getCommonerController() {
        return CommonerController;
    }

    public HashMap<Integer, Player> getPlayers() {
        return players;
    }

    public int getToHeal() {
        return toHeal;
    }

    public void setToHeal(int toHeal) {
        this.toHeal = toHeal;
    }

    public int getToKill() {
        return toKill;
    }

    public void setToKill(int toKill) {
        this.toKill = toKill;
    }

    public int getToTest() {
        return toTest;
    }

    public void setToTest(int toTest) {
        this.toTest = toTest;
    }

    public int getToVote() {
        return toVote;
    }

    public void setToVote(int toVote) {
        this.toVote = toVote;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    private <T extends Player> void setController(ArrayList<Integer> randomSequence, int startIndex, int numberOfEntries, boolean hasUser, Controller<T> control, Class<? extends Player> tclass) {
        HashMap<Integer, T> group = new HashMap<>();
        for (int i = startIndex; i < startIndex + numberOfEntries; i++) {
            int index = randomSequence.get(i);
            try {
                this.getPlayers().put(index, tclass.getDeclaredConstructor(new Class[]{String.class, Boolean.class}).newInstance("Player" + index, hasUser && i == startIndex));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
            if (hasUser && i == startIndex) {
                control.setHasUser(true);
                control.setUserID(index);
                this.setUserID(index);
            }
            group.put(index, (T) this.getPlayers().get(index));
        }
        control.setPlayers(group);
    }

    public ArrayList<Integer> generateRandomSequence(int n) {
        ArrayList<Integer> randomSequence = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            randomSequence.add(i + 1);
        }
        Collections.shuffle(randomSequence);
        return randomSequence;
    }

    public void initialisePlayers() {
        System.out.println("Welcome To Mafia");
        String inputMsg = "Enter the number of players: ";
        String errorMsg = "The minimum number of players is 6.\n";
        IntegerInput in = new IntegerInput();
        int numberOfPlayers = in.getIntegerInput(inputMsg, errorMsg, 6);
        ArrayList<Integer> randomSequence = this.generateRandomSequence(numberOfPlayers);
        ArrayList<Integer> inputMenuRange = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int userChoice = in.getIntegerInputInRange(inputMenuRange, "Choose a Character\n1)Mafia\n2)Detective\n3)Healer\n4)Commoner\n5)Assign Randomly\n", "Enter a valid input.\n");

        int numMafia = numberOfPlayers / 5;
        int numDetectives = numberOfPlayers / 5;
        int numHealers = Math.max(1, numberOfPlayers / 10);
        int numCommoners = numberOfPlayers - numMafia - numDetectives - numHealers;

        if (userChoice == 5) {
            userChoice = (int) (Math.random() * 4 + 1);
        }

        this.setController(randomSequence, 0, numMafia, userChoice == 1, this.getMafiaController(), Mafia.class);
        this.setController(randomSequence, numMafia, numDetectives, userChoice == 2, this.getDetectiveController(), Detective.class);
        this.setController(randomSequence, numMafia + numDetectives, numHealers, userChoice == 3, this.getHealerController(), Healer.class);
        this.setController(randomSequence, numberOfPlayers - numCommoners, numCommoners, userChoice == 4, this.getCommonerController(), Commoner.class);

        for (Player p : this.getPlayers().values()) {
            System.out.println(p);
        }

        if (this.getMafiaController().isHasUser()) {
            this.getMafiaController().displayOtherPlayers(this.getUserID(), "mafia");
        } else if (this.getDetectiveController().isHasUser()) {
            this.getDetectiveController().displayOtherPlayers(this.getUserID(), "detective");
        } else if (this.getHealerController().isHasUser()) {
            this.getHealerController().displayOtherPlayers(this.getUserID(), "healer");
        } else {
            System.out.println("You are " + this.getPlayers().get(this.getUserID()) + ".");
            System.out.println("You are a commoner.");
        }
    }

    private Set<Integer> getPreVoteList(Controller<?> controller) {
        Set<Integer> list = new HashSet<>(this.getPlayers().keySet());
        Set<Integer> controllerPlayers = controller.getPlayerIndex();
        for (Integer i : controllerPlayers) {
            list.remove(i);
        }
        for (Integer i : this.getPlayers().keySet()) {
            if (!this.getPlayers().get(i).isAlive()) {
                list.remove(i);
            }
        }
        return list;
    }

    public void preVote() {
        String inputMsg = "Choose a target: ";
        String computerMsg = "Mafias have chosen their target.\n";
        String errorMsg = "You cannot choose a Mafia as a target.\n";
        String errorMsgNotInRange = "Player selected is already dead or does not exist. Please select an alive person\n";
        this.setToKill(this.getMafiaController().preVote(this.getPreVoteList(this.getMafiaController()), inputMsg, computerMsg, errorMsgNotInRange, errorMsg));

        if (getToKill() != -1) {
            float damage = Mafia.decreaseHP(this.getPlayers().get(this.getToKill()).getHealthPoints(), this.getMafiaController().getAlivePlayers());
            this.getPlayers().get(this.getToKill()).decreaseHealthPoints(damage);
        }

        inputMsg = "Choose a player to test: ";
        computerMsg = "Detectives have chosen a player to test.\n";
        errorMsg = "You cannot test a Detective.\n";
        this.setToTest(this.getDetectiveController().preVote(this.getPreVoteList(this.getDetectiveController()), inputMsg, computerMsg, errorMsgNotInRange, errorMsg));

        if (this.getDetectiveController().isHasUser()) {
            if (this.getMafiaController().hasPlayer(this.getToTest())) {
                System.out.println("Player" + this.getToTest() + " is a Mafia.");
                this.setToVote(this.getToTest());
            } else {
                System.out.println("Player" + this.getToTest() + " is not a Mafia.");
            }
        }

        inputMsg = "Choose a player to heal: ";
        computerMsg = "Healers have chosen someone to heal.\n";
        errorMsg = "Cannot heal a player that is out of the game.\n";
        HashSet<Integer> list = new HashSet<>(this.getPlayers().keySet());
        for (Integer i : this.getPlayers().keySet()) {
            if (!this.getPlayers().get(i).isAlive()) {
                list.remove(i);
            }
        }
        this.setToHeal(this.getHealerController().preVote(list, inputMsg, computerMsg, errorMsgNotInRange, errorMsg));

        if (this.getToHeal() != -1) {
            this.getPlayers().get(this.getToHeal()).addHealthPoints(getHealAmount());
        }
    }

    public void initialiseVariables() {
        this.setToKill(-1);
        this.setToHeal(-1);
        this.setToTest(-1);
        this.setToVote(-1);
    }

    public int removePlayerFromGame(int index) {
        this.getPlayers().get(index).kill();
        return this.checkGameEnd();
    }

    public int vote() {
        int count = 0;
        HashSet<Integer> alivePlayers = new HashSet<>(this.getPlayers().keySet());
        for (Integer i : this.getPlayers().keySet()) {
            if (!this.getPlayers().get(i).isAlive()) {
                alivePlayers.remove(i);
            }
        }
        do {
            if (count == 0) {
                count = 1;
            } else {
                System.out.println("Voting tie! Vote in round again.");
            }
            HashMap<Integer, Integer> votes = new HashMap<>();
            for (Integer i : alivePlayers) {
                votes.put(i, 0);
            }
            for (Integer i : alivePlayers) {
                Player player = getPlayers().get(i);
                HashSet<Integer> available = new HashSet<>(alivePlayers);
                available.remove(i);
                int selection = player.vote(available);
                votes.replace(selection, votes.get(selection) + 1);
            }
            Map.Entry<Integer, Integer> maxEntry = null;
            int numberOfEntry = 0;
            for (Map.Entry<Integer, Integer> entry : votes.entrySet()) {
                if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                    maxEntry = entry;
                    numberOfEntry = 1;
                } else if (entry.getValue().equals(maxEntry.getValue())) {
                    maxEntry = entry;
                    numberOfEntry += 1;
                }
            }
            if (numberOfEntry == 1) {
                return maxEntry.getKey();
            }
        } while (true);
    }

    public void displayAlive() {
        int count = 0;
        for (Player player : this.getPlayers().values()) {
            if (player.isAlive()) {
                count++;
            }
        }
        int numberOfAlive = count;
        StringBuilder s = new StringBuilder(count + " players are remaining: ");
        for (Player player : this.getPlayers().values()) {
            if (player.isAlive()) {
                if (count == 1) {
                    s.append(" and ").append(player.getName());
                } else if (count == numberOfAlive) {
                    s.append(player.getName());
                } else {
                    s.append(", ").append(player.getName());
                }
                count--;
            }
        }
        System.out.println(s + " are alive.");
    }

    public void displayHP() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        for (Player player : this.getPlayers().values()) {
            if (player.isAlive()) {
                System.out.println(player.getName() + " " + df.format(player.getHealthPoints()));
            }
        }
    }

    public int playRound() {
        this.initialiseVariables();
        this.displayAlive();
        this.preVote();
        System.out.println("--End of Actions--");
        int noDeath = -1;
        for (Integer i : this.getPlayers().keySet()) {
            Player player = this.getPlayers().get(i);
            if (!this.getMafiaController().hasPlayer(i)) {
                if (player.getHealthPoints() == 0 && player.isAlive()) {
                    System.out.println(player.getName() + " has died.");
                    noDeath = i;
                }
            }
        }
        this.displayHP();
        if (noDeath == -1) {
            System.out.println("No one died.");
        } else {
            int gameStatus = this.removePlayerFromGame(noDeath);
            if (gameStatus != 0) {
                return gameStatus;
            }
        }
        int toRemove;
        if (this.getDetectiveController().isHasUser() && this.getToVote() != -1) {
            toRemove = this.getToVote();
        } else {
            toRemove = this.vote();
        }
        System.out.println(this.getPlayers().get(toRemove).toString() + " was voted out.");
        this.displayHP();
        return this.removePlayerFromGame(toRemove);
    }

    public int checkGameEnd() {
        if (this.getMafiaController().numberOfAlive() == 0) {
            return 1;
        } else if (this.getMafiaController().numberOfAlive() >= this.getDetectiveController().numberOfAlive() + this.getHealerController().numberOfAlive() + this.getCommonerController().numberOfAlive()) {
            return 2;
        } else {
            return 0;
        }
    }

    public void displayPlayers() {
        this.getMafiaController().displayPlayers("Mafia");
        this.getDetectiveController().displayPlayers("Detective");
        this.getHealerController().displayPlayers("Healer");
        this.getCommonerController().displayPlayers("Commoner");
    }

    public void playGame() {
        int count = 1;
        while (true) {
            System.out.println("Round " + count + ":");
            int status = this.playRound();
            System.out.println("--End of round " + count + "--\n");
            if (status == 1) {
                System.out.println("\nGame Over.\nThe Mafias Have Lost!");
                this.displayPlayers();
                break;
            } else if (status == 2) {
                System.out.println("\nGame Over.\nThe Mafias Have Won!");
                this.displayPlayers();
                break;
            }
            count++;
        }

    }

}
