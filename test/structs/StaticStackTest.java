package structs;

import exception.EmptyCollectionException;
import exception.FullCollectionException;
import exception.NullObjectException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Felipe on 22/05/2016.
 */
public class StaticStackTest {

    private StaticStack<Integer> stack;
    private StaticStack<Integer> stackLimited;

    @Before
    public void initialize() throws Exception {
        stackLimited = new StaticStack(3,false);
        stackLimited.push(3);
        stackLimited.push(6);
        stackLimited.push(9);
        stack = new StaticStack(3);
        stack.push(3);
        stack.push(6);
        stack.push(9);
    }


    @Test(expected = NullObjectException.class)
    public void testPushNullObject() throws Exception {
        stack.push(null);
    }

    @Test
    public void testPush() throws Exception {
        //Tetando inserção normal.
        stack.push(4);
        stack.push(7);
        stack.push(10);
        assertTrue("Tamanho deve ser 6!",stack.getCurrentSize() == 6);
    }

    @Test
    public void testPop() throws Exception {
        assertEquals((Integer)9,stack.pop());
        stack.push(4);
        assertEquals("É esperado o número 4!",(Integer)4,stack.pop());
        assertEquals(2,stack.getCurrentSize());

    }

    @Test(expected = EmptyCollectionException.class)
    public void testPopEmpty() throws Exception{
        stack.disposeAll();
        stack.pop();
    }

    @Test
    public void testMaxCapacity() throws  Exception {
        stack.push(4);
        stack.push(7);
        assertEquals(5,stack.getCurrentSize());
    }

    @Test(expected = FullCollectionException.class)
    public void testMaxCapacityNoResizable() throws  Exception{
        stackLimited.push(10);
    }

    @Test
    public void testGetFirst() throws  Exception{
        assertEquals("Esperado é o 9",(Integer)9,stack.getFirst());
        stack.pop();
        assertEquals("Esperado é o 6",(Integer)6,stack.getFirst());

    }

    @Test(expected = EmptyCollectionException.class)
    public void testGetFirstEmpty() throws Exception{
        stack.disposeAll();
        stack.getFirst();
    }


    @Test(expected = EmptyCollectionException.class)
    public void testGetLastEmpty() throws Exception{
        stack.disposeAll();
        stack.getLast();
    }

    @Test
    public void testGetLast() throws  Exception{
        assertEquals("Esperado é o 9",(Integer)3,stack.getLast());
        stack.pop();
        assertEquals("Esperado é o 6",(Integer)3,stack.getLast());

    }
}