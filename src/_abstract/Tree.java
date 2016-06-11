package _abstract;

import _enum.Node_Identifier;
import nodes.TreeNode;
import structs.LinkedList;

/**
 * Created by Felipe on 02/06/2016.
 */
public abstract class Tree<E extends Comparable<E>> {

    protected TreeNode<E> root;
    protected int currentSize;
    protected boolean allowEqualsElements;

    public abstract void insert(E obj);
    public abstract E remove(E obj);
    public abstract E retrieve(E obj);

    public Tree(){
        root = new TreeNode(Node_Identifier.ROOT);
    }

    public boolean isEmpty(){
        return currentSize == 0;
    }

    protected boolean isRoot(TreeNode<E> node){
        return node.getFather() == root;
    }

    public int getCurrentSize(){
        return currentSize;
    }

}
