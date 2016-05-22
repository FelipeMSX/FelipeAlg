package sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Felipe on 16/05/2016.
 */
public class QuickSortTest {

    @Test(timeout = 1000)
    public void testSort() throws Exception {
        //Teste para ordenzação de string
        {
            QuickSort<String> quick = new QuickSort<>();
            String[] arrayString = {"B", "D", "R", "A", "Z", "N", "I", "C"};
            String[] arrayExpected = {"A", "B", "C", "D", "I", "N", "R", "Z"};
            quick.sort(arrayString);
            assertArrayEquals(arrayExpected, arrayString);
        }

        //Ordenação de inteiros.
        {
            Integer[] arrayInteger = {8, 1, 10, -19, 7, 1567, 102};
            Integer[] arrayExpected = {-19, 1, 7, 8, 10, 102, 1567};
            QuickSort<Integer> quick = new QuickSort<>();
            quick.sort(arrayInteger);
            assertArrayEquals(arrayExpected, arrayInteger);
        }

        //Ordenação de inteiros com alguns valores iguais.
        {
            Integer[] arrayInteger  = {8, 1, 10, -19, 7, 1567, 102, 8,1};
            Integer[] arrayExpected = {-19, 1,1, 7, 8, 8, 10, 102, 1567};
            QuickSort<Integer> quick = new QuickSort<>();
            quick.sort(arrayInteger);
            assertArrayEquals(arrayExpected, arrayInteger);
        }

    }
}