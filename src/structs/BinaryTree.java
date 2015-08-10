package structs;

import exception.EqualsElementException;
import interfaces.Common;
import nodes.BinaryTreeNode;

/**
 * Created by Felipe on 09/08/2015.
 */
public class BinaryTree<E extends Comparable<E>> implements Common<E> {

    private BinaryTreeNode<E> root; // ponteiro para o primeiro nó, não possui dados.
    private int size;

    public BinaryTree(){
        root = new BinaryTreeNode<E>();
        size = 0;
    }

    @Override
    public boolean insert(E object) throws EqualsElementException
    {
        BinaryTreeNode<E> node = new BinaryTreeNode<E>(object);
        if(isEmpty())
        {
            root.setFather(node);
            size++;
        }else
        {
            BinaryTreeNode<E> search = root.getFather();

            while(search != null){
                //(4 < 10) <= -1 OK; avançar para direita para encontrar valores corretos.
                if( search.getObject().compareTo(object) <= -1 )
                {
                    if(search.hasRightNode())
                    {
                        search = search.getRight();  // avançar para direita;
                    }else
                    {
                        search.setRight(node);
                        node.setFather(search);
                        break;
                    }

                }else if (search.getObject().compareTo(object) >= 1) {
                    if (search.hasLeftNode())
                    {
                        search = search.getLeft(); // avançar para direita;
                    } else
                    {
                        search.setLeft(node);
                        node.setFather(search);
                        break;
                    }
                }else // ==0 não permite itens iguais
                {
                    throw new EqualsElementException("Not allowed equals elements");
                }
            }
        }
        return false;
    }

    @Override
    public boolean remove(E object) {
        return (size == 0);
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public E getItem(E object) {
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

    @Override
    public void disposeAll() {

    }

    public void printList()
    {
        BinaryTreeNode node = root.getFather();

        printALL(node);
    }

    private void printALL(BinaryTreeNode node)
    {
        if(node != null)
        {
            printALL(node.getLeft());
            System.out.println(node.getObject());
            printALL(node.getRight());
        }
    }
}
