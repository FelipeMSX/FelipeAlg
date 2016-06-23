package structs;

import exception.ElementNotFoundException;
import exception.EmptyCollectionException;
import exception.EqualsElementException;
import exception.NullObjectException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.ObjectTest;

/**
 * Created by Felipe on 03/06/2016.
 */
public class BinaryTreeTest {

    private BinaryTree<ObjectTest> biTree;

    @Before
    public void initialize() throws Exception {
        biTree = new BinaryTree();
        ObjectTest o1 = new ObjectTest(10, "N� Raiz");
        ObjectTest o2 = new ObjectTest(5, "Esquerdo da raiz.");
        ObjectTest o3 = new ObjectTest(20, "Direito da raiz");
        ObjectTest o4 = new ObjectTest(2, "Es.Esquerda da raiz");
        ObjectTest o5 = new ObjectTest(8, "Es.Direita da raiz");
        ObjectTest o6 = new ObjectTest(15, "Di.esquerda da raiz");

        /*
          Representa��o da �rvore bin�ria:
                        10
                       /  \
                      /    \
                     5      20
                    / \     /
                   2   8   15

         */
        biTree.insert(o1);
        biTree.insert(o2);
        biTree.insert(o3);
        biTree.insert(o4);
        biTree.insert(o5);
        biTree.insert(o6);
    }

    @Test(expected = NullObjectException.class)
    public void testInsertNullObject(){
        biTree.insert(null);
    }

    @Test(expected = EqualsElementException.class)
    public void testInsertEqualsObject(){
        biTree = new BinaryTree<>();
        ObjectTest o1 = new ObjectTest(10, "N� Raiz");
        ObjectTest o2 = new ObjectTest(10, "raiz repetido.");
        biTree.insert(o1);
        biTree.insert(o2);
    }

    //Remover n� raiz
    //Remover com um elemento s� na �rvore.
    @Test
    public void testRemoveOnlyRoot() throws  Exception{
        /*
          Representa��o da �rvore bin�ria:
                        10
                       /  \
                      /    \
                     5      20
                    / \     /
                   2   8   15
         */

        ObjectTest o1 = new ObjectTest(10, "N� Raiz");
        biTree.remove(o1);
        /*
                        15
                       /  \
                      /    \
                     5      20
                    / \
                   2   8
         */
        o1 = new ObjectTest(15, "N� Raiz");
        biTree.remove(o1);
                /*
                        20
                       /
                      /
                     5
                    / \
                   2   8
         */
        o1 = new ObjectTest(20, "N� Raiz");
        biTree.remove(o1);
        /*
                        8
                       /
                      /
                     5
                    /
                   2
         */
        o1 = new ObjectTest(8, "N� Raiz");
        biTree.remove(o1);
        o1 = new ObjectTest(5, "N� Raiz");
        biTree.remove(o1);
        o1 = new ObjectTest(2, "N� Raiz");
        biTree.remove(o1);
    }

    //Remover um n� com um filho a esquerda
    //Remover um n� com um filho na esquerda sendo o n� a ser removido o raiz.
    @Test
    public void testRemoveLeftSon() throws  Exception{
        ObjectTest o1 = new ObjectTest(-100, "N� Raiz");
        biTree.insert(o1);
        o1 = new ObjectTest(-50, "N� Raiz");
        biTree.insert(o1);
        o1 = new ObjectTest(-60, "N� Raiz");
        biTree.insert(o1);
        o1 = new ObjectTest(-200, "N� Raiz");
        biTree.insert(o1);
       /*
          Representa��o da �rvore bin�ria:
                        10
                       /  \
                      /    \
                     5      20
                    / \     /
                   2   8   15
                  /
                -100
               /     \
           -200      -50
                    /
                  -60
         */

        ObjectTest o2 = new ObjectTest(2, "N� Raiz");
        biTree.remove(o2);
               /*
          Representa��o da �rvore bin�ria:
                        10
                       /  \
                      /    \
                     5      20
                    / \     /
                  -50   8   15
                  /
                -100
               /     \
           -200      -60
         */
        o2 = new ObjectTest(-50, "N� Raiz");
        biTree.remove(o2);
        o2 = new ObjectTest(-60, "N� Raiz");
        biTree.remove(o2);
        o2 = new ObjectTest(-100, "N� Raiz");
        biTree.remove(o2);
        o2 = new ObjectTest(-200, "N� Raiz");
        biTree.remove(o2);

    }


    @Test(expected = EmptyCollectionException.class)
    public void testRemoveEmptyCollection() throws  Exception{
        biTree = new BinaryTree<>();
        biTree.remove(new ObjectTest(5, "XXXX"));
    }

    @Test(expected = NullObjectException.class)
    public void testRemoveNullObject() throws  Exception{
        biTree.remove(null);
    }

    @Test(expected = ElementNotFoundException.class)
    public void testRemoveElementNotFound() throws  Exception{
        biTree.remove(new ObjectTest(999, "Not found."));
    }

    //Remover um n� com 2 filhos
    @Test
    public void testRemoveDualSon() throws  Exception{
        ObjectTest o2 = new ObjectTest(5, "XXXX");
        biTree.remove(o2);
        o2 = new ObjectTest(8, "XXXX");
        biTree.remove(o2);
        o2 = new ObjectTest(2, "XXXX");
        biTree.remove(o2);
        /*
          Representa��o da �rvore bin�ria:
                        10
                       /  \
                      /    \
                     5      20
                    / \     /
                   2   8   15
         */
    }

    @Test
    public void testRetrieve() throws Exception{
        /*
          Representa��o da �rvore bin�ria:
                        10
                       /  \
                      /    \
                     5      20
                    / \     /
                   2   8   15
         */
        ObjectTest o2 = new ObjectTest(5, "XXXX");
        Assert.assertNotNull(biTree.retrieve(o2));

        //Recuperar no n� folha.
        o2 = new ObjectTest(2, "XXXX");
        Assert.assertNotNull(biTree.retrieve(o2));
    }

    @Test(expected = EmptyCollectionException.class)
    public void testRetrieveEmptyCollection() throws  Exception{
        biTree = new BinaryTree<>();
        biTree.retrieve(new ObjectTest(5, "XXXX"));
    }

    @Test(expected = NullObjectException.class)
    public void testRetrieveNullObject() throws  Exception{
        biTree.retrieve(null);
    }

    @Test(expected = ElementNotFoundException.class)
    public void testRetrieveElementNotFound() throws  Exception{
        biTree.retrieve(new ObjectTest(999, "Not found."));
    }

}