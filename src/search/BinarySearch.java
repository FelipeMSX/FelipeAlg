package search;


public class BinarySearch<E extends Comparable<E>> {

	public E binarySearch(E[] array, E item){
		int left = 0;
		int right = array.length -1;
		int mid = 0;

		while( left <= right){
			mid = midValue(left, right);
			// Se o valor do item a ser achado for maior ao do array � necess�rio avan�ar para direita.
			// arrayItem < item
			if (array[mid].compareTo(item) <= -1)
				left = mid + 1;
			else
			// Se o valor do item a ser achado for menor ao do array � necess�rio avan�ar para esquerda.
			// arrayItem > item
			if( array[mid].compareTo(item) >= 1)
				right = mid -1;
			else
				return array[mid];
		}
		return null;
	}

	private int midValue(int left, int right)
	{
		return left +(right - left)/2;
	}
}
