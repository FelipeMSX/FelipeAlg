package structs;

import nodes.BinaryNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Felipe on 02/06/2016.
 */
public class AVLTree <E extends Comparable<E>> {
    private BinaryNode<E> root;
    private int currentSize;

    public void insert(E obj){
        throw new NotImplementedException();
    }

    public E remove(E obj){
        throw new NotImplementedException();
    }

    public E retrive(E obj){
        throw new NotImplementedException();
    }


    /**
     * @param obj Utilizado sua key para encontrar na coleção o objeto desejado.
     * @return BinaryNode com a posição específica de acordo com a key, se o objeto informado como parâmetro
     * ainda não existir retorna a posição anterior a ele onde ele deveria ficar.
     */
    private BinaryNode<E> findNode(E obj){
        BinaryNode<E> searchNode = root.getRight();

        while(true){
            if(searchNode.getObject().compareTo(obj) < 0){
                if(searchNode.hasRight()) {
                    searchNode = searchNode.getRight();
                }else{
                    return searchNode;
                }
            }
            else
            if(searchNode.getObject().compareTo(obj) > 0){
                if(searchNode.hasLeft()) {
                    searchNode = searchNode.getLeft();
                }else{
                    return searchNode;
                }
            }
            else
            if(searchNode.getObject().compareTo(obj) == 0){
                return searchNode;
            }
        }
    }

    /**
     * @return True se a lista estiver vazia, false se não estiver.
     */
    public boolean isEmpty(){
        return currentSize == 0;
    }


    /**
     * @return Um inteiro representando o atual tamanho da coleção.
     */
    public int getCurrentSize(){
        return currentSize;
    }
}
