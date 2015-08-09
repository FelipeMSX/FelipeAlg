package sort;

public class BubbleSort<E extends Comparable<E>> {

	/* 
	 * Aceita Valores Iguais;
	 * Caso n�o seja efetuada nenhuma troca, significa que deve parar.
	 */
	private E[] sortArray(E[] array){	
		for (int i = 0; i < array.length; i++){
			boolean exit = true;
			for (int j= 0;j < (array.length - (i+1)) ; j++){	
				// Verifica se o elemento a frente � maior, caso sim, � preciso trocar com ele;
				if (array[j].compareTo( array[j+1])  >= 1){
					E aux = array[j];
					array[j] = array[j+1];
					array[j+1] = aux;
					exit = false;
				}	
			}
			// Se n�o exister nenhuma troca ent�o j� est� ordenado!!!.
			if (exit)
				break;
		}
		return array;
	}
	
	public E[] sort(E[] array){
			return sortArray(array);		
	}
}
