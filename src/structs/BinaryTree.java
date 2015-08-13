package structs;

import _abstract.Tree;
import exception.ElementNotFoundException;
import exception.EmptyListException;
import exception.EqualsElementException;
import _interfaces.Common;
import nodes.TreeNode;

/**
 * Created by Felipe on 09/08/2015.
 */
public class BinaryTree<E extends Comparable<E>> extends Tree<E> {

    public BinaryTree()
    {
        super();
    }

    @Override
    public boolean insert(E object) throws EqualsElementException
    {
        TreeNode<E> node = new TreeNode<E>(object);
        if(isEmpty())
        {
            root.setFather(node);
            node.setFather(root);
        }else
        {
            TreeNode<E> search = root.getFather();

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

                }
                else if (search.getObject().compareTo(object) >= 1)
                {
                    if (search.hasLeftNode())
                    {
                        search = search.getLeft(); // avançar para direita;
                    } else
                    {
                        search.setLeft(node);
                        node.setFather(search);
                        break;
                    }
                }
                else // ==0 não permite itens iguais
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
            TreeNode<E> removeNode =  getNode(object);

            if(removeNode == null)
            {
                throw new ElementNotFoundException("The element not exists in the list.");
            }
            else
            {
                E objectReturn = removeNode.getObject();

                //Remoção do nó sem filhos.
                if(!removeNode.hasLeftNode() && !removeNode.hasRightNode())
                {
                    TreeNode<E> father = removeNode.getFather();
                    //Remoção do nó raiz
	                if(root.getFather().equals(removeNode))
		                root.setFather(null);
	                else if(father.hasLeftNode() && father.getLeft().equals(removeNode))
		                father.setLeft(null);
	                else
		                father.setRight(null);

	                //Cortando Ligações do nó.
	                removeNode.setFather(null);
                }
                //Remoção com só um filho na esquerda.
                else if(removeNode.hasLeftNode() && !removeNode.hasRightNode())
                {
                    TreeNode<E> father = removeNode.getFather();
                    removeNode.getLeft().setFather(father);
                    if(removeNode.equals(root.getFather()))
                        root.setFather(removeNode.getLeft());
                    else
                        father.setLeft(removeNode.getLeft());

	                //Cortando Ligações do nó.
	                removeNode.setFather(null);
	                removeNode.setLeft(null);
                }
                //Remoção com só um filho na direita.
                else if(!removeNode.hasLeftNode() && removeNode.hasRightNode())
                {
                    TreeNode<E> father = removeNode.getFather();
                    removeNode.getRight().setFather(father);

                    removeNode.getRight().setFather(father);
                    if(removeNode.equals(root.getFather()))
                        root.setFather(removeNode.getRight());
                    else
                        father.setRight(removeNode.getRight());

	                //Cortando Ligações do nó.
	                removeNode.setFather(null);
	                removeNode.setRight(null);
                }
                //Remoção com 2 filhos.
                /*
                 * Ir para direita e depois enquanto existir navegando somente para esquerda.
                 */
                else
                {

                    TreeNode<E> replaceNode = removeNode.getRight();
                    while(replaceNode.hasLeftNode())
                    {
                        replaceNode = replaceNode.getLeft();
                    }

                    //Não existe filhos a esquerda,
                    if(replaceNode.equals(removeNode.getRight()))
                    {
                        if(replaceNode.hasRightNode())
                            replaceNode.getRight().setFather(removeNode);

	                    removeNode.setRight(replaceNode.getRight());
	                    removeNode.setObject(replaceNode.getObject());

	                    //Cortando Ligações do nó.
	                    replaceNode.setFather(null);
	                    replaceNode.setRight(null);

                    }
                    //Se existir filho a esquerda,
                    else
                    {
                        removeNode.setObject(replaceNode.getObject());
	                    //Cortando Ligações do nó.
                        replaceNode.getFather().setLeft(null);
                        replaceNode.setFather(null);
                    }
                }

                size--;
                return objectReturn;
            }
        }
    }

    @Override
    protected TreeNode<E> getNode(E object)
    {
        return null;
    }

    @Override
    public E getFirst()
    {
        return null;
    }

    @Override
    public E getLast()
    {
        return null;
    }


}
