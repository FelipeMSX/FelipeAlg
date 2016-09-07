package sort;


import exception.NullObjectException;

import java.util.Comparator;

/*
	Descrição:
		- Aceita valores iguais.
        - O pivô é o elemento do meio.
        - Ordem Crescente.
*/
public class QuickSort<E> {
	private final Comparator<E> comparator;

	QuickSort(Comparator<E> comparator){
		this.comparator = comparator;
		if(comparator == null)
			throw new NullObjectException();
	}

	public  void sort(E input[]) {
		quicksort(input,0, input.length-1);
	}

	/*
	 * Posição do pivo é sempra o último elemento
	 */
	private void quicksort(E V[], int inicio, int fim) {
		if(inicio < fim) {
			int pivot = partition(V, inicio, fim);
			quicksort(V, inicio, pivot - 1);
			quicksort(V, pivot + 1, fim);
		}
	}
	
	private int partition(E input[], int init, int end) {
		int positionPivot = definePivot(init,end);
		//Ao definir o pivô é preciso colocá-lo no fim.
		swap(input,end,positionPivot);
        positionPivot    = end;
		int left        = init;
		int right       = end -1;

		// Rodar enquato left&right não se cruzarem no vetor.
        // Varra com left da esquerda para direita o vetor até encontrar o elemento maior que o pivô.
        // Varra com right da direita para esquerda o vetor até encontrar o elemento menor que o pivô.
		while(left <= right) {

			while((left <= right) && comparator.compare(input[left],input[positionPivot]) <= 0 )
				left++;

			while((left <= right) && comparator.compare(input[right],input[positionPivot]) > 0  )
				right--;

			if(left < right) {
				swap(input,left,right);
			}else {
				swap(input,left,positionPivot);
				positionPivot = left;
			}
		}
		return positionPivot;
	}


	private void swap(E[] input ,int X, int Y){
		E temp = input[X];
		input[X] = input[Y];
		input[Y] = temp;
	}

	private int definePivot(int init, int end){
        return init +(end - init)/2;
	}
}