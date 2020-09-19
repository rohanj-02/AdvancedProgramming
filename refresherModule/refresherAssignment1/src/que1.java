//WRITTEN BY ROHAN JAIN
//2019095

import java.util.Scanner;

public class que1 {

    static void printArray(int[] arr, int skip){
        for(int i = 0; i < arr.length; ++i){
            if(i == (skip - 1)){
                continue;
            }
            System.out.print(arr[i] + " ");
        }
    }

    static void printArray(int[] arr){
        printArray(arr, -1);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(--T >= 0){
            int n = s.nextInt();
            int[] in = new int[n];
            for(int i = 0; i < n; ++i){
                in[i] = s.nextInt();
            }
            int startingNode = s.nextInt();
            boolean isLoop = (startingNode != 0);
            int count = isLoop ? in.length - startingNode + 1: 0;
            if(isLoop){
                System.out.println("True");
                System.out.println(count);
                printArray(in, startingNode);
            }
            else{
                System.out.println("False");
                System.out.println(count);
                printArray(in);
            }
        }

    }
}
