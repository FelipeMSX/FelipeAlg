package _abstract;

/**
 * Created by Felipe on 22/05/2016.
 */
public abstract class Queue_Stack <E extends  Comparable<E>> extends StaticStruct<E> {

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

}
