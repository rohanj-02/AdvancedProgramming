import java.util.ArrayList;

public class TreeNode {
	private Integer data;
	private ArrayList<TreeNode> children;

	public TreeNode() {
		this.data = (int) (Math.random() * 1000000 + 1);
		System.out.println(this.data);
		this.children = new ArrayList<>();
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}

	public ArrayList<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<TreeNode> children) {
		this.children = children;
	}

	public void traverseForkJoinPool(){

	}

}
