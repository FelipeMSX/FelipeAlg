package sort;



/**
 * Created by Felipe on 11/11/2015.
 */


public class HeapSort<E extends Comparable<E>> {


    public void sort(E input[]){
        heapsort(input,input.length);
    }

    private void heapsort(E input[], int n){
        buildMinHeap(input, n);
        int i;
        for(i = n - 1; i > 0; i--){
            swapItem(input,0,i);
            minHeapify(input, 0, i);
        }
    }

    private void minHeapify(E input[], int i, int n) {
        int P = i;
        int E = getLeft(i);
        int D = getRight(i);

        if (E < n && input[E].compareTo(input[P]) >= 0 )
            P = E;

        if (D < n && input[D].compareTo(input[P]) >= 0 )
            P = D;

        if (P != i) {
            swapItem(input, i, P);
            minHeapify(input, P, n);
        }
    }

    private void buildMinHeap(E input[], int n) {
        for (int i = n/2 - 1; i >= 0; i--)
            minHeapify(input, i, n);
    }

    private void swapItem(E input[], int positionX, int positionY) {
        E temp = input[positionX];
        input[positionX] = input[positionY];
        input[positionY] = temp;
    }

    private int getFather(int i) {
        return (i-1)/2;
    }

    private int getLeft(int i) {
        return 2*i+1;
    }

    private int getRight(int i) {
        return 2*i+2;
    }

}
