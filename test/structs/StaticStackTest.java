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
        StaticStack<Integer> stack = new StaticStack(3,false);
        stack.push(new Integer(2));
        stack.push(new Integer(4));
        stack.push(new Integer(6));

        assertTrue(stack.isFull());
        try{
            stack.push(new Integer(2));
            stack.push(new Integer(2));
            assertTrue(false);
        }catch (FullCollectionException e){

        }
        assertTrue(stack.isFull());
    }

    @Test
    public void testPop() throws Exception {
        StaticStack<Integer> stack = new StaticStack(10);
        stack.push(new Integer(2));
        stack.push(new Integer(4));
        stack.push(new Integer(6));
        assertEquals(new Integer(2),stack.pop());
        assertEquals(new Integer(4),stack.pop());
        assertEquals(new Integer(6),stack.pop());
        assertTrue(stack.isEmpty());

        //testar remo��o de objeto com vetor vazio.
        try{
            stack.pop();
            assertTrue(false);
        }catch (EmptyCollectionException e){

        }

    }

    @Test
    public void testGetFirst() throws  Exception{
        StaticStack<Integer> stack = new StaticStack(10);
        stack.push(new Integer(2));
        stack.push(new Integer(4));
        stack.push(new Integer(6));
        assertEquals(new Integer(2),stack.getFirst());
        stack.pop();
        stack.push(new Integer(12));
        assertEquals(new Integer(4),stack.getFirst());
    }

    @Test
    public void testGetLast() throws  Exception{
        StaticStack<Integer> stack = new StaticStack(10);
        stack.push(new Integer(2));
        stack.push(new Integer(4));
        stack.push(new Integer(6));
        assertEquals(new Integer(6),stack.getLast());
        stack.push(new Integer(20));
        assertEquals(new Integer(20),stack.getLast());
    }
}