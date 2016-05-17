package search;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Felipe on 16/05/2016.
 */
public class BinarySearchTest {

    @Test
    public void testBinarySearch() throws Exception {
        BinarySearch<Integer> bs = new BinarySearch<>();
        Integer[] array =  {1,2,3,4,5,6,7,8,9,10};

        //Procurando um valor no extremo a esquerda.
        Integer value = bs.binarySearch(array,1);
        assertEquals(1,value,0);

        //Procurando um valor que existe na direita.
        value = bs.binarySearch(array,10);
        assertEquals( 10, value, 0);

        //Procurando um valor que n�o existe
        value = bs.binarySearch(array,100);
        assertNull("O valor n�o deve ser nulo",value);
    }
}