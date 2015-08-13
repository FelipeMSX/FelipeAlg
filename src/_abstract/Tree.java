package _abstract;

import _interfaces.Common;
import exception.EmptyListException;
import nodes.TreeNode;

/**
 * Created by 3tecnos999 on 13/08/2015.
 */
public abstract class Tree<E extends Comparable<E>> implements Common<E> {

    protected TreeNode<E> root; // ponteiro para o primeiro nó, não possui dados.
    protected int size;

    public Tree()
    {
        root = new TreeNode<>();
        size = 0;
    }

    @Override
    public void disposeAll()
    {
        //Corta a ligação do filho com o ponteiro da raiz.
        if(size >=1)
            root.getFather().setFather(null);

        root.setFather(null);
        size = 0;
    }

    @Override
    public boolean isEmpty()
    {
        return (size == 0);
    }

    @Override
    public E getItem(E object)
    {
        TreeNode<E> node = getNode(object);
        return (node !=null) ? node.getObject() : null;
    }

    protected abstract TreeNode<E>  getNode(E object);

    //Preciso implementar Pilha primeiro;
    protected E depthFirstSearch(E object)
    {
        if(isEmpty())
        {
            throw new EmptyListException("The list no contain this element.");
        }
        else
        {


        }

        return null;
    }

    protected TreeNode<E> breadthFirstSearch(E object){return null;}
    //Somenete para teste
    public void printList()
    {
        TreeNode node = root.getFather();
        printALL(node);
    }

    //Somenete para teste
    private void printALL(TreeNode node)
    {
        if(node != null)
        {
            printALL(node.getLeft());
            System.out.println(node.getObject());
            printALL(node.getRight());
        }
    }
}
