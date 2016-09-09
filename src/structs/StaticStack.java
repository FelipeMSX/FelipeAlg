package structs;

import _abstract.Queue_Stack;
import exception.EmptyCollectionException;
import exception.NullObjectException;

import java.util.Comparator;

/**
 * 	Inser��o � na posi��o mais a esquerda n�o vazia. Remo��o � retirada o primeiro elmento do vetor.
 */

public class StaticStack<E> extends Queue_Stack<E> {


	/**
	 * @param maxSize Inicializa a pilha com um valor limite m�ximo.
	 */
	public StaticStack(int maxSize) {
		super(maxSize);
	}

	/**
	 * @param maxSize Inicializa a pilha com um valor limite m�ximo.
	 * @param isResizable Propriedade que define se a pilha ir� crescer dinamicamente.
	 */
	public StaticStack(int maxSize, boolean isResizable) {
		super(maxSize,isResizable);
	}


	/**
	 * @param maxSize Inicializa a pilha com um valor limite m�ximo.
	 */
	public StaticStack(int maxSize, Comparator<E> comparator) {
		super(maxSize,comparator);
	}

	/**
	 * @param maxSize Inicializa a pilha com um valor limite m�ximo.
	 * @param isResizable Propriedade que define se a pilha ir� crescer dinamicamente.
	 */
	public StaticStack(int maxSize, boolean isResizable,  Comparator<E> comparator) {
		super(maxSize,isResizable,comparator);
	}

	/**
	 * Objeto a ser inserido n�o pode ser nulo, antes de tentar inserir ser� verificado se a lista est� cheia, ap�s
	 * isso ir� inserir o objeto.
	 * @param obj Elemento que ser� inserido na pilha.
	 */
	@Override
	public void push(E obj) {
		if(obj == null){
			throw new NullObjectException();
		}
		else
		if(isFull()) {
			doubleCapacity();
			if(!isFull())
				push(obj);
		}else {
			vector[currentSize++] = obj;
		}
	}

	/**
	 * Se a lista estiver vazia lan�ar� uma exce��o, caso contr�rio, ir� remover o objeto normalmente.
	 * @return Elemento que primeiro deve ser retirado da pilha.
	 */
	@Override
	public E pop() {
		if(isEmpty()) {
			throw new EmptyCollectionException();
		}else {
			E temp = vector[--currentSize];
			vector[currentSize] = null;

			return temp;
		}
	}

	/**
	 * Se a cole��o estiver vazia lan�ar� uma exce��o, caso contr�rio, ir� retornar o primeiro elemento a sair da pilha.
	 * @return Retorna o primeiro elemento a ser removido da pilha.
	 */
	@Override
	public E getFirst(){
		if(isEmpty()) {
			throw new EmptyCollectionException();
		}else {
			return vector[currentSize-1];
		}
	}

	/**
	 * Se a cole��o estiver vazia lan�ar� uma exce��o, caso contr�rio, ir� retornar o �ltimo elemento a sair da pilha.
	 * @return O �timo elemento a ser retirado da pilha.
	 */
	@Override
	public E getLast(){
		if(isEmpty()) {
			throw new EmptyCollectionException();
		}else {
			return vector[0];
		}
	}
}
