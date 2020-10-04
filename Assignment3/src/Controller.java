import java.util.*;

public class Controller<T> {
    private HashMap<Integer, T> players;
    private boolean hasUser;

    public Controller() {
        players = new HashMap<>();
    }

    public HashMap<Integer, T> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<Integer, T> players) {
        this.players = players;
    }

    public boolean isHasUser() {
        return hasUser;
    }

    public void setHasUser(boolean hasUser) {
        this.hasUser = hasUser;
    }

    public int getIntegerInput(ArrayList<Integer> range){
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        int input = 0;
        do {
        try {
                input = Integer.parseInt(s.next());
                for(Integer i : range){
                    if (input == i) {
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    System.out.println("Player" + input + "is already dead or doesn't exist! Please enter a valid playerID.");
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("That was not a number.  Please try again.");
                flag = true;
            }
        }while (flag);
        return input;
    }

    public int preVote(Set<Integer> playerKeys) {
        for(Integer i: players.keySet()){
            playerKeys.remove(i);
        }
        ArrayList<Integer> playerIDs = new ArrayList<>(playerKeys);
        int choice;
        if(this.isHasUser()){
            choice = this.getIntegerInput(playerIDs);
        }
        else{
            int temp = (int)(Math.random() * playerIDs.size() + 1);
            choice = playerIDs.get(temp);
        }
        return choice;
    }
}