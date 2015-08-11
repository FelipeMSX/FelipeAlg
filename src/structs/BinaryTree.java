package structs;

import exception.ElementNotFoundException;
import exception.EmptyListException;
import exception.EqualsElementException;
import interfaces.Common;
import nodes.BinaryTreeNode;

/**
 * Created by Felipe on 09/08/2015.
 */
public class BinaryTree<E extends Comparable<E>> implements Common<E> {

    private BinaryTreeNode<E> root; // ponteiro para o primeiro n�, n�o possui dados.
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
            node.setFather(root);
            size++;
        }else
        {
            BinaryTreeNode<E> search = root.getFather();

            while(search != null){
                //(4 < 10) <= -1 OK; avan�ar para direita para encontrar valores corretos.
                if( search.getObject().compareTo(object) <= -1 )
                {
                    if(search.hasRightNode())
                    {
                        search = search.getRight();  // avan�ar para direita;
                    }else
                    {
                        search.setRight(node);
                        node.setFather(search);
                        break;
                    }

                }else if (search.getObject().compareTo(object) >= 1) {
                    if (search.hasLeftNode())
                    {
                        search = search.getLeft(); // avan�ar para direita;
                    } else
                    {
                        search.setLeft(node);
                        node.setFather(search);
                        break;
                    }
                }else // ==0 n�o permite itens iguais
                {
                    throw new EqualsElementException("Not allowed equals elements.");
                }
            }
        }
        size++;
        return true;
    }

    @Override
    public E remove(E object) {
        if(isEmpty())
        {
            throw new EmptyListException("The list no contain element this element.");
        }else
        {
            BinaryTreeNode<E> search =  getNode(object);

            if(search == null)
            {
                throw new ElementNotFoundException("The element not exists in the list.");
            }else
            {
                //Remo��o do n� sem filhos.
                if(!search.hasLeftNode() && !search.hasRightNode())
                {
                    //Remo��o do n� raiz
                    if(size == 1)
                    {
                        root.setFather(null);
                    }
                    //Remo��o de qualquer outro n�
                    else
                    {
                        BinaryTreeNode<E> father = search.getFather();
                        if(father.hasLeftNode() && father.getLeft().equals(search))
                            father.setLeft(null);
                        else
                            father.setRight(null);
                    }
                }
                //Remo��o com s� um filho na esquerda.
                else if(search.hasLeftNode() && !search.hasRightNode())
                {
                    BinaryTreeNode<E> father = search.getFather();
                    search.getLeft().setFather(father);
                    if(search.equals(root.getFather()))
                    {
                        root.setFather(search.getLeft());
                    }
                    else
                    {
                        father.setLeft(search.getLeft());
                    }
                }
                //Remo��o com s� um filho na direita.
                else if(!search.hasLeftNode() && search.hasRightNode())
                {
                    BinaryTreeNode<E> father = search.getFather();
                    search.getRight().setFather(father);

                    search.getRight().setFather(father);
                    if(search.equals(root.getFather()))
                    {
                        root.setFather(search.getRight());
                    }
                    else
                    {
                        father.setRight(search.getRight());
                    }
                }
                //Remo��o com 2 filhos.
                /*
                 * Ir para direita e depois enquanto existir navegando somente para esquerda.
                 */
                else
                {

                    BinaryTreeNode<E> replaceNode = search.getRight();
                    while(replaceNode.hasLeftNode())
                    {
                        replaceNode = replaceNode.getLeft();
                    }

                    //N�o existe filhos a esquerda, cons
                    if(replaceNode.equals(search.getRight()))
                    {
                        if(replaceNode.hasRightNode())
                        {
                            search.setRight(replaceNode.getRight());
                            replaceNode.getRight().setFather(search);
                        }else
                        {
                            search.setRight(null);
                            replaceNode.setFather(null);
                        }
                        search.setObject(replaceNode.getObject());

                    }
                    //Se existir filho a esquerda,
                    else
                    {
                        search.setObject(replaceNode.getObject());
                        replaceNode.getFather().setLeft(null);
                        replaceNode.setFather(null);
                    }
                }

                size--;
                return search.getObject();
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public E getItem(E object)
    {
        BinaryTreeNode<E> node = getNode(object);
        return (node !=null) ? node.getObject() : null;
    }

    private BinaryTreeNode<E> getNode(E object)
    {
        if(isEmpty())
        {
            throw new EmptyListException("The list no contain element to remove it.");
        }
        else
        {
            BinaryTreeNode<E> search = root.getFather();

            //Encontrar o n� a ser removido
            while(search != null)
            {
                //Avan�ara para direita
                if( search.getObject().compareTo(object) <= -1 )
                {
                    search = search.getRight();
                }
                //Avan�ara para esquerda
                else if (search.getObject().compareTo(object) >= 1)
                {
                    search = search.getLeft();
                }
                else
                {
                    break;
                }
         }
            return (search != null) ? search : null;
        }
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast()
    {
        return null;
    }

    @Override
    public void disposeAll()
    {
        root.setFather(null);
        size = 0;
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
