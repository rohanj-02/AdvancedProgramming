import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        int input = 0;
//        do {
//            System.out.println("Please give a number between: ");
//            try {
//                input = Integer.parseInt(s.next());
//            } catch (InputMismatchException | NumberFormatException e) {
//                System.out.println("That was not a number.  Please try again.");
//                input = -1; // guarantee we go around the loop again
//            }
//        }while (input < 0 );
        Game mafia = new Game();
        mafia.initialisePlayers();
        mafia.playGame();
    }
}
