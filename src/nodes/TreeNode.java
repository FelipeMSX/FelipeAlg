package  nodes;

public class TreeNode<E> extends Node<E>{
	
	private TreeNode<E> father,left,right;
	
	public TreeNode(){
		super();
		this.father = null;
		this.left = null;
		this.right = null;
	}
	
	public TreeNode(E object){
		super(object);
		this.father = null;
		this.left = null;
		this.right = null;
	}

	public TreeNode<E> getFather() {
		return father;
	}

	public void setFather(TreeNode<E> father) {
		this.father = father;
	}

	public TreeNode<E> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<E> left) {
		this.left = left;
	}

	public TreeNode<E> getRight() {
		return right;
	}

	public void setRight(TreeNode<E> right) {
		this.right = right;
	}

	public boolean hasLeftNode()
	{
		return (left != null);
	}

	public boolean hasRightNode()
	{
		return (right != null);
	}

	public boolean hasFatherNode()
	{
		return (father != null);
	}
}
