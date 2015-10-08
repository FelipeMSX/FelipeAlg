package com.company;

import structs.LinkedQueue;
import util.ObjectTeste;

/**
 * Created by Felipe on 09/08/2015.
 */
public class AlgolTest {
    public static void main(String args[])
    {
        LinkedQueue<ObjectTeste> sq = new LinkedQueue();
        ObjectTeste o1 = new ObjectTeste(123,"Felipe");
        sq.insert(o1);

        ObjectTeste o2 = new ObjectTeste(4,"Alberto");
        sq.insert(o2);
        System.out.println(sq.remove());
        System.out.println(sq.remove());
        System.out.println(sq.remove());

    }
}

