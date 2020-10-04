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

    public Set<Integer> getPlayerIndex(){
        return players.keySet();
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

    public int getIntegerInput(ArrayList<Integer> range, String inputMsg, String errorMsg){
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        int input = 0;
        do {
        try {
            System.out.println(inputMsg);
                input = Integer.parseInt(s.next());
                for(Integer i : range){
                    if (input == i) {
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    boolean temp = true;
                    for(Integer i : players.keySet()){
                        if(input == i){
                            temp = false;
                            System.out.println(errorMsg);
                        }
                    }
                    if(temp){
                        System.out.println("You cannot choose a player that is out of the game.");
                    }
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("That was not a number.  Please try again.");
                flag = true;
            }
        }while (flag);
        return input;
    }

    public int preVote(Set<Integer> playerKeys, String inputMsg, String computerMsg, String errorMsg) {
        ArrayList<Integer> playerIDs = new ArrayList<>(playerKeys);
        int choice;
        //add check for null list
        if(this.isHasUser()){
            choice = this.getIntegerInput(playerIDs, inputMsg, errorMsg);
        }
        else{
            int temp = (int)(Math.random() * playerIDs.size());
            choice = playerIDs.get(temp);
            System.out.println(computerMsg);
        }
        return choice;
    }

    public boolean hasPlayer(int index){
        for(Integer i : players.keySet()){
            if(i == index){
                return true;
            }
        }
        return false;
    }

    public void removePlayer(int index){
        if(this.hasPlayer(index)){
            players.remove(index);
        }
    }
}