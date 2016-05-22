package _abstract;

import _interfaces.Common;
import exception.EmptyCollectionException;

/**
 * Created by Felipe on 06/10/2015.
 */
public abstract class StaticStruct<E> implements Common<E> {

	protected E[] vector;
	protected int size;
	protected int maxSize;

	public StaticStruct()
	{
		size = 0;
		maxSize = 100;
		vector = (E[]) new Object[maxSize];
	}

	public StaticStruct(int size, int maxSize)
	{
		this.size = size;
		this.maxSize = maxSize;
		vector = (E[]) new Object[maxSize];
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

	@Override
	public E getFirst() {
		if(!isEmpty())
			return vector[0];
		else
			throw new EmptyCollectionException();
	}

	@Override
	public E getLast() {
		if(!isEmpty())
			return vector[size-1];
		else
			throw new EmptyCollectionException();
	}

	public int getSize()
	{
		return size;
	}

}
