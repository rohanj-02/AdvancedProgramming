import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveAction;

public class TreeForkJoinPool extends RecursiveAction {

	private final TreeNode root;
	private final Integer height;
	private final HashMap<Integer, Integer> nodesToCheck;

	public TreeForkJoinPool(TreeNode root, int height, HashMap<Integer, Integer> nodesToCheck) {
		this.root = root;
		this.height = height;
		this.nodesToCheck = nodesToCheck;
	}

	public void isNodeToCheck() {
		for (Map.Entry<Integer, Integer> entry : this.nodesToCheck.entrySet()) {
			if (entry.getKey().equals(root.getData())) {
				entry.setValue(this.height);
				return;
			}
		}
	}

	@Override
	protected void compute() {
		if (this.root == null) {
			return;
		}
		this.isNodeToCheck();
		for (TreeNode child : root.getChildren()) {
			TreeForkJoinPool pool = new TreeForkJoinPool(child, height + 1, nodesToCheck);
			pool.fork();
		}
		// No need of join as it is not required. Add helpQuiesce() somewhere
		helpQuiesce();
	}

	public void printResult() {
		for (Map.Entry<Integer, Integer> entry : this.nodesToCheck.entrySet()) {
			if (entry.getValue().equals(-1)) {
				System.out.println("No occurrences of " + entry.getKey() + ".");
			} else {
				System.out.println("Found " + entry.getKey() + " at depth " + entry.getValue() + ".");
			}
		}
	}
}