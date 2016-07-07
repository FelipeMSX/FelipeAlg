package sort;


/*
	Descrição:
		- Aceita valores iguais.
        - Ordem Crescente.
*/
@SuppressWarnings("unchecked")
public class MergeSort<E extends Comparable<E>> {

	private E tempVector[];

	//Prepara a função antes de ser chamada
	public void sort(E input[])
	{
		mergesort(input,input.length);
	}

	private void mergesort(E input[], int length){

		tempVector = (E[]) new Comparable[length];
		merge(input, 0, length -1);
	}

	private void merge(E input[], int ini, int end) {
		if(ini < end) {
			int middle = ini + (end - ini) / 2;
			merge(input, ini, middle);
			merge(input, middle + 1, end);
			intercalate(input, ini, middle, end);
		}
	}

	private void intercalate(E input[], int init, int middle, int end) {
		int i = init;
		int j = middle+1;
		int k = init; // controlador do vetor output, a cada adição no vetor, é incrementado

		while (i <= middle || j <= end ) {

			// Se já passou do fim, significa que não possui mais elementos do meio pro fim para inserir no vetor
			if(j > end)
				tempVector[k++] = input[i++];
				// Se i > meio, significa que não existe mais elementos do inicio ao fim para comparar, agora é só adicioar do meio +1 ao fim.
			else if(i > middle)
				tempVector[k++] = input[j++];
			else if(input[i].compareTo(input[j]) <= 0)
				tempVector[k++] = input[i++];
			else
				tempVector[k++] = input[j++];
		}
		//Copiar os elementos para o vetor entrada
		for(int w = init ; w <= end; w++)
			input[w] = tempVector[w];
	}

}
