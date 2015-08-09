package nodes;

public class BinaryTreeNode<E> extends Node<E> {
	
	private BinaryTreeNode<E> father,left,right;
	
	public BinaryTreeNode(){
		super();
		this.father = null;
		this.left = null;
		this.right = null;
	}
	
	public BinaryTreeNode(E object){
		super(object);
		this.father = null;
		this.left = null;
		this.right = null;
	}

	public BinaryTreeNode<E> getFather() {
		return father;
	}

	public void setFather(BinaryTreeNode<E> father) {
		this.father = father;
	}

	public BinaryTreeNode<E> getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode<E> left) {
		this.left = left;
	}

	public BinaryTreeNode<E> getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode<E> right) {
		this.right = right;
	}

	
}
