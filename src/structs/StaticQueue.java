package structs;

import _abstract.Queue_Stack;
import exception.EmptyCollectionException;
import exception.NullObjectException;

/**
 * Inserção é na posição mais a esquerda não vazia. A sua remoção é retirada o primeiro elmento do vetor.
 */

public class StaticQueue<E extends Comparable<E>> extends Queue_Stack<E> {


	/**
	 * Inicializa a fila com a capacidade de 100, e tambem por padrão é dinamicamene crescente.
	 */
	public StaticQueue(){
		super();
	}
	/**
	 * @param maxSize Tamanho inicial máximo da fila.
	 */
	public StaticQueue(int maxSize) {
		super(maxSize);
	}

	/**
	 * @param maxSize Tamanho inicial máximo da fila.
	 * @param isResizable TRUE indica que a lista irá crescer dinamicamente.
	 */
	public StaticQueue(int maxSize, boolean isResizable) {
		super(maxSize,isResizable);
	}

	/**
	 * Objeto não pode ser passado como nulo, se a fila estiver cheia irá tentar crescer a fila e após isso inserir na
	 * coleção.
	 * @param obj será inserido no vetor na posição (vector.length).
	 */
	@Override
	public void push(E obj) {
		if(obj == null) {
			throw new NullObjectException();
		}
		else
		if(isFull()) {
			doubleCapacity();
			if(!isFull())
				push(obj);
		}else {
			vector[currentSize++] = obj;
		}
	}

	/**
	 * @return O primeiro elemeto a ser retirado da fila.
	 */
	@Override
	public E pop() {
		if(isEmpty()) {
			throw new EmptyCollectionException();
		}else {
			E obj = vector[0];
			currentSize--;
			for(int i = 0; i < currentSize; i++){
				vector[i] = vector[i+1];
			}
			return obj;
		}
	}
}
