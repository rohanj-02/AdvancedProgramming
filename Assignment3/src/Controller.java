import java.util.*;

public class Controller<T extends Player> {
    private TreeMap<Integer, T> players;
    private boolean hasUser;
    private int userID;

    public Controller() {
        this.setPlayers(new TreeMap<>());
        this.setHasUser(false);
        this.setUserID(0);
    }

    public TreeMap<Integer, T> getPlayers() {
        return players;
    }

    public void setPlayers(TreeMap<Integer, T> players) {
        this.players = players;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isHasUser() {
        return hasUser;
    }

    public void setHasUser(boolean hasUser) {
        this.hasUser = hasUser;
    }

    public TreeMap<Integer, T> getAlivePlayers() {
        TreeMap<Integer, T> returnVal = new TreeMap<>();
        for (Map.Entry<Integer, T> p : this.getPlayers().entrySet()) {
            if (p.getValue().isAlive()) {
                returnVal.put(p.getKey(), p.getValue());
            }
        }
        return returnVal;
    }

    public Set<Integer> getPlayerIndex() {
        return getPlayers().keySet();
    }

    public int preVote(Set<Integer> playerKeys, String inputMsg, String computerMsg, String errorMsgNotInRange, String errorMsg) {
        ArrayList<Integer> playerIDs = new ArrayList<>(playerKeys);
        int choice;
        IntegerInput in = new IntegerInput();
        if (this.numberOfAlive() > 0) {
            if (this.isHasUser() && this.getPlayers().get(this.getUserID()).isAlive()) {
                choice = in.getIntegerInputInRange(playerIDs, this.getPlayerIndex(), inputMsg, errorMsgNotInRange, errorMsg);
            } else {
                int temp = (int) (Math.random() * playerIDs.size());
                choice = playerIDs.get(temp);
                System.out.print(computerMsg);
            }
        } else {
            System.out.print(computerMsg);
            choice = -1;
        }
        return choice;
    }

    public boolean hasPlayer(int index) {
        for (Integer i : this.getPlayers().keySet()) {
            if (i == index) {
                return true;
            }
        }
        return false;
    }

    public int numberOfAlive() {
        int count = 0;
        for (Player p : this.getPlayers().values()) {
            if (p.isAlive()) {
                count++;
            }
        }
        return count;
    }

    public void displayPlayers(String category) {
        if (getPlayers().size() > 1) {
            category = "were " + category + "s.";
            StringBuilder s = new StringBuilder();
            int count = this.getPlayers().size();
            for (Map.Entry<Integer, T> player : this.getPlayers().entrySet()) {
                if (count == 1) {
                    s.append(" and ").append(player.getValue().toString());
                } else if (count == this.getPlayers().size()) {
                    s.append(player.getValue().toString());
                } else {
                    s.append(", ").append(player.getValue().toString());
                }
                count--;
            }
            System.out.println(s + " " + category);
        } else {
            StringBuilder s = new StringBuilder();
            for (Map.Entry<Integer, T> player : this.getPlayers().entrySet()) {
                s.append(player.getValue().toString());
            }
            category = "was " + category + ".";
            s.append(" ").append(category);
            System.out.println(s);
        }
    }

    public void displayOtherPlayers(int x, String type) {

        System.out.println("You are " + this.getPlayers().get(x).toString());
        StringBuilder s = new StringBuilder("You are a " + type + ". Other " + type + "s are: [");
        int count = 0;
        for (Map.Entry<Integer, T> i : this.getPlayers().entrySet()) {
            if (i.getKey() != x) {
                if (count == 0) {
                    s.append(i.getValue().toString());
                    count++;
                } else {
                    s.append(", ").append(i.getValue().toString());
                }
            }
        }
        s.append("]");
        System.out.println(s);
    }

}