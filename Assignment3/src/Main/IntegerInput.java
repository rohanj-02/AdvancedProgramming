package Main;

import java.util.*;

public class IntegerInput {

    public int getIntegerInputInRange(ArrayList<Integer> range, Set<Integer> keys, String inputMsg, String errorMsgNotInRange, String errorMsgInKeys) {
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        int input = 0;
        do {
            try {
                System.out.print(inputMsg);
                input = Integer.parseInt(s.next());
                for (Integer i : range) {
                    if (input == i) {
                        flag = false;
                        break;
                    }
                }
                boolean temp = true;
                if (flag) {
                    for (Integer i : keys) {
                        if (input == i) {
                            temp = false;
                            System.out.print(errorMsgInKeys);
                        }
                    }
                    if (temp) {
                        System.out.print(errorMsgNotInRange);
                    }
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("That was not a number.  Please try again.");
                flag = true;
            }
        } while (flag);
        return input;
    }

    public int getIntegerInputInRange(ArrayList<Integer> range, String inputMsg, String errorMsg) {
        return getIntegerInputInRange(range, new HashSet<Integer>(), inputMsg, errorMsg, "");
    }

    public int getIntegerInput(String inputMsg, String errorMsg, int min, int max) {
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        int input = 0;
        do {
            System.out.print(inputMsg);
            try {
                input = Integer.parseInt(s.next());
                if (input >= min && input <= max) {
                    flag = false;
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

    public int getIntegerInput(String inputMsg, String errorMsg, int min) {
        return this.getIntegerInput(inputMsg, errorMsg, min, Integer.MAX_VALUE);
    }
}

// Code written by Rohan Jain
// 2019095
