package sort;


public class MergeSort {

	/**
	 * @param args
	 */
	/*
	 * lembrar que java arredonda sempre pra baixo;
	 */
	
	static int i = 0;
	static int j = 0;
	static int k = 0;
	static int fileSize = 0;
	static String filePath = "";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(args[0]);
		
	}
	
	
	
	public static void readFile(){
		
	}
	//Prepara a função antes de ser chamada
	public static int[] mergesort(int input[]){
		return merge(new int[input.length], input, 0, input.length-1);
	}

	private static int[] merge(int output[], int input[], int ini, int fim) {
		if(ini < fim) {
			int meio = ini + (fim - ini) / 2;
			merge(output, input, ini, meio);
			merge(output, input, meio + 1, fim);
			intercalar(output, input, ini, meio, fim);
		}
		return output;
	}
	
	private static void intercalar(int output[], int input[], int ini,int meio, int fim) {
		 i = ini;
		 j = meio+1;
		 k = ini;
		
		//Varrer tudo até meio comparando com o primeiro elemento do meio +1
		//Depois e comparando os elementos
		while (i <= meio || j <= fim ){
			// Se já passou do fim, significa que não possui mais elementos do meio pro fim para inserir no vetor
			if(j > fim){
				output[k++] = input[i++];
			}
			else
			if(i > meio){
				output[k++] = input[j++];
			}
			else
			if(input[i] > input[j]){
				output[k++]= input[i++];
			}else{
				output[k++] = input[j++];
			}
		}	
		for(int w = ini ; w <= fim; w++){
			input[w] = output[w];
		}
		
	}
}
