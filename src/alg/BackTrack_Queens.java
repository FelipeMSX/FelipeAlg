package alg;

import structs.StaticStack;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by felipemsx on 06/09/2016.
 */
public class BackTrack_Queens {
    private final int queens;
    private final int columns;
    private final int lines;
    private boolean [][] environment;

     BackTrack_Queens(int queens,int columns, int lines){
         this.queens    = queens;
         this.columns   = columns;
         this.lines     = lines;
         environment    = new boolean[lines][columns];
     }


    public void findSolution(){

    }


}

