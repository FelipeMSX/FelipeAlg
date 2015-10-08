package _abstract;

public abstract class TreeNode<E,V> extends Node<E> {

    private V father;

    public TreeNode(){
        super();
        this.father = null;
    }

    public TreeNode(E object){
        super(object);
        this.father = null;
    }

    public V getFather() {
        return father;
    }

    public void setFather(V father) {
        this.father = father;
    }

    public boolean hasFatherNode()
    {
        return (father != null);
    }
}
