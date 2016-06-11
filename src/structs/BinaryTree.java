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
                    //Se o objeto a ser inserido for maior que o do nó atual é preciso avançar pra direita.
                    if(searchNode.getObject().compareTo(obj) < 0){
                        // Se ainda existe um nó a direita é preciso avançar para ele, caso contrário, está no local
                        //      exato para inserção do novo elemento.
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
                    //Se o objeto a ser inserido é menor que o nó atual é preciso avançar pra esquerda.
                    if(searchNode.getObject().compareTo(obj) > 0){
                        //Se ainda existe um nó a esquerda é preciso navegar para ele, se não existir nenhum nó a esquerda
                        //  significa que está no local exato para pôr o novo elemento.
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
                        Quando algum objeto é igual ele é colocado ao lado do seu correspondente, como se aquele nó
                        agora possuisse uma lista encadeada de elementos iguais.
                     */
                    }
                    else{
                        //Antes de inserir verifica se é permitido objetos com o mesmo ID.
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

            //Tratar remoção na raiz;
            //Tratar remoçao de nó com nenhum filho.
            //Tratar remoção de nó com 1 filho na esquerda.
            //Tratar remoção de um nó com 1 filho na direita.
            //Tratar remoção de nó com 2 filhos, NÃO É NECESSÁRIO.
            //Adicional em todos os casos de já existir um elemento com a mesma key.
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
