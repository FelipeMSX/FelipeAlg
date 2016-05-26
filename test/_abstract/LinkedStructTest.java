package _abstract;

import exception.ElementNotFoundException;
import exception.EmptyCollectionException;
import org.junit.Test;
import structs.LinkedList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Izabela on 25/05/2016.
 */
public class LinkedStructTest {
    @Test
    public void testGetFirst() throws Exception {
        LinkedList<Integer> list = new LinkedList();
        //Tentar obter o primeiro elemento com a coleção vazia.
        try{
            list.getFirst();
            assertTrue("A coleção está vazia!",false);
        }catch (EmptyCollectionException e){}

        list.insert(3);
        list.insert(6);
        list.insert(9);
        assertEquals("Primiro elemento deveria ser o 9 !",(Integer)9,list.getFirst());
    }

    @Test
    public void testGetLast() throws Exception {
        LinkedList<Integer> list = new LinkedList();
        //Tentar obter o primeiro elemento com a coleção vazia.
        try{
            list.getLast();
            assertTrue("A coleção está vazia!",false);
        }catch (EmptyCollectionException e){}

        list.insert(3);
        list.insert(6);
        list.insert(9);
        assertEquals("Último elemento deveria ser o 3 !",(Integer)3,list.getLast());

    }

    @Test
    public void testGetCurrentSize() throws Exception {
        LinkedList<Integer> list = new LinkedList();

        assertTrue("O tamanho da coleção deveria se 0!",list.getCurrentSize() == 0);
        list.insert(3);
        list.insert(6);
        list.insert(9);

        assertTrue("O tamanho da coleção deveria ser 3!",list.getCurrentSize() == 3);

        list.remove();
        list.remove();
        list.remove();
        assertTrue("O tamanho da coleção deveria se 0!",list.getCurrentSize() == 0);
    }

    @Test
    public void testDisposeAll() throws Exception {
        LinkedList<Integer> list = new LinkedList();

        list.insert(3);
        list.insert(6);
        list.insert(9);
        list.disposeAll();

        assertTrue("O tamanho da coleção deveria se 0!",list.getCurrentSize() == 0);

    }

    @Test
    public void testRetrieve() throws Exception {
        LinkedList<Integer> list = new LinkedList();
        //Tentar obter um elemento com a coleção vazia!
        try{
            list.retrieve(3);
            assertTrue("Não é possível obter um elemento de uma coleção vazia",false);
        }catch(EmptyCollectionException e){}

        list.insert(3);
        list.insert(6);
        list.insert(9);

        //Tentar obter um elemento de uma coleção com elementos sendo ela ossu
        try{
            list.retrieve(4);
            assertTrue("Elemento não existe na coleção!",false);
        }catch(ElementNotFoundException e){}

        assertEquals("Elemento esperado deveria ser o 3!",(Integer)3,list.retrieve(3));
    }

    @Test
    public void testIterator() throws Exception {
        LinkedList<Integer> list = new LinkedList();
        //Teste com iterator com a lista vazia.
        for(Integer integer: list){
            assertTrue("Não deve entrar no foreach já que a lista está vazia",false);
        }

        //Teste com o iterator com a lista com alguns elementos.
        list.insert(3);
        list.insert(6);
        list.insert(9);
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
}