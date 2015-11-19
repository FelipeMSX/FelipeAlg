package PAA;

import java.io.*;

/**
 * Created by Felipe Morais on 26/10/2015.
 */
public class KMP {

	static int validGeneCountMin;                       //Tamanho m�nimo para uma sequ�ncia ser considerada v�lida.
	static String geneticSequence;                      // A sequ�ncia gen�tica que ser� usada para encontrar os padr�es das doen�as.
	static int diseaseCount;                            // Total de doen�as.
	static FileData[] Diseases;							// Armazena o total de doen�as � pesquisar.
	static FileData[] sortedDiseases;
	static StringBuilder steps = new StringBuilder();   // Armazena toda os passos que ser�o gravados no arquivo de sa�da.

	public static void main(String args[]) throws IOException
	{
		if(args.length !=0) {
			readInputFile(args[0]);
			runKMP();
			sortDiseases(Diseases);
			createSteps();
			writeSteps(args[1]);
		}
	}

	public static void runKMP()
	{
		for(int k = 0; k < diseaseCount;k++)
		{
			FileData disease = Diseases[k];
			//Para cada doen�a fazer a pesquisa na sequ�ncia gen�tica inteira.
			int acceptableMatch = 0;
			for(int i = 0; i < disease.gene.length; i++)
			{
				KMPTable kTable		=  calcTableKMP(disease.gene[i]);
				int qtdMatched 		= searchKMP(geneticSequence, kTable);
				double percentage 	= (double)qtdMatched/(double)kTable.charSequence.length();

				if(percentage >= 0.9d)
					acceptableMatch++;

			}
			//C�lcula o total de porcengatem de acordo com a quantidade de genes aceit�veis com o total, f�rmula: aceitaveis/total;
			disease.percentageMatched = (byte)Math.round(((double)acceptableMatch /(double)disease.gene.length)*100);
		}
	}

	public static KMPTable calcTableKMP(String input) {
		int i = 1;
		int m = input.length();
		int len = 0;
		KMPTable table = new KMPTable(input);//Inicializar a table

		while(i < m) {
			if (table.charSequence.charAt(i) == table.charSequence.charAt(len)) {
				table.bigPS[i] = ++len;
				i++;
			}
			else {
				if (len != 0) {
					len = table.bigPS[len - 1];
				} else {
					table.bigPS[i] = 0;
					i++;
				}
			}
		}

		for(int k = 0; k < m; k++)
            table.bigPS[k] =  table.bigPS[k]-1;


		return table;
	}

	//retorna a posi��o da ocorr�ncia do primeiro match no texto;
	public static int searchKMP(String text, KMPTable table)
	{
		int tablePos 		= -1;// Indica a posi��o atual na tabelaKMP ao decorrer do algoritmo.
		int textP 			= 0 ;// Indica a posi��o atual no texto ao decorrer do algoritmo.
		int textLength 		= text.length();
		int	tableLength 	= table.charSequence.length();
		int lengthMatched 	= 0;
		int genCount 	    = 0;//A cada caractere encontrada incrementa essa vari�vel, para ser considerado v�lido deve ser maior que o m�nimo estabelecido.
		//Texto menor que o padr�o
		if(tableLength > textLength)
			return -1;

		while(textP != textLength)
		{
			if(text.charAt(textP) == table.charSequence.charAt(tablePos+1)) {
				tablePos++;
				genCount++;
                textP++;
				//FullMatch
				if(tablePos+1 == tableLength)
				{
					if(genCount >= validGeneCountMin)
						lengthMatched += genCount;

					return lengthMatched;
				}
			}else{
				if(genCount >= validGeneCountMin) {
					lengthMatched += genCount;
					String newSearch = table.charSequence.substring(genCount,tableLength);

					//Se n�o possuir  quantidade m�nima de caracteres encerrar.
					if(newSearch.length() < validGeneCountMin)
						return lengthMatched;

					//Regerar a tabela para continuar o algoritmo personalizado
					table 		=  calcTableKMP(newSearch);
                    tableLength = table.bigPS.length;
					tablePos 	= -1;
					genCount	= 0;
                    textP++;
				}else {
					//Se for diferente atribuir o valor da tabela calculado correspondente
                    tablePos = tablePos == -1 ? -1 :table.bigPS[tablePos];
					genCount = tablePos != -1 ? tablePos + 1 : 0;
                    if(tablePos == -1 )
                        textP++;
                }
			}
		}
		return lengthMatched;
	}

