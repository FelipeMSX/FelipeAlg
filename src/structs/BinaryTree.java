package structs;

import _abstract.Tree;
import _enum.Node_Identifier;
import exception.EmptyCollectionException;
import exception.EqualsElementException;
import exception.NullObjectException;
import nodes.TreeNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Felipe on 01/06/2016.
 */
public class BinaryTree<E extends Comparable<E>> extends Tree<E>{

    public BinaryTree(){
        allowEqualsElements = true;
    }

    public BinaryTree(boolean allowEqualsElements){
        this.allowEqualsElements = allowEqualsElements;
    }

    @Override
    public void insert(E obj) {
        if(obj == null){
            throw new NullObjectException();
        }else{
            if(isEmpty()) {
                TreeNode<E> node = new TreeNode<E>(Node_Identifier.SOURCE, obj);
                node.setFather(root);
                root.putChild(node);
                currentSize++;
            }else{
                TreeNode<E> searchNode = root.children().getFirst();
                while(searchNode != null){
                    //Se o objeto a ser inserido for maior que o do n� atual � preciso avan�ar pra direita.
                    if(searchNode.getObject().compareTo(obj) < 0){
                        // Se ainda existe um n� a direita � preciso avan�ar para ele, caso contr�rio, est� no local
                        //      exato para inser��o do novo elemento.
                        if(hasRightNode(searchNode)){
                            searchNode = getRightNode(searchNode);
                        }
                        else{
                            TreeNode<E> node = new TreeNode<>(Node_Identifier.RIGHT, obj);
                            node.setFather(searchNode);
                            searchNode.putChild(node);
                            currentSize++;
                            return;
                        }
                    }
                    else
                    //Se o objeto a ser inserido � menor que o n� atual � preciso avan�ar pra esquerda.
                    if(searchNode.getObject().compareTo(obj) > 0){
                        //Se ainda existe um n� a esquerda � preciso navegar para ele, se n�o existir nenhum n� a esquerda
                        //  significa que est� no local exato para p�r o novo elemento.
                        if(hasLeftNode(searchNode)){
                            searchNode = getLeftNode(searchNode);
                        }
                        else{
                            TreeNode<E> node = new TreeNode<E>(Node_Identifier.LEFT, obj);
                            node.setFather(searchNode);
                            searchNode.putChild(node);
                            currentSize++;
                            return;
                        }
                    /*
                        Quando algum objeto � igual ele � colocado ao lado do seu correspondente, como se aquele n�
                        agora possuisse uma lista encadeada de elementos iguais.
                     */
                    }
                    else{
                        //Antes de inserir verifica se � permitido objetos com o mesmo ID.
                        if(allowEqualsElements) {
                            TreeNode<E> node = new TreeNode<E>(searchNode.getIdentifier(), obj);
                            node.setFather(searchNode.getFather());
                            searchNode.getFather().putChild(node);
                            currentSize++;
                            return;
                        }else{
                            throw new EqualsElementException();
                        }
                    }
                }
            }
        }
    }

    @Override
    public E remove(E obj) {
        if(obj == null){
            throw new NullObjectException();
        }
        else
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        else{
            TreeNode<E> searchNode = root.children().getFirst();

            //Tratar remo��o na raiz;
            //Tratar remo�ao de n� com nenhum filho.
            //Tratar remo��o de n� com 1 filho na esquerda.
            //Tratar remo��o de um n� com 1 filho na direita.
            //Tratar remo��o de n� com 2 filhos, N�O � NECESS�RIO.
            //Adicional em todos os casos de j� existir um elemento com a mesma key.
        }
        return null;
    }

    @Override
    public E retrieve(E obj) {
        return null;
    }


    private boolean hasEqualsNode(TreeNode<E> node){
        return node.retrieveSpecificChildren(node.getIdentifier()).getCurrentSize() > 0;
    }

    private boolean hasRightNode( TreeNode<E> node){
        return node.retrieveSpecificChildren(Node_Identifier.RIGHT).getCurrentSize() > 0;
    }

    private boolean hasLeftNode( TreeNode<E> node){
        return node.retrieveSpecificChildren(Node_Identifier.LEFT).getCurrentSize() > 0;
    }

    private TreeNode<E> getRightNode( TreeNode<E> node){
        return  hasRightNode(node) ? node.retrieveSpecificChildren(Node_Identifier.RIGHT).getFirst() : null;
    }

    private TreeNode<E> getLeftNode( TreeNode<E> node){
        return hasLeftNode(node) ? node.retrieveSpecificChildren(Node_Identifier.LEFT).getFirst() : null;
    }

    private TreeNode<E> getEqualsNode( TreeNode<E> node){
        return hasEqualsNode(node) ? node.retrieveSpecificChildren(node.getIdentifier()).getFirst() : null;
    }

    private void replaceEqualsNode(TreeNode node){
        LinkedList<TreeNode<E>> list = node.retrieveSpecificChildren(node.getIdentifier());
        if(list.getCurrentSize() > 2){

        }

    }

}
