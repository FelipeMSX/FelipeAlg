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
public class StaticQueueTest {


    private StaticQueue<Integer> queue;
    private StaticQueue<Integer> queueLimited;

    @Before
    public void initialize() throws Exception {
        queueLimited = new StaticQueue(3,false);
        queueLimited.push(3);
        queueLimited.push(6);
        queueLimited.push(9);
        queue = new StaticQueue(2);
        queue.push(1);
        queue.push(2);
    }

    @Test(expected = FullCollectionException.class)
    public void testPushFullCollection() throws Exception {
        queueLimited.push(new Integer(2));
    }

    @Test(expected = NullObjectException.class)
    public void testPushNullObject() throws Exception {
        //Testando a fila para inserção de valores nulo
        queue.push(null);
    }

    @Test
    public void testPush() throws Exception {
        queue.push(3);
        assertTrue(!queue.isFull());
    }

    @Test(expected = EmptyCollectionException.class)
    public void testPopEmptyCollection() throws Exception {
        queue.disposeAll();
        queue.pop();
        queue.push(null);
    }

    @Test
    public void testPop() throws Exception {
        assertEquals(new Integer(3),queueLimited.pop());
        assertEquals(new Integer(6),queueLimited.pop());
        assertEquals(new Integer(9),queueLimited.pop());
        assertTrue(queueLimited.isEmpty());
    }

    @Test
    public void testGetFirst() throws  Exception{
        assertEquals(new Integer(3),queueLimited.getFirst());
        queueLimited.pop();
        queueLimited.push(new Integer(12));
        assertEquals(new Integer(6),queueLimited.getFirst());
    }

    @Test
    public void testGetLast() throws  Exception{
        assertEquals(new Integer(2),queue.getLast());
        queue.push(new Integer(20));
        assertEquals(new Integer(20),queue.getLast());
    }
}