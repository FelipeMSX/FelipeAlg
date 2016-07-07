package _abstract;

import exception.ElementNotFoundException;
import exception.EmptyCollectionException;
import exception.FullCollectionException;
import org.junit.Before;
import org.junit.Test;
import structs.StaticQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Teste criado para a classe abstrata StaticStruct, para isso, utilizamos uma clase concreta para testar os m�todos.
 */
public class StaticStructTest {

    private StaticQueue<Integer> queue;
    @Before
    public void initialize() throws Exception {
        queue = new StaticQueue<>();
        queue.push(3);
        queue.push(6);
        queue.push(9);
    }

    @Test
    public void testIsEmpty() throws Exception {
        StaticQueue<Integer> queue = new StaticQueue<>(10);
        assertTrue("A cole��o deveria estar vazia!",queue.isEmpty());
    }

    @Test
    public void testGetFirst() throws Exception {
        assertEquals("� esperado o elemento 3!",(Integer)3,queue.getFirst());
    }

    @Test(expected = EmptyCollectionException.class)
    public void testGetFirstEmpty() throws  Exception{
        queue.disposeAll();
        queue.getFirst();
    }

    @Test
    public void testGetLast() throws Exception {
        assertEquals("� esperado o elemento 9!",(Integer)9,queue.getLast());
    }

    @Test(expected = EmptyCollectionException.class)
    public void testGetLastEmpty() throws  Exception{
        queue.disposeAll();
        queue.getLast();
    }

    @Test
    public void testGetCurrentSize() throws Exception {
        queue.pop();
        queue.pop();
        assertEquals("Tamanho esperado � 1!",1,queue.getCurrentSize());
    }

    @Test
    public void testDisposeAll() throws Exception {
        queue.disposeAll();
        queue.push(4);
        assertEquals("Tamanho esperado � 4!",(Integer)4,queue.getFirst());
    }

    @Test
    public void testRetrieve() throws Exception {
        assertEquals("Item esperado  � 3!",(Integer)3,queue.retrieve(3));
    }

    @Test(expected = EmptyCollectionException.class)
    public void testRetrieveEmpty() throws  Exception{
        queue.disposeAll();
        queue.retrieve(4);
    }

    @Test(expected = ElementNotFoundException.class)
    public void testRetrieveElementNotFound() throws  Exception{
        //Procura por um elemento que n�o existe na cole��o.
        queue.retrieve(8);
    }

    @Test
    public void testIsFull() throws Exception {
        StaticQueue<Integer> queue = new StaticQueue<>(2);
        queue.push(1);
        queue.push(2);
        assertTrue("Deveria estar cheia a cole��o!",queue.isFull());
    }

    @Test
    public void testDoubleCapacity() throws Exception {
            queue.isResizable();
            queue.setAutoRezise(10);
            queue.push(1);
            queue.push(2);
            queue.push(3);
            assertTrue("Tamanho da cole��o deveria ser 3",queue.getCurrentSize() == 6);
    }
    @Test(expected = FullCollectionException.class)
    public void testDoubleCapacityNotResizable() throws Exception {
        //Testando para uma cole��o n�o dinamicamente crescente.
        StaticQueue<Integer> queue = new StaticQueue<>(2,false);
        queue.setAutoRezise(10);
        queue.push(1);
        queue.push(2);
        queue.push(3);
    }

}