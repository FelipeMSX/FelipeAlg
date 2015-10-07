package structs;

import _abstract.StaticStruct;

/**
 * Created by Felipe on 06/10/2015.
 */
public class StaticQueue<E extends Comparable<E>> extends StaticStruct<E> {


	public StaticQueue()
	{
		super();
	}

	@Override
	protected void doubleCapacity() {

	}

	@Override
	public boolean insert(E object) {
		return false;
	}

	@Override
	public E remove(E object) {
		return null;
	}

	@Override
	public void disposeAll() {

	}
}
