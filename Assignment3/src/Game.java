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

    public Game() {
        players = new HashMap<>();
        MafiaController = new Controller<>();
        HealerController = new Controller<>();
        DetectiveController = new Controller<>();
        CommonerController = new Controller<>();
    }

    public int getIntegerInput(){
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        int input = 0;
        do {
            System.out.println("Enter the number of players: ");
            try {
                input = Integer.parseInt(s.next());
                if(input >= 6){
                    flag = false;
                }
                if(flag){
                    System.out.println("The minimum number of players is 6.");
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("That was not a number.  Please try again.");
                flag = true;
            }
        }while (flag);
        return input;
    }

    public int getIntegerInputInRange(ArrayList<Integer> range, String inputMsg, String errorMsg){
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        int input = 0;
        do {
            System.out.println(inputMsg);
            try {
                input = Integer.parseInt(s.next());
                for(Integer i : range){
                    if (input == i) {
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    System.out.println(errorMsg);
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("That was not a number.  Please try again.");
                flag = true;
            }
        }while (flag);
        return input;
    }

    public void initialisePlayers() {

        int numberOfPlayers = this.getIntegerInput();
        ArrayList<Integer> randomSequence = generateRandomSequence(numberOfPlayers);
        ArrayList<Integer> inputMenuRange = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
        int userChoice = this.getIntegerInputInRange(inputMenuRange, "Choose a Character\n1)Mafia\n2)Detective\n3)Healer\n4)Commoner\n5)Assign Randomly", "Choose a valid input");

        int i = 0;
        int numMafia = (int)numberOfPlayers/5;
        int numDetectives = (int)numberOfPlayers/5;
        int numHealers = Math.max(1, (int)numberOfPlayers/10);
        int numCommoners = numberOfPlayers - numMafia - numDetectives - numHealers;

        setController(randomSequence, 0, numMafia, userChoice == 1, MafiaController, Mafia.class);
        setController(randomSequence, numMafia, numDetectives, userChoice == 2, DetectiveController, Detective.class);
        setController(randomSequence, numMafia + numDetectives, numHealers , userChoice == 3, HealerController, Healer.class);
        setController(randomSequence, numberOfPlayers - numCommoners, numCommoners, userChoice == 4, CommonerController, Commoner.class);

        for (Player p : players.values()) {
            System.out.println(p);
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

    private <T> void setController(ArrayList<Integer> randomSequence, int startIndex, int numberOfEntries, boolean hasUser, Controller<T> control, Class<? extends Player> tclass) {
        HashMap<Integer, T> group = new HashMap<>();
        for (int i = startIndex; i < startIndex + numberOfEntries; i++) {
            int index = randomSequence.get(i);
            Class[] cArg = new Class[2];
            cArg[0] = String.class;
            cArg[1] = Boolean.class;
            try {
                players.put(index, tclass.getDeclaredConstructor(cArg).newInstance("Player" + index, hasUser && i == startIndex));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
            if (hasUser) {
                control.setHasUser(true);
            }
            group.put(index, (T) players.get(index));
        }
        control.setPlayers(group);
    }

    private Set<Integer> getPreVoteList(Controller controller){
        Set<Integer> list = new HashSet<>(players.keySet());
        Set<Integer> controllerPlayers = controller.getPlayerIndex();
        for(Integer i: controllerPlayers){
            list.remove(i);
        }
        return list;
    }

    public void preVote(){
        String inputMsg = "Choose a target: ";
        String computerMsg = "Mafias have chosen their target.";
        String errorMsg = "You cannot choose a Mafia as a target. ";
        toKill = MafiaController.preVote(getPreVoteList(MafiaController), inputMsg, computerMsg, errorMsg);

        int damage = Mafia.decreaseHP(players.get(toKill).getHealthPoints(), MafiaController.getPlayers());
        players.get(toKill).decreaseHealthPoints(damage);


        inputMsg = "Choose a player to test: ";
        computerMsg = "Detectives have chosen a player to test.";
        errorMsg = "You cannot test a Detective.";
        toTest = DetectiveController.preVote(getPreVoteList(DetectiveController), inputMsg, computerMsg, errorMsg);

        if(DetectiveController.isHasUser()){
            if(MafiaController.hasPlayer(toTest)){
                System.out.println("Player" + toTest + " is a Mafia.");
            }
            else{
                System.out.println("Player" + toTest + " is not a Mafia.");
            }
        }

        inputMsg = "Choose a player to heal: ";
        computerMsg = "Healers have chosen someone to heal.";
        errorMsg = "Cannot heal a player that is out of the game. ";
        toHeal = HealerController.preVote(new HashSet<Integer>(players.keySet()), inputMsg, computerMsg, errorMsg);

        players.get(toHeal).addHealthPoints(HEAL_AMOUNT);
    }

    public void initialiseVariables(){
        this.toKill = -1;
        this.toHeal = -1;
        this.toTest = -1;
    }

    public void removePlayerFromGame(int index){
        //TODO Change all remove to isAlive = false
        //So that can print details in the end
        MafiaController.removePlayer(index);
        DetectiveController.removePlayer(index);
        HealerController.removePlayer(index);
        CommonerController.removePlayer(index);
        players.remove(index);
    }

    public int vote(){
        int count = 0;
        do{
            if(count == 0){
                count = 1;
            }
            else{
                System.out.println("Voting tie! Vote in round again.");
            }
            HashMap<Integer, Integer> votes = new HashMap<>();
            for(Integer i : players.keySet()) {
                votes.put(i, 0);
            }
            for(Integer i : players.keySet()){
                Player player = players.get(i);
                HashSet<Integer> available = new HashSet<>(players.keySet());
                available.remove(i);
                int selection = player.vote(available);
                votes.replace(selection, votes.get(selection) + 1);
            }
            Map.Entry<Integer, Integer> maxEntry = null;
            int numberOfEntry = 0;
            for(Map.Entry<Integer, Integer> entry : votes.entrySet()){
                if(maxEntry == null || entry.getValue() > maxEntry.getValue()){
                    maxEntry = entry;
                    numberOfEntry = 1;
                }
                else if(entry.getValue().equals(maxEntry.getValue())){
                    maxEntry = entry;
                    numberOfEntry += 1;
                }
            }
            if(numberOfEntry == 1){
                return maxEntry.getKey();
            }
        }while(true);
    }

    public void displayAlive()
    {
        StringBuilder s = new StringBuilder(players.keySet().size() + " players are remaining: ");
        for(Player player: players.values()){
            s.append(player.getName()).append(", ");
        }
        System.out.println(s + " are alive.");
    }

    public void playRound(){
        this.initialiseVariables();
        this.displayAlive();
        this.preVote();
        int noDeath = -1;
        for(Integer i : players.keySet()){
            Player player = players.get(i);
            if(!MafiaController.hasPlayer(i)){
                if(player.getHealthPoints() == 0){
                    System.out.println(player.getName() + " has died.");
                    noDeath = i;
                }
            }
        }
        if(noDeath == -1){
            //Came once randomly Maybe coz of healer.. Implement debug docs for clarity
            System.out.println("No one died.");
        }
        else{
            this.removePlayerFromGame(noDeath);
        }
        // TODO If mafia found out by detective user then directly vote person out.
        // TODO Game end condition
        int toRemove = this.vote();
        this.removePlayerFromGame(toRemove);
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
