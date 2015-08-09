package nodes;

/**
 * @author FelipeMSX
 *
 */
public class LinkedDoubleNode<E> extends Node<E> {

	private LinkedDoubleNode<E> previous,next;
	
	public LinkedDoubleNode(){
		super();
		this.previous = null;
		this.next = null;
	}
	
	public LinkedDoubleNode(E object){
		super(object);
		this.previous = null;
		this.next = null;
	}

	public LinkedDoubleNode<E> getPrevious() {
		return previous;
	}

	public void setPrevious(LinkedDoubleNode<E> previous) {
		this.previous = previous;
	}

	public LinkedDoubleNode<E> getNext() {
		return next;
	}

	public void setNext(LinkedDoubleNode<E> next) {
		this.next = next;
	}
}
