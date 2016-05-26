package structs;

import exception.ElementNotFoundException;
import exception.EmptyCollectionException;
import exception.NullObjectException;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.*;

/**
 * Created by Felipe on 23/05/2016.
 */
public class LinkedListTest {

    @Test
    public void testInsert() throws Exception {
        LinkedList<Integer> list = new LinkedList();
        //tentar inserir um objeto nulo
        try{
            list.insert(null);
            assertTrue("Não deveria ser permitido inserir um objeto nulo",false);
        }catch(NullObjectException e){}

        list.insert(3);
        list.insert(6);
        list.insert(9);
    }

    @Test
    public void testRemove1() throws Exception {
        LinkedList<Integer> list = new LinkedList();
        //Tentar Remover passando como argumento um objeto nulo.
        try{
            list.remove(null);
            assertTrue("Não é permitido passar um objeto nulo como argumento!",false);
        }catch(NullObjectException e){}

        //Tentar Remover um item da coleção sendo que ela está vazia.
        try{
            list.remove(3);
            assertTrue("Não é possível remover um objeto sendo que a lista está vazia!",false);
        }catch (EmptyCollectionException e){}

        list.insert(3);
        try{
            list.remove(2);
            assertTrue("A lista possui somente um elemento, porém, foi passado " +
                    "um objeto com key diferente",false);
        }catch (ElementNotFoundException e){}

        //Removendo um objeto com a lista de tamanho 1.
        assertEquals("Objeto diferente do esperado!",(Integer)3,list.remove(3));
        assertTrue("A lista devia estar vazia!",list.isEmpty());

        list.insert(3);
        list.insert(6);
        list.insert(9);

        //Tentar remover buscando por um elemento que não existe.
        try{
            list.remove(4);
            assertTrue(false);
        }catch (ElementNotFoundException e){}


        //Remover todos os elementos.
        list.remove(3);
        list.remove(6);
        list.remove(9);
        assertTrue("A coleção deveria estar vazia!",list.isEmpty());
    }

    @Test
    public void testRemove2() throws Exception {
        LinkedList<Integer> list = new LinkedList();
        //Tentar remover com a coleção vazia.
        try{
            list.remove();
            assertTrue("Não é possível remover um elemento de uma coleção vazia!",false);
        }catch(EmptyCollectionException e) {}
        list.insert(3);
        assertEquals("Elemento removido deveria ser o 3!",(Integer)3,list.remove());
    }

}