	//Ordena usando o mergesort as doen�as;
	public static void sortDiseases(FileData[] diseases)
	{
		mergesort(diseases);
	}

	//Prepara a fun��o antes de ser chamada
	public static void mergesort(FileData input[]){
		sortedDiseases = new FileData[diseaseCount];
		merge(input, 0, diseaseCount -1);
	}

	private static void merge(FileData input[], int ini, int end) {
		if(ini < end) {
			int middle = ini + (end - ini) / 2;
			merge(input, ini, middle);
			merge(input, middle + 1, end);
			intercalate(input, ini, middle, end);
		}
	}

	private static void intercalate(FileData input[], int init, int middle, int end) {
		int i = init;
		int j = middle+1;
		int k = init; // controlador do vetor output, a cada adi��o no vetor, � incrementado

		while (i <= middle || j <= end ) {

			// Se j� passou do fim, significa que n�o possui mais elementos do meio pro fim para inserir no vetor
			if(j > end)
				sortedDiseases[k++] = input[i++];
			// Se i > meio, significa que n�o existe mais elementos do inicio ao fim para comparar, agora � s� adicioar do meio +1 ao fim.
			else if(i > middle)
				sortedDiseases[k++] = input[j++];
			else if(input[i].percentageMatched >= input[j].percentageMatched)
				sortedDiseases[k++] = input[i++];
			else
				sortedDiseases[k++] = input[j++];
		}
		//Copiar os elementos para o vetor entrada
		for(int w = init ; w <= end; w++)
			input[w] = sortedDiseases[w];
	}

    public static void createSteps() {
        int qtd = sortedDiseases.length -1;
        for(int i = 0; i < qtd; i++)
            steps.append(sortedDiseases[i].diseaseName+": " +sortedDiseases[i].percentageMatched + "%\n");

        steps.append(sortedDiseases[qtd].diseaseName+": " +sortedDiseases[qtd].percentageMatched + "%");
    }

	public static void writeSteps(String filePath) throws IOException {
		FileWriter fw = new FileWriter( filePath );
		fw.write(steps.toString());
		fw.close();
	}

	public static void readInputFile(String filePath) throws FileNotFoundException {
		// Contem o caminho do arquivo
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		try
		{
			//L� os cont?iners cadastrados
			validGeneCountMin   = Integer.parseInt(br.readLine());
			geneticSequence     = br.readLine();
			diseaseCount        = Integer.parseInt(br.readLine());
			Diseases 			= new FileData[diseaseCount];
			int count = 0;
			while (br.ready())
			{
				Diseases[count] = examineLine(br.readLine());
				count++;
			}

			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static FileData examineLine(String line) {
		FileData data 		= new FileData();
		String[] analise 	= line.split(" ");
		data.diseaseName 	= analise[0];

		data.gene = new String[Integer.parseInt(analise[1])];
		for (int i = 0; i < data.gene.length; i ++)
			data.gene[i] = analise[i+2];

		return data;
	}

	private static class FileData {
		public String diseaseName;
		public String[] gene;
		public byte percentageMatched;
	}

	private static class KMPTable {
		public String charSequence;//Caract�res do padr�o
		public int bigPS[]; //Maior prefixo que tamb�m � sufixo;

		public KMPTable(String charSequence){
			this.charSequence = charSequence;
			this.bigPS = new int[charSequence.length()];
		}
	}

}
