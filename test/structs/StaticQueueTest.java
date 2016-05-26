package structs;

import exception.EmptyCollectionException;
import exception.FullCollectionException;
import exception.NullObjectException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Felipe on 22/05/2016.
 */
public class StaticQueueTest {

    @Test
    public void testPush() throws Exception {
        StaticQueue<Integer> stack = new StaticQueue(3,false);
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

        //Testando a fila para inserção de valores nulo
        try{
            stack.push(null);
            assertTrue("Inserão de valor nulo não é permitido!",false);
        }catch(NullObjectException e){}
    }

    @Test
    public void testPop() throws Exception {
        StaticQueue<Integer> stack = new StaticQueue(10);
        stack.push(new Integer(2));
        stack.push(new Integer(4));
        stack.push(new Integer(6));
        assertEquals(new Integer(2),stack.pop());
        assertEquals(new Integer(4),stack.pop());
        assertEquals(new Integer(6),stack.pop());
        assertTrue(stack.isEmpty());

        //testar remoção de objeto com vetor vazio.
        try{
            stack.pop();
            assertTrue(false);
        }catch (EmptyCollectionException e){

        }

    }

    @Test
    public void testGetFirst() throws  Exception{
        StaticQueue<Integer> stack = new StaticQueue(10);
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
        StaticQueue<Integer> stack = new StaticQueue(10);
        stack.push(new Integer(2));
        stack.push(new Integer(4));
        stack.push(new Integer(6));
        assertEquals(new Integer(6),stack.getLast());
        stack.push(new Integer(20));
        assertEquals(new Integer(20),stack.getLast());
    }
}