package structs;

/**
 * Created by Felipe on 06/10/2015.
 */
/*
	Descri��o:
		-Remo��o � retirada o primeiro elmento do vetor.
		-Elemento com a mesma prioridade � colocado antes.
		-O elento com maior
 */
public class StaticPriorityStack<E extends Comparable<E>> extends StaticStack<E> {


	public StaticPriorityStack(int maxSize) {
		super(maxSize);
	}

	public StaticPriorityStack(int maxSize, boolean isResizable) {
		super(maxSize,isResizable);
	}

	@Override
	public void push(E object) {
		if(isFull()) {
			doubleCapacity();
		}else {

			for(int i = 0;i < currentSize; i++){
				//if(object.compareTo(vector[i]))
			}
			vector[currentSize++] = object;
		}
	}
}
