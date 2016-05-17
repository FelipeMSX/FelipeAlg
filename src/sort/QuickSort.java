package sort;

public class QuickSort<E extends Comparable<E>> {
	/*
	 * Ordem crescente, Não aceita valores iguais
	 *
	 */

	public  void sort(E input[]) {
		quicksort(input,0, input.length-1);
	}

	/*
	 * Posição do pivo é sempra o último elemento
	 */
	private void quicksort(E V[], int inicio, int fim) {
		if(inicio < fim) {
			int pivo = partition(V, inicio, fim);
			quicksort(V, inicio, pivo - 1);
			quicksort(V, pivo + 1, fim);
		}
	}
	
	private int partition(E input[], int inicio, int fim) {
		int positionPivo = fim; // pivo será sempre o ultimo elemento;
		int left = 0; int right = fim -1;
		
		// significa que i j acabaram de se cruzar
		while(left < right) {

			//input[left] < input[positionPivo]
			while(input[left].compareTo(input[positionPivo]) < 0)
				left++;

			//input[right] > input[positionPivo]
			while(input[right].compareTo(input[positionPivo]) > 0)
				right++;
			
			if(left < right) {
				E temp = input[left];
				input[left] = input[right];
				input[right] = temp;
			}else {
				E temp = input[left];
				input[left] = input[positionPivo];
				input[positionPivo] = temp;
				positionPivo = left;
			}
		}
		return positionPivo;
	}
}