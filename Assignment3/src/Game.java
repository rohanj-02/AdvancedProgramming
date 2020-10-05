import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Game {
    private static final int HEAL_AMOUNT = 500;
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
        players = new HashMap<>();
        MafiaController = new Controller<>();
        HealerController = new Controller<>();
        DetectiveController = new Controller<>();
        CommonerController = new Controller<>();
    }

    public static int getHealAmount() {
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

    public int getIntegerInput() {
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        int input = 0;
        do {
            System.out.print("Enter the number of players: ");
            try {
                input = Integer.parseInt(s.next());
                if (input >= 6) {
                    flag = false;
                }
                if (flag) {
                    System.out.println("The minimum number of players is 6.");
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("That was not a number.  Please try again.");
                flag = true;
            }
        } while (flag);
        return input;
    }

    public int getIntegerInputInRange(ArrayList<Integer> range, String inputMsg, String errorMsg) {
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        int input = 0;
        do {
            System.out.print(inputMsg);
            try {
                input = Integer.parseInt(s.next());
                for (Integer i : range) {
                    if (input == i) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    System.out.println(errorMsg);
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("That was not a number.  Please try again.");
                flag = true;
            }
        } while (flag);
        return input;
    }

    public void initialisePlayers() {
        System.out.println("Welcome To Mafia");
        int numberOfPlayers = this.getIntegerInput();
        ArrayList<Integer> randomSequence = generateRandomSequence(numberOfPlayers);
        ArrayList<Integer> inputMenuRange = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int userChoice = this.getIntegerInputInRange(inputMenuRange, "Choose a Character\n1)Mafia\n2)Detective\n3)Healer\n4)Commoner\n5)Assign Randomly\n", "Enter a valid input");

        int numMafia = numberOfPlayers / 5;
        int numDetectives = numberOfPlayers / 5;
        int numHealers = Math.max(1, numberOfPlayers / 10);
        int numCommoners = numberOfPlayers - numMafia - numDetectives - numHealers;

        if (userChoice == 5) {
            userChoice = (int) (Math.random() * 4 + 1);
        }

        setController(randomSequence, 0, numMafia, userChoice == 1, getMafiaController(), Mafia.class);
        setController(randomSequence, numMafia, numDetectives, userChoice == 2, getDetectiveController(), Detective.class);
        setController(randomSequence, numMafia + numDetectives, numHealers, userChoice == 3, getHealerController(), Healer.class);
        setController(randomSequence, numberOfPlayers - numCommoners, numCommoners, userChoice == 4, getCommonerController(), Commoner.class);

        for (Player p : getPlayers().values()) {
            System.out.println(p);
        }

        if (getMafiaController().isHasUser()) {
            getMafiaController().displayOtherPlayers(getUserID(), "mafia");
        } else if (getDetectiveController().isHasUser()) {
            getDetectiveController().displayOtherPlayers(getUserID(), "detective");
        } else if (getHealerController().isHasUser()) {
            getHealerController().displayOtherPlayers(getUserID(), "healer");
        } else {
            System.out.println("You are " + getPlayers().get(getUserID()) + ".");
            System.out.println("You are a commoner.");
        }
        //        HashMap<Integer, Mafia> mafia = new HashMap<>();
//        for (; i < numberOfPlayers / 5; i++) {
//            int index = randomSequence.get(i);
//            if (userChoice == 1 && i == 0) {
//                players.put(index, new Mafia("Player" + index, true));
//                MafiaController.setHasUser(true);
//            }
//            players.put(index, new Mafia("Player" + index));
//            mafia.put(index, (Mafia) players.get(index));
//        }
//        MafiaController.setPlayers(mafia);
//
    }

    private <T extends Player> void setController(ArrayList<Integer> randomSequence, int startIndex, int numberOfEntries, boolean hasUser, Controller<T> control, Class<? extends Player> tclass) {
        HashMap<Integer, T> group = new HashMap<>();
        for (int i = startIndex; i < startIndex + numberOfEntries; i++) {
            int index = randomSequence.get(i);
            try {
                getPlayers().put(index, tclass.getDeclaredConstructor(new Class[]{String.class, Boolean.class}).newInstance("Player" + index, hasUser && i == startIndex));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
            if (hasUser && i == startIndex) {
                control.setHasUser(true);
                control.setUserID(index);
                this.setUserID(index);
            }
            group.put(index, (T) getPlayers().get(index));
        }
        control.setPlayers(group);
    }

    private Set<Integer> getPreVoteList(Controller<?> controller) {
        Set<Integer> list = new HashSet<>(getPlayers().keySet());
        Set<Integer> controllerPlayers = controller.getPlayerIndex();
        for (Integer i : controllerPlayers) {
            list.remove(i);
        }
        for (Integer i : getPlayers().keySet()) {
            if (!getPlayers().get(i).isAlive()) {
                list.remove(i);
            }
        }
        return list;
    }

    public void preVote() {
        String inputMsg = "Choose a target: ";
        String computerMsg = "Mafias have chosen their target.";
        String errorMsg = "You cannot choose a Mafia as a target. ";
        setToKill(getMafiaController().preVote(getPreVoteList(getMafiaController()), inputMsg, computerMsg, errorMsg));

        if (getToKill() != -1) {
            int damage = Mafia.decreaseHP(getPlayers().get(getToKill()).getHealthPoints(), getMafiaController().getAlivePlayers());
            getPlayers().get(getToKill()).decreaseHealthPoints(damage);
        }

        inputMsg = "Choose a player to test: ";
        computerMsg = "Detectives have chosen a player to test.";
        errorMsg = "You cannot test a Detective.";
        setToTest(getDetectiveController().preVote(getPreVoteList(getDetectiveController()), inputMsg, computerMsg, errorMsg));

        if (getDetectiveController().isHasUser()) {
            if (getMafiaController().hasPlayer(getToTest())) {
                System.out.println("Player" + getToTest() + " is a Mafia.");
                setToVote(getToTest());
            } else {
                System.out.println("Player" + getToTest() + " is not a Mafia.");
            }
        }

        inputMsg = "Choose a player to heal: ";
        computerMsg = "Healers have chosen someone to heal.";
        errorMsg = "Cannot heal a player that is out of the game. ";
        HashSet<Integer> list = new HashSet<>(getPlayers().keySet());
        for (Integer i : getPlayers().keySet()) {
            if (!getPlayers().get(i).isAlive()) {
                list.remove(i);
            }
        }
        setToHeal(getHealerController().preVote(list, inputMsg, computerMsg, errorMsg));

        if (getToHeal() != -1) {
            getPlayers().get(getToHeal()).addHealthPoints(getHealAmount());
        }
    }

    public void initialiseVariables() {
        this.setToKill(-1);
        this.setToHeal(-1);
        this.setToTest(-1);
        this.setToVote(-1);
    }

    public int removePlayerFromGame(int index) {
        //TODO Change all remove to isAlive = false
        //So that can print details in the end
//        MafiaController.removePlayer(index);
//        DetectiveController.removePlayer(index);
//        HealerController.removePlayer(index);
//        CommonerController.removePlayer(index);
        getPlayers().get(index).kill();
        return this.checkGameEnd();
    }

    public int vote() {
        int count = 0;
        HashSet<Integer> alivePlayers = new HashSet<>(getPlayers().keySet());
        for (Integer i : getPlayers().keySet()) {
            if (!getPlayers().get(i).isAlive()) {
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
        for (Player player : getPlayers().values()) {
            if (player.isAlive()) {
                count++;
            }
        }
        int numberOfAlive = count;
        StringBuilder s = new StringBuilder(count + " players are remaining: ");
        for (Player player : getPlayers().values()) {
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
//        s.insert(0, numberOfAlive);
        System.out.println(s + " are alive.");
    }

    public void displayHP() {
        for (Player player : getPlayers().values()) {
            if (player.isAlive()) {
                System.out.println(player.getName() + " " + player.getHealthPoints());
            }
        }
    }

    public int playRound() {
        this.initialiseVariables();
        this.displayAlive();
        this.preVote();
        System.out.println("--End of Actions--");
        int noDeath = -1;
        for (Integer i : getPlayers().keySet()) {
            Player player = getPlayers().get(i);
            if (!getMafiaController().hasPlayer(i)) {
                if (player.getHealthPoints() == 0 && player.isAlive()) {
                    System.out.println(player.getName() + " has died.");
                    noDeath = i;
                }
            }
        }
        this.displayHP();
        if (noDeath == -1) {
            //Came once randomly Maybe coz of healer.. Implement debug docs for clarity
            System.out.println("No one died.");
        } else {
            int gameStatus = this.removePlayerFromGame(noDeath);
            if (gameStatus != 0) {
                return gameStatus;
            }
        }
        int toRemove;
        if (getDetectiveController().isHasUser() && getToVote() != -1) {
            toRemove = getToVote();
        } else {
            toRemove = this.vote();
        }
        System.out.println(getPlayers().get(toRemove).toString() + " was voted out.");
        this.displayHP();
        return this.removePlayerFromGame(toRemove);
    }

//    public HashMap<String, Integer> numberOfAlive(){
//        HashMap<String, Integer> alive = new HashMap<>();
//        alive.put("Mafia", 0);
//        alive.put("Detective", 0);
//        alive.put("Commoner", 0);
//        alive.put("Healer", 0);
//        for(Player player: players.values()){
//            if(player.isAlive()){
//                if(player.getClass() == Mafia.class){
//                    alive.replace("Mafia", alive.get("Mafia") + 1);
//                }
//                else if(player.getClass() == Detective.class){
//                    alive.replace("Detective", alive.get("Detective") + 1);
//                }
//                else if(player.getClass() == Healer.class){
//                    alive.replace("Healer", alive.get("Healer") + 1);
//                }
//                else if(player.getClass() == Commoner.class){
//                    alive.replace("Commoner", alive.get("Commoner") + 1);
//                }
//            }
//        }
//        return alive;
//    }

    public int checkGameEnd() {
        if (getMafiaController().numberOfAlive() == 0) {
            return 1;
        } else if (getMafiaController().numberOfAlive() >= getDetectiveController().numberOfAlive() + getHealerController().numberOfAlive() + getCommonerController().numberOfAlive()) {
            return 2;
        } else {
            return 0;
        }
    }

    public void displayPlayers() {
        getMafiaController().displayPlayers("Mafia");
        getDetectiveController().displayPlayers("Detective");
        getHealerController().displayPlayers("Healer");
        getCommonerController().displayPlayers("Commoner");
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

    public ArrayList<Integer> generateRandomSequence(int n) {
        ArrayList<Integer> randomSequence = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            randomSequence.add(i + 1);
        }
        Collections.shuffle(randomSequence);
        return randomSequence;
    }
}
