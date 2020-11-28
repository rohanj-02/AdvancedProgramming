public class Tree {
	static final private Integer MAX_CHILDREN = 5;
	public int height;
	private Integer numberOfNodes;
	private TreeNode root;

	public Tree(int numberOfNodes) {
		this.numberOfNodes = numberOfNodes;
		this.root = new TreeNode();
		this.height = 0;
	}

	public Integer getNumberOfNodes() {
		return numberOfNodes;
	}

	public void setNumberOfNodes(Integer numberOfNodes) {
		this.numberOfNodes = numberOfNodes;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getRandomNumber(int maxNumber) {
		if (maxNumber == 0) {
			return 0;
		}
		return (int) (Math.random() * maxNumber + 1);
	}

	public void generateTree() {
		this.generateTree(this.root, 0, this.numberOfNodes - 1);
	}

	public void generateTree(TreeNode node, int height, int numberOfNodes) {
		if (numberOfNodes < 0) {
			return;
		}

		this.height = Math.max(this.height, height);

		int numberOfChildren = Math.min(getRandomNumber(MAX_CHILDREN), numberOfNodes);
		numberOfNodes -= numberOfChildren;

		for (int i = 0; i < numberOfChildren; i++) {
			int nodesOfChild = 0;
			if (i == numberOfChildren - 1) {
				nodesOfChild = numberOfNodes;
			} else {
				nodesOfChild = getRandomNumber(numberOfNodes);
				numberOfNodes -= nodesOfChild;
			}
			TreeNode child = new TreeNode();
			node.getChildren().add(child);
			generateTree(child, height + 1, nodesOfChild);
		}
	}



	//Old generate function
//	public void generateTree(TreeNode node, int height) {
//		if (node == null || (numberOfNodes <= 0 && node.getNumberOfChildren() == 0)) {
//			return;
//		}
//
//		height += 1;
//		this.height = Math.max(this.height, height);
//
//		// distribute number of nodes per child node as well with
//		int numChildren = node.getNumberOfChildren();
//		for (int i = 0; i < numChildren; i++) {
//			int numChildrenOfChild = Math.min(getRandomNumber(MAX_CHILDREN), numberOfNodes);
//			numberOfNodes -= numChildrenOfChild;
//			TreeNode currChild = new TreeNode(numChildrenOfChild);
//			node.getChildren().add(currChild);
//		}
//
//		for (TreeNode child : node.getChildren()) {
//			generateTree(child, height + 1);
//		}
//	}
}