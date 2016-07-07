package _abstract;

/**
 * @author FelipeMSX
 *
 */
public abstract class Node<E> {
	private E object;
	
	protected Node(){
		this.object = null;	
	}

	protected Node(E object){
		this.object = object;
	}
	
	public E getObject() {
		return object;
	}
	
	public void setObject(E object) {
		this.object = object;
	}
	
}
