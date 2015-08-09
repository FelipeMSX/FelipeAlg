package search;

public class SequentialSearch<E extends Comparable<E>>  {

	/*
	 * Percorre toda a lista at� achar o elemento, retorna null caso n�o encontre-o.
	 * complexidade:
	 * melhor caso = k;
	 * pior caso = n+1;
	 * m�dio caso = n/2;
	 */
	
	public E search(E[] itens, E item){
		for(E value : itens){
			if(value.compareTo(item)== 0)
				return value;		
		}
		return null;
	}
}
