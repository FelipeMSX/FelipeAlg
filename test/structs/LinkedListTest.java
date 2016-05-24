package structs;

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
        LinkedList<Integer> lList = new LinkedList();
        //tentar inserir um objeto nulo
        try{
            lList.insert(null);
            assertTrue("Não deveria ser permitido inserir um objeto nulo",false);
        }catch(NullObjectException e){}
        lList.insert(3);
        lList.insert(6);
        lList.insert(9);
    }

    @Test
    public void testRemove() throws Exception {
        throw new NotImplementedException();
    }

    @Test
    public void testRemove1() throws Exception {
        throw new NotImplementedException();
    }

    @Test
    public void testIsEmpty() throws Exception {
        throw new NotImplementedException();
    }

    @Test
    public void testGetFirst() throws Exception {
        throw new NotImplementedException();
    }

    @Test
    public void testGetLast() throws Exception {
        throw new NotImplementedException();
    }

    @Test
    public void testGetCurrentSize() throws Exception {
        throw new NotImplementedException();
    }

    @Test
    public void testDisposeAll() throws Exception {
        throw new NotImplementedException();
    }

    @Test
    public void testRetrieve() throws Exception {
        throw new NotImplementedException();
    }

    @Test
    public void testIterator() throws Exception {
        throw new NotImplementedException();
    }
}