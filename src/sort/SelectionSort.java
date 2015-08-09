package sort;

public class SelectionSort<E extends Comparable<E>> {

	public E[] sort(E[] array){
		E elementFlopy;
		for(int i = 0; i < array.length; i++ ){
			for(int j = i+1; j < array.length; j++){
				if(array[i].compareTo( array[j]) >= 1){
					elementFlopy = array[i];
					array[i] = array[j];
					array[j] = elementFlopy;
				}
			}
		}
		return array;
	}
}
