package study;


import util.FileLoader;

/**
 * Created by Felipe on 06/07/2016.
 */

/**
 * Conclusão: Nâo vi diferença significativa.
 */
public class Study_ArrayCopy {

    public static void main(String args[]){
        FileLoader file = new FileLoader();
        file.loader("resources\\100000int.txt");
        long[] numbers = file.getDataFile();
        util.Stopwatch stopwatch = new util.Stopwatch();
        int iterations = 1000;

        stopwatch.start();
        for(int idx = 0 ; idx < iterations; ++idx) {
            numbers.clone();
        }
        stopwatch.stop();
        System.out.println("Using clone: " + stopwatch.calculateElapsedTimeInMilliSeconds());


        stopwatch.start();
        for(int idx = 0 ; idx < iterations; ++idx) {
            long [] copy = new long[numbers.length];
            System.arraycopy( numbers, 0, copy, 0, numbers.length );
        }
        stopwatch.stop();
        System.out.println("Using System.arraycopy: " + stopwatch.calculateElapsedTimeInMilliSeconds());


        stopwatch.start();
        for(int iterIdx = 0 ; iterIdx < iterations; ++iterIdx) {
            long [] copy = new long[numbers.length];
            for (int idx = 0; idx < numbers.length; ++idx) {
                copy[idx] = numbers[idx];
            }
        }
        stopwatch.stop();
        System.out.println("Using for loop: " + stopwatch.calculateElapsedTimeInMilliSeconds());
    }

}
