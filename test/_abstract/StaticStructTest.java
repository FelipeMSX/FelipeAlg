package _abstract;

import exception.ElementNotFoundException;
import exception.EmptyCollectionException;
import exception.FullCollectionException;
import org.junit.Test;
import structs.StaticQueue;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Felipe on 25/05/2016.
 */
public class StaticStructTest {
    @Test
    public void testIsEmpty() throws Exception {
        StaticQueue<Integer> queue = new StaticQueue(10);
        assertTrue("A coleção deveria estar vazia!",queue.isEmpty());
    }

    @Test
    public void testGetFirst() throws Exception {
        StaticQueue<Integer> queue = new StaticQueue();
        try{
            queue.getFirst();
            assertTrue("Não é possível obter um elemento de uma coleção vazia",false);
        }catch(EmptyCollectionException e){}

        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertEquals("É esperado o elemento 1!",(Integer)1,queue.getFirst());
    }

    @Test
    public void testGetLast() throws Exception {
        StaticQueue<Integer> queue = new StaticQueue();
        try{
            queue.getLast();
            assertTrue("Não é possível obter um elemento de uma coleção vazia",false);
        }catch(EmptyCollectionException e){}

        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertEquals("É esperado o elemento 3!",(Integer)3,queue.getLast());

    }

    @Test
    public void testGetCurrentSize() throws Exception {
        StaticQueue<Integer> queue = new StaticQueue();
        try{
            queue.getFirst();
            assertTrue("Não é possível obter um elemento de uma coleção vazia",false);
        }catch(EmptyCollectionException e){}

        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.pop();
        queue.pop();
        assertEquals("Tamanho esperado é 3!",(Integer)3,queue.getFirst());

    }

    @Test
    public void testDisposeAll() throws Exception {
        StaticQueue<Integer> queue = new StaticQueue();

        queue.push(1);
        queue.push(2);
        queue.disposeAll();
        queue.push(4);
        assertEquals("Tamanho esperado é 4!",(Integer)4,queue.getFirst());

    }

    @Test
    public void testRetrieve() throws Exception {
        StaticQueue<Integer> queue = new StaticQueue();
        try{
            queue.retrieve(4);
            assertTrue("Impossível obter um elemento de uma coleção vazia!",false);
        }catch(EmptyCollectionException e){}

        queue.push(1);
        queue.push(2);
        queue.push(4);
        assertEquals("Tamanho esperado é 4!",(Integer)4,queue.retrieve(4));

        //Procura por um elemento que não existe na coleção.
        try{
            queue.retrieve(8);
            assertTrue("Elemento não existe na coleção!",false);
        }catch(ElementNotFoundException e){}
    }

    @Test
    public void testIsFull() throws Exception {
        StaticQueue<Integer> queue = new StaticQueue(2);

        queue.push(1);
        queue.push(2);
        assertTrue("Deveria estar cheia a coleção!",queue.isFull());
    }

    @Test
    public void testDoubleCapacity() throws Exception {
        //Testando para uma coleção não dinamicamente crescente.
        {
            StaticQueue<Integer> queue = new StaticQueue(2);
            queue.setResizable(false);
            queue.setAutoRezise(10);
            queue.push(1);
            queue.push(2);
            try{
                queue.push(3);
                assertTrue("Não deveria permitir inserir um novo item",false);
            }catch(FullCollectionException e){}

            assertTrue("Deveria estar cheia a coleção!", queue.getCurrentSize() == 2);
        }
        //Testando
        {
            StaticQueue<Integer> queue = new StaticQueue(2);
            queue.setResizable(true);
            queue.isResizable();
            queue.setAutoRezise(10);
            queue.push(1);
            queue.push(2);
            queue.push(3);
            assertTrue("Tamanho da coleção deveria ser 3",queue.getCurrentSize() == 3);
        }
    }

}