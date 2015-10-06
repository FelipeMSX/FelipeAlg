package _interfaces;


public interface Common<E> {
	 boolean insert(E object) throws Exception;
	 E remove(E object);
	 boolean isEmpty();
	 E getItem(E object);
	 E getFirst();
	 E getLast();
	 void disposeAll();
}
