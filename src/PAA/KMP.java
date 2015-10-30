package PAA;

import java.io.*;

/**
 * Created by Felipe Morais on 26/10/2015.
 */
public class KMP {
	//https://en.wikibooks.org/wiki/Algorithm_Implementation/String_searching/Knuth-Morris-Pratt_pattern_matcher#C_and_Java

	static int validGeneCountMin;                       //Tamanho mínimo para uma sequência ser considerada válida.
	static String geneticSequence;                      // A sequência genética que será usada para encontrar os padrões das doenças.
	static int diseaseCount;                            // Total de doenças.
	static FileData[] Diseases;							// Armazena o total de doenças à pesquisar.
	static FileData[] sortedDiseases;
	static StringBuilder steps = new StringBuilder();   // Armazena toda os passos que serão gravados no arquivo de saída.
	static int lastPositionValid;

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
		for(int k = 0; k < Diseases.length;k++)
		{
			FileData disease = Diseases[k];
			//Para cada doença fazer a pesquisa na sequência genética inteira.
			int acceptableMatch = 0;
			for(int i = 0; i < disease.gene.length; i++)
			{
				lastPositionValid 	= 0;
				KMPTable kTable		=  calcTableKMP(disease.gene[i]);
				int qtdMatched 		= searchKMP(geneticSequence, kTable);
				double percentage 	= (double)qtdMatched/(double)kTable.charSequence.length();

				if(percentage >= 0.9d)
				{
					acceptableMatch++;
				}
			}
			//Cálcula o o total de porcengatem de acordo com a quantidade de genes aceitáveis com o total, fórmula: aceitaveis/total;
			disease.percentageMatched = (byte)Math.round(((double)acceptableMatch /(double) disease.gene.length)*100);
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
			} else // (pat[i] != pat[len])
			{
				if (len != 0) {
					len = table.bigPS[len - 1];

				} else // if (len == 0)
				{
					table.bigPS[i] = 0;
					i++;
				}
			}
		}

		for(int k = 0; k < m; k++)
            table.bigPS[k] =  table.bigPS[k]-1;
		return table;
	}

	//retorna a posição da ocorrência do primeiro match no texto;
	public static int searchKMP(String text, KMPTable table)
	{
		int tableP 			= -1;//TablePonteiro
		int textP 			= 0 ;//TextoPonteiro
		int textLength 		= text.length();
		int	tableLength 	= table.charSequence.length();
		lastPositionValid 	= 0;
		int tempGenCount 	= 0;
		//Texto menor que o padrão
		if(tableLength > textLength)
			return -1;

		StringBuilder sTeste = new StringBuilder();
		while(textP != textLength)
		{
			sTeste.append(text.charAt(textP));
			if(text.charAt(textP) == table.charSequence.charAt(tableP+1)) {
				tableP++;
				tempGenCount++;
                textP++;
				//FullMatch
				if(tableP+1 == tableLength)
				{
					if(tempGenCount >= validGeneCountMin) {
						lastPositionValid += tempGenCount;
					}
					return lastPositionValid;
				}
			}else{
				if(tempGenCount >= validGeneCountMin) {
					lastPositionValid += tempGenCount;
					String d = table.charSequence.substring(tempGenCount,tableLength);

					//Se não possuir  quantidade mínima de caracteres encerrar.
					if(d.length() < validGeneCountMin)
						return lastPositionValid;

					table 			=  calcTableKMP(d);
                    tableLength 	= table.bigPS.length;
					tableP 			= -1;
					tempGenCount	= 0;
                    textP++;
				}else {
					//Se for diferente atribuir o valor da tabela calculado correspondente
                    tableP = tableP == -1 ? -1 :table.bigPS[tableP];
					tempGenCount = tableP != -1 ? tableP + 1 : 0;
                    if(tableP == -1 )
                        textP++;
                }
			}
		}
		return lastPositionValid;
	}

	//Ordena usando o mergesort as doenças;
	public static void sortDiseases(FileData[] diseases)
	{
		mergesort(diseases);
	}

	//Prepara a função antes de ser chamada
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
		int k = init; // controlador do vetor output, a cada adição no vetor, é incrementado

		while (i <= middle || j <= end ) {

			// Se já passou do fim, significa que não possui mais elementos do meio pro fim para inserir no vetor
			if(j > end) {
				sortedDiseases[k++] = input[i++];
			}
			// Se i > meio, significa que não existe mais elementos do inicio ao fim para comparar, agora é só adicioar do meio +1 ao fim.
			else if(i > middle) {
				sortedDiseases[k++] = input[j++];
			}
			else if(input[i].percentageMatched >= input[j].percentageMatched) {
				sortedDiseases[k++] = input[i++];
			}else {
				sortedDiseases[k++] = input[j++];
			}
		}
		//Copiar os elementos para o vetor entrada
		for(int w = init ; w <= end; w++){
			input[w] = sortedDiseases[w];
		}

	}

    public static void createSteps()
    {
        int qtd = sortedDiseases.length -1;
        for(int i = 0; i < qtd; i++)
        {
            steps.append(sortedDiseases[i].diseaseName+": " +sortedDiseases[i].percentageMatched + "%\n");
        }
        steps.append(sortedDiseases[qtd].diseaseName+": " +sortedDiseases[qtd].percentageMatched + "%");
    }

	public static void writeSteps(String filePath) throws IOException
	{
		FileWriter fw = new FileWriter( filePath );
		fw.write(steps.toString());
		fw.close();
	}

	public static String formatOutput(String disease, int percentage)
	{
		//String.format("%s: %d%%","H1Z1",123));
		return disease+": "+percentage+'%';
	}

	public static void readInputFile(String filePath) throws FileNotFoundException
	{
		// Contem o caminho do arquivo
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		try
		{
			//L? os cont?iners cadastrados
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

	public static FileData examineLine(String line){
		FileData data 		= new FileData();
		String[] analise 	= line.split(" ");
		data.diseaseName 	= analise[0];

		data.gene = new String[Integer.parseInt(analise[1])];
		for (int i = 0; i < data.gene.length; i ++)
		{
			data.gene[i] = analise[i+2];
		}

		return data;
	}


	private static class FileData {
		public String diseaseName;
		public String[] gene;
		public byte percentageMatched;
	}

	private static class KMPTable {
		public String charSequence;
		public int bigPS[]; //Maior prefixo que tamb?m ? sufixo;

		public KMPTable(String charSequence){
			this.charSequence = charSequence;
			this.bigPS = new int[charSequence.length()];
		}
	}

}
