package nodes;

/**
 * @author FelipeMSX
 *
 */
public class Node<E> {
	private E object;
	
	public Node(){
		this.object = null;	
	}
	
	public Node(E object){
		this.object = object;
	}
	
	public E getObject() {
		return object;
	}
	
	public void setObject(E object) {
		this.object = object;
	}
	
}
