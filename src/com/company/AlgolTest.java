package com.company;

import structs.BinaryTree;
import util.ObjectTeste;

/**
 * Created by Felipe on 09/08/2015.
 */
public class AlgolTest {
    public static void main(String args[])
    {
        ObjectTeste o1 = new ObjectTeste();
        o1.setiD(100);

        ObjectTeste o2 = new ObjectTeste();
        o2.setiD(20);

        ObjectTeste o3 = new ObjectTeste();
        o3.setiD(50);

        ObjectTeste o4 = new ObjectTeste();
        o4.setiD(150);

        ObjectTeste o5 = new ObjectTeste();
        o5.setiD(125);

        ObjectTeste o6 = new ObjectTeste();
        o6.setiD(10);
        ObjectTeste o7 = new ObjectTeste();
        o7.setiD(25);
        ObjectTeste o8 = new ObjectTeste();
        o8.setiD(75);

        ObjectTeste o9 = new ObjectTeste();
        o9.setiD(250);


        BinaryTree<ObjectTeste> tree = new BinaryTree<ObjectTeste>();
        tree.insert(o1);
        tree.insert(o2);
        tree.insert(o3);
        tree.insert(o4);
        tree.insert(o5);
        tree.insert(o6);
        tree.insert(o7);
        tree.insert(o8);
        tree.insert(o9);

       // tree.printList();

        tree.remove(o1);
        tree.remove(o9);

        tree.remove(o2);
        tree.remove(o5);
        tree.remove(o3);
        tree.remove(o7);
        tree.remove(o6);
        tree.remove(o4);
        tree.printList();
    }
}

