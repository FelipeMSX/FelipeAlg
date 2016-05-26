package structs;

import exception.ElementNotFoundException;
import exception.NullObjectException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Felipe on 22/05/2016.
 */
public class StaticPriorityQueueTest {

    @Test
    public void testPush() throws Exception {
        {
            StaticPriorityQueue<Integer> stack = new StaticPriorityQueue(10);
            stack.push(new Integer(1));
            stack.push(new Integer(3));
            stack.push(new Integer(2));
            stack.push(new Integer(2));

            assertEquals(new Integer(3), stack.getFirst());
            assertEquals(new Integer(1), stack.getLast());
            stack.disposeAll();
        }

        //Testndo a fila com limite e também inserção com valores nulo.
        {
            StaticPriorityQueue<Integer> stack = new StaticPriorityQueue<Integer>(2,true);
            stack.push(new Integer(1));
            stack.push(new Integer(3));
            stack.push(new Integer(2));
            stack.push(new Integer(2));

            assertEquals(new Integer(3), stack.getFirst());
            assertEquals(new Integer(1), stack.getLast());
            //Testando inserção com objeto nulo.
            try{
                stack.push(null);
                assertTrue("Não é permitido inserção de valor nulo",false);
            }catch(NullObjectException e){}
            stack.disposeAll();
        }
    }
}