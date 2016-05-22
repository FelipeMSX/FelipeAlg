package structs;

import _abstract.Queue_Stack;
import exception.EmptyCollectionException;

/**
 * Created by Felipe on 06/10/2015.
 */
/*
	Descrição:
		-Inserção é na posição mais a esquerda não vazia.
		-Remoção é retirada o primeiro elmento do vetor.
 */
public class StaticStack<E extends Comparable<E>> extends Queue_Stack<E> {


	public StaticStack(int maxSize) {
		super(maxSize);
	}

	public StaticStack(int maxSize, boolean isResizable) {
		super(maxSize,isResizable);
	}

	@Override
	public void push(E object) {
		if(isFull()) {
			doubleCapacity();
		}else {
			vector[currentSize++] = object;
		}
	}

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
