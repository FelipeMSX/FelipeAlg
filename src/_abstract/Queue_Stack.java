package _abstract;

import exception.NullObjectException;

import java.util.Comparator;

/**
 * Classe abstrata usada como base para as filas e pilhas e suas estruturas derividas.
 */
public abstract class Queue_Stack <E> extends StaticStruct<E> {


    public abstract void push(E obj);
    public abstract E pop();

    public Queue_Stack(){
        super();
    }

    public Queue_Stack(int maxsize){
        super(maxsize);
    }

    public Queue_Stack(int maxsize, boolean isResizable){
        super(maxsize,isResizable);
    }

    public Queue_Stack(int maxsize, Comparator<E> comparator){
        super(maxsize,comparator);
    }

    public Queue_Stack(int maxsize, boolean isResizable, Comparator<E> comparator){
        super(maxsize,isResizable,comparator);
    }

}
