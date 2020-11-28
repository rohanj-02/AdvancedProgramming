import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Tree tree = new Tree(s.nextInt());
		long startTime = System.nanoTime();
		tree.generateTree();
		long endTime = System.nanoTime();
		//divide by 1000000 to get milliseconds.
		System.out.println("Time taken: " + (endTime - startTime));
		System.out.println("Height: " + tree.getHeight());
		System.out.print("Input number of nodes to check: ");
		int numOfNodes = s.nextInt();
		HashMap<Integer, Integer> nodesToCheck = new HashMap<>(numOfNodes);
		for(int i = 0; i < numOfNodes; i++){
			nodesToCheck.put(s.nextInt(), -1);
		}
		System.out.println("Input the technique to check the program:\n1) Explicit Multithreading\n2) ForkJoinPool");
		int technique = s.nextInt();
		System.out.print("Enter number of threads to be used: ");
		int numThreads = s.nextInt();
		switch(technique){
			case 1:
//				explicit multithreading code elided
				System.out.println("Explicit");
				break;
			case 2:
				System.out.println("ForkJoinPool");
				ForkJoinPool pool = new ForkJoinPool(numThreads);
				TreeForkJoinPool task = new TreeForkJoinPool(tree.getRoot(), 0, nodesToCheck);
				pool.invoke(task);
				task.printResult();
				break;
			default:
				System.out.println("Wrong input");
		}
	}
}
