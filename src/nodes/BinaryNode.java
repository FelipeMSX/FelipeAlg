package nodes;

import _abstract.Node;

/**
 * Created by Felipe on 02/06/2016.
 */
public class BinaryNode<E> extends Node<E> {
    private BinaryNode<E> father,left,right;

    public BinaryNode(){
        super();
    }
    public BinaryNode(E obj){
        super(obj);
    }

    public boolean hasFather(){
        return (father != null);
    }

    public boolean hasLeft(){
        return (left != null);
    }

    public boolean hasRight(){
        return (right != null);
    }

    public BinaryNode<E> getRight() {
        return right;
    }

    public void setRight(BinaryNode<E> right) {
        this.right = right;
    }

    public BinaryNode<E> getLeft() {
        return left;
    }

    public void setLeft(BinaryNode<E> left) {
        this.left = left;
    }

    public BinaryNode<E> getFather() {
        return father;
    }

    public void setFather(BinaryNode<E> father) {
        this.father = father;
    }

}
