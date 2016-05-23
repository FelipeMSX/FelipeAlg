package structs;

import exception.EmptyCollectionException;
import exception.FullCollectionException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Felipe on 22/05/2016.
 */
public class StaticStackTest {

    @Test
    public void testPush() throws Exception {
        StaticStack<Integer> queue = new StaticStack(100);
        queue.push(new Integer(4));
        queue.push(new Integer(7));
        queue.push(new Integer(10));
        queue.push(new Integer(11));

        //Checar o tamanho;
        assertEquals(4,queue.getCurrentSize());
        assertEquals(new Integer(7),queue.retrieve(new Integer(7)));
    }

    @Test
    public void testPop() throws Exception {
        StaticStack<Integer> queue = new StaticStack(100);
        queue.push(new Integer(4));

        assertEquals(new Integer(4),queue.pop());
        assertEquals(0,queue.getCurrentSize());

        try {
            queue.pop();
            assertTrue("A pilha não possui nenhum elemento!",false);
        }catch (EmptyCollectionException e){

        }
    }

    @Test
    public void testMaxCapacity() throws  Exception {
        StaticStack<Integer> queue = new StaticStack(2,false);
        queue.push(new Integer(4));
        queue.push(new Integer(7));

        try{
            queue.push(new Integer(10));
        }catch (FullCollectionException e){

        }

        assertEquals(2,queue.getMaxSize());
    }

    @Test
    public void testGetFirst() throws  Exception{
        StaticStack<Integer> queue = new StaticStack(10);
        queue.push(new Integer(2));
        queue.push(new Integer(4));
        queue.push(new Integer(6));
        assertEquals(new Integer(6),queue.getFirst());
        queue.pop();
        queue.push(new Integer(12));
        assertEquals(new Integer(12),queue.getFirst());
        queue.pop();
        queue.pop();
        queue.pop();
        try{
            queue.getFirst();
            assertTrue("A pilha deveria estar vazia!",false);
        }catch (EmptyCollectionException e){}
    }

    @Test
    public void testGetLast() throws  Exception{
        StaticStack<Integer> queue = new StaticStack(10);
        queue.push(new Integer(2));
        queue.push(new Integer(4));
        queue.push(new Integer(6));
        assertEquals(new Integer(2),queue.getLast());
        queue.push(new Integer(20));
        assertEquals(new Integer(2),queue.getLast());
        queue.pop();
        queue.pop();
        queue.pop();
        queue.pop();

        try{
            queue.getLast();
            assertTrue("A pilha deveria estar vazia!",false);
        }catch (EmptyCollectionException e){}
    }
}