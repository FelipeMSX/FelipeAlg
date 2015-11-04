package PAA;

import java.io.*;

/**
 * Created by Felipe Morais on 26/10/2015.
 */
public class Compressao {
	//https://en.wikibooks.org/wiki/Algorithm_Implementation/String_searching/Knuth-Morris-Pratt_pattern_matcher#C_and_Java

	static int diseaseCount;                            // Total de doenças.
	static FileData[] Diseases;							// Armazena o total de doenças à pesquisar.
	static StringBuilder steps = new StringBuilder();   // Armazena toda os passos que serão gravados no arquivo de saída.

	public static void main(String args[]) throws IOException
	{
//		if(args.length !=0) {
//			readInputFile(args[0]);
//
//			createSteps();
//			writeSteps(args[1]);
//		}

		System.out.println(encode("aaaaabbbbbcccccr"));
	}

	public static String encode(String source) {
		StringBuffer dest = new StringBuffer();
		for (int i = 0; i < source.length(); i++) {
			int runLength = 1;
			while (i + 1 < source.length()
					&& source.charAt(i) == source.charAt(i + 1)) {
				runLength++;
				i++;
			}
			dest.append(runLength);
			dest.append(source.charAt(i));
		}
		return dest.toString();
	}

	public void RunLengthEncoding(String input)
	{

	}
    public static void createSteps()
    {

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
			//validGeneCountMin   = Integer.parseInt(br.readLine());
			//geneticSequence     = br.readLine();
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

}
