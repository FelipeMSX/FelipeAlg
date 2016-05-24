package _interfaces;


public interface Common<E> {
	 boolean isEmpty();
	 E getFirst();
	 E getLast();
	 E retrieve(E obj);
	 int getCurrentSize();
	 void disposeAll();
}
