package _abstract;

import _interfaces.Common;
import exception.EmptyListException;

/**
 * Created by Felipe on 06/10/2015.
 */
public abstract  class StaticStruct<E> implements Common<E> {

	protected E[] vector;
	protected int size;
	protected int maxSize;
	public StaticStruct()
	{
		size = 0;
		maxSize = 100;
	}

	abstract protected void doubleCapacity();

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull()
	{
		return size == maxSize;
	}

	@Override @Deprecated
	public E getItem(E object) {
		return null;
	}

	@Override
	public E getFirst() {
		if(!isEmpty())
			return vector[0];
		else
			throw new EmptyListException();
	}

	@Override
	public E getLast() {
		if(!isEmpty())
			return vector[size-1];
		else
			throw new EmptyListException();
	}

}
