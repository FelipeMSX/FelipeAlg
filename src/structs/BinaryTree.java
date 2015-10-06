package structs;

import _abstract.Tree;
import exception.ElementNotFoundException;
import exception.EmptyListException;
import exception.EqualsElementException;
import nodes.TreeNode_Binary;

/**
 * Created by Felipe on 09/08/2015.
 */
public class BinaryTree<E extends Comparable<E>> extends Tree<E, TreeNode_Binary<E>> {

    public BinaryTree()
    {
        super();
        root = new TreeNode_Binary();
    }

    @Override
    public boolean insert(E object) throws EqualsElementException
    {
        TreeNode_Binary<E> node = new TreeNode_Binary<E>(object);
        if(isEmpty())
        {
            root.setFather(node);
            node.setFather(root);
        }else
        {
            TreeNode_Binary<E> search = (TreeNode_Binary<E>)root.getFather();

            while(search != null){
                //(4 < 10) <= -1 OK; avançar para direita para encontrar valores corretos.
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

                }
                else if (search.getObject().compareTo(object) >= 1)
                {
                    if (search.hasLeftNode())
                    {
                        search = search.getLeft(); // avan�ar para direita;
                    } else
                    {
                        search.setLeft(node);
                        node.setFather(search);
                        break;
                    }
                }
                else // ==0 n�o permite itens iguais
                    throw new EqualsElementException("Not allowed equals elements.");
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
        }
        else
        {
            TreeNode_Binary<E> removeNode =  getNode(object);

            if(removeNode == null)
            {
                throw new ElementNotFoundException("The element not exists in the list.");
            }
            else
            {
                E objectReturn = removeNode.getObject();

                //Remo��o do n� sem filhos.
                if(!removeNode.hasLeftNode() && !removeNode.hasRightNode())
                {
                    TreeNode_Binary<E> father = (TreeNode_Binary<E>)removeNode.getFather();
                    //Remo��o do n� raiz
	                if(root.getFather().equals(removeNode))
		                root.setFather(null);
	                else if(father.hasLeftNode() && father.getLeft().equals(removeNode))
		                father.setLeft(null);
	                else
		                father.setRight(null);

	                //Cortando Liga��es do n�.
	                removeNode.setFather(null);
                }
                //Remo��o com s� um filho na esquerda.
                else if(removeNode.hasLeftNode() && !removeNode.hasRightNode())
                {
                    TreeNode_Binary<E> father = (TreeNode_Binary<E>)removeNode.getFather();
                    removeNode.getLeft().setFather(father);
                    if(removeNode.equals(root.getFather()))
                        root.setFather(removeNode.getLeft());
                    else
                        father.setLeft(removeNode.getLeft());

	                //Cortando Liga��es do n�.
	                removeNode.setFather(null);
	                removeNode.setLeft(null);
                }
                //Remo��o com s� um filho na direita.
                else if(!removeNode.hasLeftNode() && removeNode.hasRightNode())
                {
                    TreeNode_Binary<E> father = (TreeNode_Binary<E>)removeNode.getFather();
                    removeNode.getRight().setFather(father);

                    removeNode.getRight().setFather(father);
                    if(removeNode.equals(root.getFather()))
                        root.setFather(removeNode.getRight());
                    else
                        father.setRight(removeNode.getRight());

	                //Cortando Liga��es do n�.
	                removeNode.setFather(null);
	                removeNode.setRight(null);
                }
                //Remo��o com 2 filhos.
                /*
                 * Ir para direita e depois enquanto existir navegando somente para esquerda.
                 */
                else
                {

                    TreeNode_Binary<E> replaceNode = removeNode.getRight();
                    while(replaceNode.hasLeftNode())
                    {
                        replaceNode = replaceNode.getLeft();
                    }

                    //N�o existe filhos a esquerda,
                    if(replaceNode.equals(removeNode.getRight()))
                    {
                        if(replaceNode.hasRightNode())
                            replaceNode.getRight().setFather(removeNode);

	                    removeNode.setRight(replaceNode.getRight());
	                    removeNode.setObject(replaceNode.getObject());

	                    //Cortando Liga��es do n�.
	                    replaceNode.setFather(null);
	                    replaceNode.setRight(null);

                    }
                    //Se existir filho a esquerda,
                    else
                    {
                        removeNode.setObject(replaceNode.getObject());
	                    //Cortando Ligacoes do nó.

                        ((TreeNode_Binary<E>)replaceNode.getFather()).setLeft(null);
                        replaceNode.setFather(null);
                    }
                }

                size--;
                return objectReturn;
            }
        }
    }

    @Override
    protected TreeNode_Binary<E> getNode(E object)
    {
        if(isEmpty())
        {
            throw new EmptyListException();
        }else
        {
            TreeNode_Binary<E> search = (TreeNode_Binary<E>)root.getFather();
            while(search != null)
            {
                if(search.getObject().compareTo(object) < 0)
                {
                    search = search.getRight();
                }
                else if((search.getObject().compareTo(object) > 0))
                {
                  search = search.getLeft();
                }
                else
                {
                   return search;
                }
            }
            return null;
        }
    }

    @Override
    //Primeiro mais à esquerda.
    public E getFirst()
    {
        if(isEmpty())
        {
            throw new EmptyListException();
        }else
        {
            TreeNode_Binary<E> search = (TreeNode_Binary<E>)root.getFather();
            while(search.hasLeftNode())
            {
                    search = search.getLeft();
            }
            return search.getObject();
        }
    }

    @Override
    public E getLast()
    {
        if(isEmpty())
        {
            throw new EmptyListException();
        }else
        {
            TreeNode_Binary<E> search = (TreeNode_Binary<E>)root.getFather();
            while(search.hasRightNode())
            {
                search = search.getRight();
            }
            return search.getObject();
        }
    }

}
