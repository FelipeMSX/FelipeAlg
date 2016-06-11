package structs;

import exception.EqualsElementException;
import exception.NullObjectException;
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
        ObjectTest o1 = new ObjectTest(10, "Nó Raiz");
        ObjectTest o2 = new ObjectTest(5, "Esquerdo da raiz.");
        ObjectTest o3 = new ObjectTest(20, "Direito da raiz");
        ObjectTest o4 = new ObjectTest(2, "Es.Esquerda da raiz");
        ObjectTest o5 = new ObjectTest(8, "Es.Direita da raiz");
        ObjectTest o6 = new ObjectTest(15, "Di.esquerda da raiz");

        /*
          Representação da árvore binária:
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
    @Test
    public void testInsert() throws Exception {
    //Inserido valores iguais.
        ObjectTest o1 = new ObjectTest(10, "Nó Raiz Repetido");
        ObjectTest o2 = new ObjectTest(8, "Nó 8 repetido.");
        ObjectTest o3 = new ObjectTest(8, "Nó 8 Repetido segunda vez.");
        biTree.insert(o1);
        biTree.insert(o2);
        biTree.insert(o3);
                /*
          Representação da árvore binária:
                        10,10R
                       /  \
                      /    \
                     5      20
                    / \     /
                   2   8,  15
                        8R,
                          8RR

         */


        //Inserir a esquerda de um nó repetido.
        ObjectTest o4 = new ObjectTest(6, "Es.Di.Es da raiz.");
        biTree.insert(o4);
        /*
          Representação da árvore binária:
                        10,10R
                       /  \
                      /    \
                     5      20
                    / \     /
                   2   8,  15
                      / 8R,
                     6    8RR
         */

    }

    @Test(expected = NullObjectException.class)
    public void testInsertNullObject(){
        biTree.insert(null);
    }

    @Test(expected = EqualsElementException.class)
    public void testInsertEqualsObject(){
        biTree = new BinaryTree<>(false);
        ObjectTest o1 = new ObjectTest(10, "Nó Raiz");
        ObjectTest o2 = new ObjectTest(10, "raiz repetido.");
        biTree.insert(o1);
        biTree.insert(o2);
    }
}