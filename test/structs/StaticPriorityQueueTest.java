package structs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Felipe on 22/05/2016.
 */
public class StaticPriorityQueueTest {

    @Test
    public void testPush() throws Exception {
        {
            StaticPriorityQueue<Integer> stack = new StaticPriorityQueue<Integer>(10);
            stack.push(new Integer(1));
            stack.push(new Integer(3));
            stack.push(new Integer(2));
            stack.push(new Integer(2));

            assertEquals(new Integer(3), stack.getFirst());
            assertEquals(new Integer(1), stack.getLast());
            stack.disposeAll();
        }

        {
            StaticPriorityQueue<Integer> stack = new StaticPriorityQueue<Integer>(2,true);
            stack.push(new Integer(1));
            stack.push(new Integer(3));
            stack.push(new Integer(2));
            stack.push(new Integer(2));

            assertEquals(new Integer(3), stack.getFirst());
            assertEquals(null, stack.retrieve(new Integer(66)));
            assertEquals(new Integer(1), stack.getLast());
            stack.disposeAll();
        }
    }
}