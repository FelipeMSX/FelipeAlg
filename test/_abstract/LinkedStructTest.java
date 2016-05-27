package _abstract;

import exception.ElementNotFoundException;
import exception.EmptyCollectionException;
import org.junit.Before;
import org.junit.Test;
import structs.LinkedList;
import structs.StaticPriorityQueue;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Classe de testes para LinkedList
 */
public class LinkedStructTest {

    private LinkedList<Integer> list;
    @Before
    public void initialize() throws Exception {
        list = new LinkedList();
        list.insert(3);
        list.insert(6);
        list.insert(9);
    }

    @Test
    public void testGetFirst() throws Exception {
        assertEquals("Primiro elemento deveria ser o 9 !",(Integer)9,list.getFirst());
    }

    @Test(expected = EmptyCollectionException.class)
    public void testGetFirstEmpty() throws Exception{
        //Tentar obter o primeiro elemento com a coleção vazia.
        list.disposeAll();
        list.getFirst();
    }

    @Test
    public void testGetLast() throws Exception {
        assertEquals("Último elemento deveria ser o 3 !",(Integer)3,list.getLast());
    }

    @Test(expected = EmptyCollectionException.class)
    public void testGetLastEmpty()throws Exception{
        //Tentar obter o primeiro elemento com a coleção vazia.
        list.disposeAll();
        list.getLast();
    }

    @Test
    public void testGetCurrentSize() throws Exception {
        assertTrue("O tamanho da coleção deveria ser 3!",list.getCurrentSize() == 3);
        list.remove();
        list.remove();
        list.remove();
        assertTrue("O tamanho da coleção deveria se 0!",list.getCurrentSize() == 0);
    }

    @Test
    public void testDisposeAll() throws Exception {
        list.disposeAll();
        assertTrue("O tamanho da coleção deveria se 0!",list.getCurrentSize() == 0);
    }

    @Test
    public void testRetrieve() throws Exception {
        assertEquals("Elemento esperado deveria ser o 3!",(Integer)3,list.retrieve(3));
     }

    @Test(expected = ElementNotFoundException.class)
    public void testRetrieveElementNotFound() throws Exception{
        //Tentar obter um elemento de uma coleção com elementos sendo ela ossu
        list.retrieve(10);
    }

    @Test(expected = EmptyCollectionException.class)
    public void testRetrieveEmptyCollection() throws Exception{
        //Tentar obter um elemento com a coleção vazia!
        list.disposeAll();
        list.retrieve(3);
    }

    @Test
    public void testIterator() throws Exception {
        //Teste com o iterator com a lista com alguns elementos.
        int i = 0;
        for(Integer integer: list){
            if(i == 0){
                assertEquals("Primeiro elemento da coleção deve ser o 9",(Integer)9,integer);
            }
            else
            if(i == 1){
                assertEquals("Primeiro elemento da coleção deve ser o 6",(Integer)6,integer);
            }
            else
            if(i == 2){
                assertEquals("Primeiro elemento da coleção deve ser o 3",(Integer)3,integer);
            }
            i++;
        }
    }

    @Test
    public void testIteratorEmpty() throws Exception{
        //Teste com iterator com a lista vazia.
        list.disposeAll();
        for(Integer integer: list){
            assertTrue("Não deve entrar no foreach já que a lista está vazia",false);
        }
    }
}