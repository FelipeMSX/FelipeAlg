package structs;

import _abstract.Tree;
import nodes.TreeNode_Binary;

/**
 * Created by 3tecnos999 on 17/08/2015.
 */
public class TreeRedBlack<E extends Comparable<E>> extends Tree<E, TreeNode_Binary<E>> {


    @Override
    protected TreeNode_Binary<E> getNode(E object) {
        return null;
    }

    @Override
    public boolean insert(E object) {
        return false;
    }

    @Override
    public E remove(E object) {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }
}
