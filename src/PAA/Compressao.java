package PAA;

import util.ElapsedTime;

import java.io.*;

/**
 * Created by Felipe Morais on 26/10/2015.
 */
public class Compressao {

	static FileData[] inputValues;							// Armazena o total de doenças à pesquisar.
	static StringBuilder steps = new StringBuilder();   // Armazena toda os passos que serão gravados no arquivo de saída.
	static final short RLE_MAX_COUNT = (short)255;
	static int RLE_Auxiliary;
	static int TOTAL;

	public static void main(String args[]) throws IOException {
		if(args.length !=0) {
			readInputFile(args[0]);
			runCompressor();
			createSteps();
			writeSteps(args[1]);
		}

	}

	public static void runCompressor(){
		int i = 0;
		while(i < TOTAL) {
			RLE_Auxiliary = 0;
			System.out.println(RunLengthEncoding(inputValues[i].decodeValue));
		//	System.out.println((double)RLE_Auxiliary/(double)inputValues[i].decodeValue.length);
			i++;
		}
	}

	public static String RunLengthEncoding(short[] input)
	{
		int size = input.length;
		StringBuilder dest = new StringBuilder();
		for (int i = 0; i < size; i++) {
			short runLength = (short)1;
			while (i + 1 < size && input[(i)] == input[i+1] && runLength != RLE_MAX_COUNT) {
				runLength++;
				i++;
			}
			dest.append(String.format("0x%02X", runLength));
			dest.append(String.format(" 0x%02X ", input[i]));
			RLE_Auxiliary += 2;
		}
		dest.deleteCharAt(dest.length()-1);
		return dest.toString();
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
			TOTAL               = Integer.parseInt(br.readLine());
			inputValues = new FileData[TOTAL];
			int count = 0;
			while (br.ready())
			{
				inputValues[count] = examineLine(br.readLine());
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
		data.decodeValue    = new short[(Short.parseShort(analise[0]))];
		for (int i = 0; i < data.decodeValue.length; i ++)
		{
			data.decodeValue[i] = Short.decode(analise[i+1]);
		}

		return data;
	}


	private static class FileData {
		public short[] decodeValue;
	}

}
