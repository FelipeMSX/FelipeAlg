package search;


public class BinarySearch<E extends Comparable<E>> {

	public E binarySearch(E[] array, E item){
		int left = 0;
		int right = array.length -1;
		int mid = 0;

		while( left <= right){
			mid = midvalue(left, right);
			// Se o valor do item a ser achado for maior ao do array é necessário avançar para direita.
			// arrayItem < item
			if (array[mid].compareTo(item) <= -1)
				left = mid + 1;
			else
			// Se o valor do item a ser achado for menor ao do array é necessário avançar para esquerda.
			// arrayItem > item
			if( array[mid].compareTo(item) >= 1)
				right = mid -1;
			else
				return array[mid];
		}
		return null;
	}

	public E binarySearchRecursive(E[] array, E item){
		return binaryAuxiliator(0, array.length-1, item, array);
	}
	
	private E binaryAuxiliator(int left, int right, E item, E array[]){
		if (left > right)
			return null;
		else {
			// calculate midpoint to cut set in half
			int mid = midvalue(left, right);
			// arrayItem < item
			if (array[mid].compareTo(item) <= -1)
				return binaryAuxiliator(mid + 1, right, item, array);
			else
			// arrayItem > item
			if (array[mid].compareTo(item) >= 1)
				return binaryAuxiliator(left, mid - 1, item, array);
			else
				return array[mid];
		}
	}
	private int midvalue(int left, int right)
	{
		return left +(right - left)/2;
	}
}
