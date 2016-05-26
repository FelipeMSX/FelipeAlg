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
            assertTrue("N�o deveria ser permitido inserir um objeto nulo",false);
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
            assertTrue("N�o � permitido passar um objeto nulo como argumento!",false);
        }catch(NullObjectException e){}

        //Tentar Remover um item da cole��o sendo que ela est� vazia.
        try{
            list.remove(3);
            assertTrue("N�o � poss�vel remover um objeto sendo que a lista est� vazia!",false);
        }catch (EmptyCollectionException e){}

        list.insert(3);
        try{
            list.remove(2);
            assertTrue("A lista possui somente um elemento, por�m, foi passado " +
                    "um objeto com key diferente",false);
        }catch (ElementNotFoundException e){}

        //Removendo um objeto com a lista de tamanho 1.
        assertEquals("Objeto diferente do esperado!",(Integer)3,list.remove(3));
        assertTrue("A lista devia estar vazia!",list.isEmpty());

        list.insert(3);
        list.insert(6);
        list.insert(9);

        //Tentar remover buscando por um elemento que n�o existe.
        try{
            list.remove(4);
            assertTrue(false);
        }catch (ElementNotFoundException e){}


        //Remover todos os elementos.
        list.remove(3);
        list.remove(6);
        list.remove(9);
        assertTrue("A cole��o deveria estar vazia!",list.isEmpty());
    }

    @Test
    public void testRemove2() throws Exception {
        LinkedList<Integer> list = new LinkedList();
        //Tentar remover com a cole��o vazia.
        try{
            list.remove();
            assertTrue("N�o � poss�vel remover um elemento de uma cole��o vazia!",false);
        }catch(EmptyCollectionException e) {}
        list.insert(3);
        assertEquals("Elemento removido deveria ser o 3!",(Integer)3,list.remove());
    }

}