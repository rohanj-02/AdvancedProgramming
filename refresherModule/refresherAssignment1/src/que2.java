//WRITTEN BY ROHAN JAIN
//2019095

import java.util.Scanner;

public class que2 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.next();
        String output = "";
        char current = input.charAt(0);
        int count = 1;
        for(int i = 1; i < input.length(); ++i){
            if(current == input.charAt(i)){
                count++;
            }
            else{
                output += current + Integer.toString(count);
                current = input.charAt(i);
                count = 1;

            }
        }
        output += current + Integer.toString(count);
        System.out.println(output);
    }
}
