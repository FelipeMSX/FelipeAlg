package  nodes;

import _abstract.TreeNode;

public class TreeNode_Binary<E> extends TreeNode<E> {
	
	private TreeNode_Binary<E> left,right;
	
	public TreeNode_Binary(){
		super();
		this.left = null;
		this.right = null;
	}
	
	public TreeNode_Binary(E object){
		super(object);
		this.left = null;
		this.right = null;
	}

	public void setLeft(TreeNode_Binary<E> left) {
		this.left = left;
	}

	public TreeNode_Binary<E> getLeft() {
		return left;
	}

	public TreeNode_Binary<E> getRight() {
		return right;
	}

	public void setRight(TreeNode_Binary<E> right) {
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

}
