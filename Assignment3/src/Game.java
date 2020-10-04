import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private HashMap<Integer, Player> players;
    private final Controller<Mafia> MafiaController;
    private final Controller<Healer> HealerController;
    private final Controller<Detective> DetectiveController;
    private final Controller<Commoner> CommonerController;

    public Game() {
        players = new HashMap<Integer, Player>();
        MafiaController = new Controller<Mafia>();
        HealerController = new Controller<Healer>();
        DetectiveController = new Controller<Detective>();
        CommonerController = new Controller<Commoner>();
    }

    public void initialisePlayers() {
        Scanner s = new Scanner(System.in);
        System.out.println("Number of players: ");
        int numberOfPlayers = s.nextInt();
        ArrayList<Integer> randomSequence = generateRandomSequence(numberOfPlayers);
        System.out.println("1 for mafia");
        int userChoice = s.nextInt();
        int i = 0;
        setController(randomSequence, 0, numberOfPlayers/5, userChoice == 1, MafiaController, Mafia.class);
        setController(randomSequence, numberOfPlayers/5, 2*numberOfPlayers/5, userChoice == 2, DetectiveController, Detective.class);
        setController(randomSequence, 2*numberOfPlayers/5, 2*numberOfPlayers/5 + 1, userChoice == 3, HealerController, Healer.class);
        setController(randomSequence, 2*numberOfPlayers/5 + 1, numberOfPlayers, userChoice == 4, CommonerController, Commoner.class);

        for(Player p : players.values()){
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
//        HashMap<Integer, Detective> detective = new HashMap<>();
//        for (; i < 2 * numberOfPlayers / 5; i++) {
//            int index = randomSequence.get(i);
//            if (userChoice == 2 && i == 0) {
//                players.put(index, new Detective("Player" + index, true));
//                DetectiveController.setHasUser(true);
//            }
//            players.put(index, new Detective("Player" + index));
//            detective.put(index, (Detective) players.get(index));
//        }
//        DetectiveController.setPlayers(detective);
    }

    private <T> void setController(ArrayList<Integer> randomSequence, int startIndex, int endIndex, boolean hasUser, Controller<T> control, Class<? extends Player> tclass) {
        HashMap<Integer, T> group = new HashMap<>();
        for (int i = startIndex; i < endIndex; i++) {
            int index = randomSequence.get(i);
            if (hasUser && i == startIndex) {
                Class[] cArg = new Class[2];
                cArg[0] = String.class;
                cArg[1] = Boolean.class;
                try {
                    players.put(index, tclass.getDeclaredConstructor(cArg).newInstance("Player" + index, true));
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }
                control.setHasUser(true);
            } else {
                Class[] cArg = new Class[1];
                cArg[0] = String.class;
                try {
                    players.put(index, tclass.getDeclaredConstructor(cArg).newInstance("Player" + index));
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }
//            players.put(index, new ?("Player" + index));
                group.put(index, (T) players.get(index));
            }
        }
        control.setPlayers(group);
    }

    public ArrayList<Integer> generateRandomSequence(int n) {
        ArrayList<Integer> randomSequence = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            randomSequence.add(i + 1);
        }
        Collections.shuffle(randomSequence);
        return randomSequence;
    }
}
