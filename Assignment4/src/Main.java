import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Tree tree = new Tree(s.nextInt());
		tree.generateTree();
		System.out.println(tree.getHeight());
		System.out.println("The end");
	}
}
