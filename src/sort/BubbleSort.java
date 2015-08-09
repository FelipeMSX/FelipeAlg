package sort;

public class BubbleSort<E extends Comparable<E>> {

	/* 
	 * Aceita Valores Iguais;
	 * Caso não seja efetuada nenhuma troca, significa que deve parar.
	 */
	private E[] sortArray(E[] array){	
		for (int i = 0; i < array.length; i++){
			boolean exit = true;
			for (int j= 0;j < (array.length - (i+1)) ; j++){	
				// Verifica se o elemento a frente é maior, caso sim, é preciso trocar com ele;
				if (array[j].compareTo( array[j+1])  >= 1){
					E aux = array[j];
					array[j] = array[j+1];
					array[j+1] = aux;
					exit = false;
				}	
			}
			// Se não exister nenhuma troca então já está ordenado!!!.
			if (exit)
				break;
		}
		return array;
	}
	
	public E[] sort(E[] array){
			return sortArray(array);		
	}
}
