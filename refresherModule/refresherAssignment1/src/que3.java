//WRITTEN BY ROHAN JAIN
//2019095

import java.util.Scanner;

public class que3 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.next();
        String output = "";
        long length = (long)input.length();
        while(length % 2 == 0){
            output += "2*";
            length /= 2;
        }
        for(long i = 3; i <= length; i += 2){
            while(length % i == 0){
                output += Long.toString(i) + "*";
                length /= i;
            }
        }
        output = output.substring(0, output.length() - 1);
        System.out.println(output);
    }
}
