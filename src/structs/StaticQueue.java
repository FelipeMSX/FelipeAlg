package structs;

import _abstract.Queue_Stack;
import exception.EmptyCollectionException;
import exception.NullObjectException;

/**
 * Inser��o � na posi��o mais a esquerda n�o vazia. A sua remo��o � retirada o primeiro elmento do vetor.
 */

public class StaticQueue<E extends Comparable<E>> extends Queue_Stack<E> {


	/**
	 * Inicializa a fila com a capacidade de 100, e tambem por padr�o � dinamicamene crescente.
	 */
	public StaticQueue(){
		super();
	}
	/**
	 * @param maxSize Tamanho inicial m�ximo da fila.
	 */
	public StaticQueue(int maxSize) {
		super(maxSize);
	}

	/**
	 * @param maxSize Tamanho inicial m�ximo da fila.
	 * @param isResizable TRUE indica que a lista ir� crescer dinamicamente.
	 */
	public StaticQueue(int maxSize, boolean isResizable) {
		super(maxSize,isResizable);
	}

	/**
	 * Objeto n�o pode ser passado como nulo, se a fila estiver cheia ir� tentar crescer a fila e ap�s isso inserir na
	 * cole��o.
	 * @param obj ser� inserido no vetor na posi��o (vector.length).
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
