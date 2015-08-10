package interfaces;


public interface Common<E> {
	public boolean insert(E object) throws Exception;
	public boolean remove(E object);
	public boolean isEmpty();
	public E getItem(E object);
	public E getFirst();
	public E getLast();
	public void disposeAll();
}
