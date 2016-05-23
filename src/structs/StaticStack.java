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
			E temp = vector[--currentSize];
			vector[currentSize] = null;

			return temp;
		}
	}

	@Override
	public E getFirst(){
		if(isEmpty()) {
			throw new EmptyCollectionException();
		}else {
			return vector[currentSize-1];
		}
	}

	@Override
	public E getLast(){
		if(isEmpty()) {
			throw new EmptyCollectionException();
		}else {
			return vector[0];
		}
	}
}
