package structs;

import _abstract.StaticStruct;
import _interfaces.Queue_Stacks;
import exception.EmptyCollectionException;

/**
 * Created by Felipe on 06/10/2015.
 */
public class StaticQueue<E> extends StaticStruct<E> implements Queue_Stacks<E>{


	public StaticQueue()
	{
		super();
	}

	public StaticQueue(int size, int maxSize)
	{
		super(size,maxSize);
	}

	@Override
	public boolean insert(E object) {
		if(isFull())
		{
			doubleCapacity();
		}else
		{
			vector[size++] = object;

		}
		return true;
	}
	@Override
	public E remove() {
		if(isEmpty())
		{
			throw new EmptyCollectionException();
		}else
		{
			E temp = vector[0];
			size--;
			for(int i =0; i < size;i++)
			{
				vector[i] = vector[i+1];
			}
			return temp;
		}
	}

	@Override
	protected void doubleCapacity() {
		if(size == maxSize)
		{
			E[] temp = (E[])new Object[size*2];
			maxSize *=2;

			for(int i = 0; i < size; i++)
			{
				temp[i] = vector[i];
			}
			vector = temp;
		}
	}

	@Override
	public void disposeAll() {
		vector = null;
		size = 0;
	}


}
