package structs;

import exception.NullObjectException;

/**
 *Remo��o � retirado o elemento com maior prioridade. Elemento com a mesma prioridade � colocado antes.
 */

public class StaticPriorityQueue<E extends Comparable<E>> extends StaticQueue<E> {


	/**
	 * @param maxSize tamanho m�ximo inicial da cole��o.
	 */
	public StaticPriorityQueue(int maxSize) {
		super(maxSize);
	}

	/**
	 * @param maxSize tamanho m�ximo inicial da cole��o.
	 * @param isResizable TRUE indica se a cole��o tem um crescimento aut�nomo, caso contr�rio, ao tentar inserir um
     *                    elemento com o tamanho no limite lan�ar� uma exce��o.
	 */
	public StaticPriorityQueue(int maxSize, boolean isResizable) {
		super(maxSize,isResizable);
	}

    /**
     * - Se o objeto for nulo lan�ar� uma exce��o .Se a lista estiver cheia ir� tentar aumentar a sua capacidade, se o
     * seu crescimento aut�nomo estiver desabilitado"FALSE" ir� lan�ar uma exce��o. Com o objeto em m�os � procurado o
     * lugar onde ele deve ser inserido, objetos com a mesma prioridade ser�o inseridos antes.
     *
     * @param obj ser� colocado na fila, n�o pode ser nulo.
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
				//encontra a posi��o do item a ser colocado.
				while(obj.compareTo(vector[position]) < 0)
					position++;

				//desloca os itens posteriores uma posi��o a frente para colocar o objeto na posi��o encontrada.
				for(int i = currentSize-1; i >= position; i--){
					vector[i+1] = vector[i];
				}
			}
			vector[position] = obj;
			currentSize++;
		}
	}
}
