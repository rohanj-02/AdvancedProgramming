import java.util.*;

public class Controller<T extends Player> {
    private HashMap<Integer, T> players;
    private boolean hasUser;
    private int userID;

    public Controller() {
        players = new HashMap<>();
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public HashMap<Integer, T> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<Integer, T> players) {
        this.players = players;
    }

    public HashMap<Integer, T> getAlivePlayers() {
        HashMap<Integer, T> returnVal = new HashMap<>();
        for (Map.Entry<Integer, T> p : players.entrySet()) {
            if (p.getValue().isAlive()) {
                returnVal.put(p.getKey(), p.getValue());
            }
        }
        return returnVal;
    }

    public Set<Integer> getPlayerIndex() {
        return players.keySet();
    }

    public boolean isHasUser() {
        return hasUser;
    }

    public void setHasUser(boolean hasUser) {
        this.hasUser = hasUser;
    }

    public int getIntegerInput(ArrayList<Integer> range, String inputMsg, String errorMsg) {
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        int input = 0;
        do {
            try {
                System.out.println(inputMsg);
                input = Integer.parseInt(s.next());
                for (Integer i : range) {
                    if (input == i) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    boolean temp = true;
                    for (Integer i : players.keySet()) {
                        if (input == i) {
                            temp = false;
                            System.out.println(errorMsg);
                        }
                    }
                    if (temp) {
                        System.out.println("You cannot choose a player that is out of the game.");
                    }
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("That was not a number.  Please try again.");
                flag = true;
            }
        } while (flag);
        return input;
    }

    public int preVote(Set<Integer> playerKeys, String inputMsg, String computerMsg, String errorMsg) {
        ArrayList<Integer> playerIDs = new ArrayList<>(playerKeys);
        int choice;
        if (this.numberOfAlive() > 0) {
            if (this.isHasUser() && this.players.get(this.userID).isAlive()) {
                choice = this.getIntegerInput(playerIDs, inputMsg, errorMsg);
            } else {
                int temp = (int) (Math.random() * playerIDs.size());
                choice = playerIDs.get(temp);
                System.out.println(computerMsg);
            }
        } else {
            System.out.println(computerMsg);
            choice = -1;
        }
        return choice;
    }

    public boolean hasPlayer(int index) {
        for (Integer i : players.keySet()) {
            if (i == index) {
                return true;
            }
        }
        return false;
    }

    public void removePlayer(int index) {
        if (this.hasPlayer(index)) {
            players.remove(index);
        }
    }

    public int numberOfAlive() {
        int count = 0;
        for (Player p : players.values()) {
            if (p.isAlive()) {
                count++;
            }
        }
        return count;
    }

    public void displayPlayers(String category) {
        if (players.size() > 1) {
            category = "were " + category + "s.";
            StringBuilder s = new StringBuilder();
            int count = players.size();
            for (Map.Entry<Integer, T> player : players.entrySet()) {
                if (count == 1) {
                    s.append(" and ").append(player.getValue().toString());
                } else if (count == players.size()) {
                    s.append(player.getValue().toString());
                } else {
                    s.append(", ").append(player.getValue().toString());
                }
                count--;
            }
            System.out.println(s + " " + category);
        } else {
            StringBuilder s = new StringBuilder();
            for (Map.Entry<Integer, T> player : players.entrySet()) {
                s.append(player.getValue().toString());
            }
            category = "was " + category + ".";
            s.append(" ").append(category);
            System.out.println(s);
        }
    }

    public void displayOtherPlayers(int x, String type) {

        System.out.println("You are " + players.get(x).toString());
        StringBuilder s = new StringBuilder("You are a " + type + ". Other " + type + "s are: [");
        int count = 0;
        for (Map.Entry<Integer, T> i : players.entrySet()) {
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