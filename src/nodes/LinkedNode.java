package nodes;

/**
 * @author FelipeMSX
 *
 */
public class LinkedNode<E> extends Node<E> {
	private LinkedNode<E> next;
	
	public LinkedNode(){
		super(null);
		this.next = null;
	}

	public LinkedNode(E object){
		super(object);
		this.next = null;
	}

	public LinkedNode<E> getNext() {
		return next;
	}

	public void setNext(LinkedNode<E> next) {
		this.next = next;
	}
}
