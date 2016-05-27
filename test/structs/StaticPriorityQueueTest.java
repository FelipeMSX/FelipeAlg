package structs;

import exception.FullCollectionException;
import exception.NullObjectException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Classe de testes para a StaticPriorityQueue
 */
public class StaticPriorityQueueTest {

    private StaticPriorityQueue<Integer> queueLimited;
    private StaticPriorityQueue<Integer> queue;
    @Before
    public void initialize() throws Exception {
        queueLimited = new StaticPriorityQueue(3,false);
        queueLimited.push(3);
        queueLimited.push(6);
        queueLimited.push(9);
        queue = new StaticPriorityQueue(2);
        queue.push(1);
        queue.push(2);
    }

    @Test
    public void testPush() throws Exception {
        queue.push(7);
        queue.push(3);
        assertTrue("Tamanho atual deve ser 3!",queue.getCurrentSize() == 4);
    }

    @Test(expected = NullObjectException.class)
    public void testPushNullObject() {
       queueLimited.push(null);
    }

    @Test(expected = FullCollectionException.class)
    public void testPushFullCollection(){
        queueLimited.push(7);
    }
}