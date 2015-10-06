package _abstract;

import _interfaces.Common;
import exception.EmptyListException;

/**
 * Created by 3tecnos999 on 13/08/2015.
 */
public abstract class Tree<E extends Comparable<E>,V extends TreeNode<E>> implements Common<E> {

    protected V root; // ponteiro para o primeiro nó, não possui dados.
    protected int size;

    public Tree()
    {
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

    protected abstract V  getNode(E object);

    //Preciso implementar Pilha primeiro;
    protected V depthFirstSearch(E object)
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

    protected V breadthFirstSearch(E object){return null;}
}
