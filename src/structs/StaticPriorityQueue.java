package structs;

import _abstract.Queue_Stack;
import exception.EmptyCollectionException;
import exception.NullObjectException;

import java.util.Comparator;

/**
 *Remoção é retirado o elemento com maior prioridade. Elemento com a mesma prioridade é colocado antes.
 */

public class StaticPriorityQueue<E> extends StaticStack<E> {


	/**
	 * @param maxSize tamanho máximo inicial da coleção.
	 */
	public StaticPriorityQueue(int maxSize, Comparator<E> comparator) {
		super(maxSize,comparator);
	}

	/**
	 * @param maxSize tamanho máximo inicial da coleção.
	 * @param isResizable TRUE indica se a coleção tem um crescimento autônomo, caso contrário, ao tentar inserir um
     *                    elemento com o tamanho no limite lançará uma exceção.
	 */
	public StaticPriorityQueue(int maxSize, boolean isResizable, Comparator<E> comparator) {
		super(maxSize,isResizable,comparator);
	}

    /**
     * - Se o objeto for nulo lançará uma exceção .Se a lista estiver cheia irá tentar aumentar a sua capacidade, se o
     * seu crescimento autônomo estiver desabilitado"FALSE" irá lançar uma exceção. Com o objeto em mãos é procurado o
     * lugar onde ele deve ser inserido, objetos com a mesma prioridade serão inseridos antes.
     *
     * @param obj será colocado na fila, não pode ser nulo.
     */
    @Override
	public void push(E obj) {
        if(obj == null){
            throw new NullObjectException();
        }
		if(isFull()) {
			doubleCapacity();
            if(!isFull())
                push(obj);
		}else {
			int position = 0;
			if(!isEmpty()){
				//encontra a posição do item a ser colocado.

				while(compareTo(obj,vector[position]) < 0)
					position++;

				//desloca os itens posteriores uma posição a frente para colocar o objeto na posição encontrada.
				for(int i = currentSize-1; i >= position; i--){
					vector[i+1] = vector[i];
				}
			}
			vector[position] = obj;
			currentSize++;
		}
	}
}
