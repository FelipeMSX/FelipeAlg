package structs;

/**
 * Created by Felipe on 06/10/2015.
 */
/*
	Descri��o:
		-Remo��o � retirado o elemento com maior prioridade.
		-Elemento com a mesma prioridade � colocado antes.
 */
public class StaticPriorityQueue<E extends Comparable<E>> extends StaticQueue<E> {


	public StaticPriorityQueue(int maxSize) {
		super(maxSize);
	}

	public StaticPriorityQueue(int maxSize, boolean isResizable) {
		super(maxSize,isResizable);
	}

	@Override
	public void push(E object) {
		if(isFull()) {
			doubleCapacity();
		}else {
			int position = 0;
			if(!isEmpty()){
				//encontra a posi��o do item a ser colocado.
				while(object.compareTo(vector[position]) < 0)
					position++;

				//desloca os itens posteriores uma posi��o a frente para colocar o objeto na posi��o encontrada.
				for(int i = currentSize-1; i >= position; i--){
					vector[i+1] = vector[i];
				}
			}
			vector[position] = object;
			currentSize++;
		}
	}
}
