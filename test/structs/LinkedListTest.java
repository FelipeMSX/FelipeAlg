package structs;

import exception.ElementNotFoundException;
import exception.EmptyCollectionException;
import exception.NullObjectException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Felipe on 23/05/2016.
 */
public class LinkedListTest {

    private LinkedList<Integer> list;

    @Before
    public void initialize() throws Exception {
        list = new LinkedList<>();
        list.insert(3);
        list.insert(6);
        list.insert(9);
    }
    @Test
    public void testInsert() throws Exception {
        list.insert(1);
        list.insert(2);
        list.insert(3);
        assertTrue("Tamanho atual da lista � 6",list.getCurrentSize() == 6);
    }

    @Test(expected = NullObjectException.class)
    public void testInserNullObject() throws Exception {
        //tentar inserir um objeto nulo
        list.insert(null);
    }

    //Teste para o m�todo remove sem argumentos

    @Test(expected = NullObjectException.class)
    public void testRemoveNoArgumentsNullObject() throws Exception{
        //Tentar Remover passando como argumento um objeto nulo.
        list.remove(null);
    }

    @Test(expected = EmptyCollectionException.class)
    public void testRemoveNoArgumentsEmpty() throws Exception{
        //Tentar Remover um item da cole��o sendo que ela est� vazia.
        list.disposeAll();
        list.remove(3);
    }

    /**
     * Testar para remo��o de um elemento que n�o existe na lista.
     * @throws Exception
     */
    @Test(expected = ElementNotFoundException.class)
    public void testRemoveNoArgumentsElementNotFound() throws Exception{
        list.remove(2);
    }

    @Test
    public void testRemoveNoArguments() throws Exception {
        assertEquals((Integer)6,list.remove(6));
        assertEquals((Integer)9,list.remove(9));
        //Removendo um objeto com a lista de tamanho 1.
        assertEquals("Objeto diferente do esperado!",(Integer)3,list.remove(3));
        assertTrue("A lista devia estar vazia!",list.isEmpty());

        list.insert(3);
        list.insert(6);
        list.insert(9);

        //Remover todos os elementos.
        list.remove(3);
        list.remove(6);
        list.remove(9);
        assertTrue("A cole��o deveria estar vazia!",list.isEmpty());
    }

    @Test
    public void testRemoveWithArguments() throws Exception {
        list.insert(3);
        assertEquals("Elemento removido deveria ser o 3!",(Integer)3,list.remove());
    }

    @Test(expected = EmptyCollectionException.class)
    public void testRemoveWithArgumentsEmpty() throws Exception {
        //Tentar remover com a cole��o vazia.
        list.disposeAll();
        list.remove();
    }
}