package structs;

import nodes.BinaryTreeNode;

/**
 * Created by Felipe on 09/08/2015.
 */
public class BinaryTree<E extends Comparable<E>>   {

    private BinaryTreeNode<E> root; // ponteiro para o primeiro nó, não possui dados.

    public BinaryTree(){
        root = new BinaryTreeNode<E>();
    }
}
