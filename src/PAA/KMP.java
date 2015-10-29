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
	static FileData[] Diseases;                         // Armazena o total de doenças à pesquisar.
	static StringBuilder steps = new StringBuilder();   // Armazena toda os passos que serão gravados no arquivo de saída.
	static int lastPositionValid;

	public static void main(String args[]) throws IOException
	{
//		if(args.length !=0) {
//			readInputFile(args[0]);
//			runKMP();
//			writeSteps(args[0]);
//		}
		validGeneCountMin = 3;
		KMPTable table =  calcTableKMP("AATAAT");
		int qtdMatched = searchKMP("AAAATTTCGTTAAATTTGAACATAGGGATA",table);
		System.out.println(qtdMatched);
	}

	public static void runKMP()
	{
		int diseaseCount = 0;
		while(Diseases.length != diseaseCount)
		{
			FileData disease = Diseases[diseaseCount];

			//Para cada gene fazer a pesquisa na sequência genética inteira.
			for(int i = 0; i < disease.gene.length; i++)
			{
				calcTableKMP(disease.gene[i]);
			}
			diseaseCount++;
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
		int tableP = -1;//TablePonteiro
		int textP = 0 ;///TextoPonteiro
		int textLength = text.length();
		int	tableLength = table.charSequence.length();

		lastPositionValid = -1;
		int tempGenCount = 0;
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

				//FullMatch
				if(tableP+1 == tableLength)
				{
					if(tempGenCount >= validGeneCountMin) {
						lastPositionValid = tableP;
					}
					return ++lastPositionValid;
				}

			}else{
				if(tempGenCount >= validGeneCountMin) {
					lastPositionValid = tableP;
					int t = (tableLength - (tableP+1));
					//CanBreak
					if( (tableLength - (tableP+1)) < validGeneCountMin)
					{
						return ++lastPositionValid;
					}
				}
				tempGenCount = 0;
				//Se for diferente atribuir o valor da tabela calculado correspondente
				tableP = tableP == -1 ? -1 :table.bigPS[tableP];

				//Corrigir caso fique antes do lastPositionValid
				if(tableP < lastPositionValid)
					tableP = lastPositionValid;

				int calc = lastPositionValid;
				while(calc < tableP) {
				calc++;
						tempGenCount++;
				}
			}
				textP++;

		}
		return ++lastPositionValid; //nomatchFound
	}

	public static void writeSteps(String filePath) throws IOException
	{
		FileWriter fw = new FileWriter( filePath );
		fw.write(steps.toString());
		fw.close();
	}

	public static String formatOutput(String disease, int percentage)
	{
		// String.format("%s: %d%%","H1Z1",123));
		return disease+": "+percentage+'%';
	}

	public static void readInputFile(String filePath) throws FileNotFoundException
	{
		// Contem o caminho do arquivo
		FileReader fr = new FileReader(filePath);

		if (fr != null)
		{
			BufferedReader br = new BufferedReader(fr);
			try
			{
				//L? os cont?iners cadastrados
				validGeneCountMin   = Integer.parseInt(br.readLine());
				geneticSequence     = br.readLine();
				diseaseCount        = Integer.parseInt(br.readLine());
				Diseases = new FileData[diseaseCount];
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
	}

	public static FileData examineLine(String line){
		FileData data 		= new FileData();
		String[] analise 	= line.split(" ");
		data.diseaseName 			= analise[0];

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
	}

	private static class KMPTable {
		public String charSequence;
		public int bigPS[]; //Maior prefixo que tamb?m ? sufixo;

		public KMPTable(String charSequence){
			this.charSequence = charSequence;
			this.bigPS = new int[charSequence.length()];
		}

		@Override
		public String toString()
		{
			String s = "";
			for(int i = 0; i < bigPS.length; i++)
				s+= bigPS[i]+" ";

			return s;
		}
	}

}
