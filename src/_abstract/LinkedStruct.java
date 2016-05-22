package _abstract;

import _interfaces.Common;
import exception.EmptyCollectionException;
import nodes.LinkedNode;

/**
 * Created by Felipe on 07/10/2015.
 */
public abstract class LinkedStruct<E>  implements Common<E> {

	//Não possui dado, é somente o ponteiro para o primeiro elemento.
	protected LinkedNode<E> head ;
	protected int size;

	public LinkedStruct()
	{
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E getFirst() {
		if(!isEmpty())
			return null;
		else
			throw new EmptyCollectionException();
	}

	@Override
	public E getLast() {
		if(!isEmpty())
			return null;
		else
			throw new EmptyCollectionException();
	}

	public int getSize()
	{
		return size;
	}

}


