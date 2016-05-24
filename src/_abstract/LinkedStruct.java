package _abstract;

import _interfaces.Common;
import exception.EmptyCollectionException;
import nodes.LinkedNode;

import java.util.Iterator;


/**
 * Created by Felipe on 23/05/2016.
 */

/* Descrição:
        - Classe abstrata usada para construção das estruturas linkadas.
        - Possui um iterator por padrão.
 */
public abstract class LinkedStruct <E extends  Comparable<E>,T extends LinkedNode<E>> implements Common<E>,Iterable<E> {

    protected T head;
    protected int currentSize;


    public abstract void insert(E obj);
    //Remover um específico elemento da lista;
    public abstract E remove(E obj);
    //Remove o primeiro elemento da coleção a partir da raiz;
    public abstract E remove();

    public LinkedStruct()
    {
        head 	    = (T) new LinkedNode<E>();
        currentSize = 0;
    }


    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public E getFirst() {
        if(!isEmpty())
            return head.getNext().getObject();
        else
            throw new EmptyCollectionException();
    }

    @Override
    public E getLast() {
        if(!isEmpty()) {
            T temp = (T) head.getNext();

            while (temp.hasNextNode()){
                temp = (T) temp.getNext();
            }
            return temp.getObject();
        }
        else
            throw new EmptyCollectionException();
    }

    @Override
    public int getCurrentSize()
    {
        return currentSize;
    }

    @Override
    public void disposeAll() {
        head.setNext(null);
        currentSize = 0;
    }

    @Override
    public E retrieve(E obj) {
        if(!isEmpty()){

            T temp = (T) head.getNext();

            while (temp.getNext() != null){
                if(temp.getObject().compareTo(obj) == 0)
                    return temp.getObject();
                else
                    temp = (T) temp.getNext();
            }
            return null;
        }else{
            throw new EmptyCollectionException();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            T next = (T) head.getNext();
            @Override
            public boolean hasNext() {
                if(next == null)
                    return false;
                else{
                    return next.hasNextNode();
                }
            }

            @Override
            public E next() {

                E object = next.getObject();
                next = (T) next.getNext();
                return object;
            }
        };
    }
}