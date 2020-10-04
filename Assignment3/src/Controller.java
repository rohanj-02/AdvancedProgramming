import java.util.HashMap;

public class Controller<T> {
    private HashMap<Integer, T> players;
    private boolean hasUser;

//    public Controller(HashMap<Integer, T> playerList) {
//        players = playerList;
//    }
//
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
}
