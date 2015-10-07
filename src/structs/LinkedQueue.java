package structs;

import _abstract.LinkedStruct;
import _interfaces.Queue_Stacks;
import exception.EmptyListException;
import nodes.LinkedDoubleNode;
import nodes.LinkedNode;

/**
 * Created by Felipe on 06/10/2015.
 */
public class LinkedQueue<E> extends LinkedStruct<E, LinkedNode<E>> implements Queue_Stacks<E> {


	public LinkedQueue() {
		super();
	}

	@Override
	public void disposeAll() {
		head.setNext(null);
		size =0;
	}

	@Override
	public boolean insert(E object) {

		return false;
	}

	@Override
	//Remover último elemento da calda.
	public E remove() {
		if(isEmpty())
		{
			throw new EmptyListException();
		}else
		{
			LinkedNode<E> node = head.getNext();
			LinkedNode<E> nodeAnterior = head.getNext();
			while(node.hasNextNode())
			{
				nodeAnterior = node;
				node = node.getNext();
			}

			nodeAnterior.setNext(null);
			size--;

			return node.getObject();
		}
	}


	@Override
	public E getFirst() {
		if(!isEmpty())
			return head.getNext().getObject();
		else
			throw new EmptyListException();
	}

	@Override
	public E getLast() {
		if(!isEmpty())
		{
			LinkedNode<E> node = head.getNext();
			while(node.hasNextNode())
			{
				node = node.getNext();
			}
			return node.getObject();
		}
		else
			throw new EmptyListException();
	}
}
