package com.company;

import structs.StaticQueue;
import util.ObjectTeste;

/**
 * Created by Felipe on 09/08/2015.
 */
public class AlgolTest {
    public static void main(String args[])
    {
        StaticQueue<ObjectTeste> sq = new StaticQueue();
        ObjectTeste o1 = new ObjectTeste(123,"Felipe");
        sq.insert(o1);

        ObjectTeste o2 = new ObjectTeste(4,"Alberto");
        sq.insert(o2);

    }
}

