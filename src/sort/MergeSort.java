package sort;

public class MergeSort<E extends Comparable<E>> {

	E sortedDiseases[];

	//Prepara a função antes de ser chamada
	public void merge(E input[])
	{
		mergesort(input,input.length);
	}

	private void mergesort(E input[], int length){

		sortedDiseases = (E[]) new Comparable[length];
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
				sortedDiseases[k++] = input[i++];
				// Se i > meio, significa que não existe mais elementos do inicio ao fim para comparar, agora é só adicioar do meio +1 ao fim.
			else if(i > middle)
				sortedDiseases[k++] = input[j++];
			else if(input[i].compareTo(input[j]) >= 0)
				sortedDiseases[k++] = input[i++];
			else
				sortedDiseases[k++] = input[j++];
		}
		//Copiar os elementos para o vetor entrada
		for(int w = init ; w <= end; w++)
			input[w] = sortedDiseases[w];
	}

}
