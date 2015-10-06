package _abstract;

public class TreeNode<E> extends Node<E> {

    private TreeNode<E> father;

    public TreeNode(){
        super();
        this.father = null;
    }

    public TreeNode(E object){
        super(object);
        this.father = null;
    }

    public TreeNode<E> getFather() {
        return father;
    }

    public void setFather(TreeNode<E> father) {
        this.father = father;
    }

    public boolean hasFatherNode()
    {
        return (father != null);
    }
}
