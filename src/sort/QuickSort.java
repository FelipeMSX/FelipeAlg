package sort;

public class QuickSort {


	/*
	 * Ordem crescente, Não aceita valores iguais
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		int x[] = {-19,1,18,15,21,3,8,-5,0,2,9,59,10,7};
		int y[] = quick(x);
		
		for(int i =0; i < y.length ; i++)
			System.out.println(y[i]);

	}
	
	public static int[] quick(int input[])
	{
		quicksort(input,0, input.length-1);	
		return input;
	}

	/*
	 * Posição do pivo é sempra o último elemento
	 */
	private static  void quicksort(int V[], int inicio, int fim) 
	{
		if(inicio < fim) 
		{
			int pivo = particionar(V, inicio, fim);
			quicksort(V, inicio, pivo - 1);
			quicksort(V, pivo + 1, fim);
		}
	}
	
	private static int particionar(int input[], int inicio, int fim)
	{
		int positionPivo = fim; // pivo será sempre o ultimo elemento;
		int left = 0; int right = fim -1; 
		
		// significa que i j acabaram de se cruzar
		while(left < right)
		{
			while(input[left] < input[positionPivo])
				left++;
			
			while(input[right] > input[positionPivo])
				right++;
			
			if(left < right)
			{
				int temp = input[left];
				input[left] = input[right];
				input[right] = temp;
			}else
			{
				int temp = input[left];
				input[left] = input[positionPivo];
				input[positionPivo] = temp;
				positionPivo = left;
			}
		}

		return positionPivo;
		
	}
	

}
