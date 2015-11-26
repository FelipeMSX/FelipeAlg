package PAA;


import util.ElapsedTime;

import java.io.*;


/**
 * Created by Felipe Morais on 05/11/2015.
 */
public class Compressao {

	static FileData[] inputValues;							// Armazena o total de doenças à pesquisar.
	static StringBuilder steps = new StringBuilder();   // Armazena toda os passos que serão gravados no arquivo de saída.
	static final short RLE_MAX_COUNT = (short)255;
	static int RLE_Auxiliary;
	static int Huffman_Auxiliary;
	static int TOTAL;

	public static void main(String args[]) throws IOException {
		ElapsedTime et = new ElapsedTime();
		if(args.length !=0) {
			readInputFile(args[0]);
			runCompressor();
			createSteps();
			writeSteps(args[1]);
		}

		System.out.println(et.calculateElapsedTimeInMilliSeconds());
	}

	public static void runCompressor(){
		int i = 0;
		steps = new StringBuilder();
		while(i < TOTAL) {
			RLE_Auxiliary = 0;
			Huffman_Auxiliary = 0;
			short[] result = RunLengthEncoding(inputValues[i].decodeValue);
			Huffman(inputValues[i].decodeValue);
		//	System.out.println((double)RLE_Auxiliary/(double)inputValues[i].decodeValue.length);

			RLEtoOutput(result,inputValues[i].decodeValue.length, RLE_Auxiliary);
			inputValues[i].decodeValue = null;
			i++;
		}
	}

	public static void RLEtoOutput(short[] result,int originalSize, int resultSize )
	{
		int size = result.length-1;
		//Calcular porcentagem

		double RLE = (double)resultSize/(double)originalSize*100;
		if(RLE < 100d) {
			steps.append("[RLE " + String.format("%.2f", RLE).replace(',', '.') + "%] ");

			for (int i = 0; i < size; i++) {
				String hex = Integer.toHexString(result[i]).toUpperCase();
				if (hex.length() == 1) {
					steps.append("0x0" + hex + " ");

				} else {
					steps.append("0x" + hex + " ");
				}
			}
			String hex = Integer.toHexString(result[result.length - 1]).toUpperCase();
			if (hex.length() == 1) {
				steps.append("0x0" + hex + "\n");

			} else {
				steps.append("0x" + hex + "\n");
			}
		}
	}
	public static short[] RunLengthEncoding(short[] input)
	{
		int size = input.length;
		short[] resultInput = new short[input.length*2];
		int resultCount = 0;
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < size; i++) {
			short runLength = (short)1;
			while (i + 1 < size && input[(i)] == input[i+1] && runLength != RLE_MAX_COUNT) {
				runLength++;
				i++;
			}

			resultInput[resultCount++] = runLength;
			resultInput[resultCount++] = input[i];

			RLE_Auxiliary += 2;
		}
		return resultInput;
	}

	public static StringBuilder Huffman(short input[])
	{
		//Montar histograma
		short[] countInput = CountInput(input);

		PriorityQueue priorityQueue = new PriorityQueue(80);
		for(short i = 0; i < 255 ; i++) {
			//Inserir na fila de prioridade mínima
			if(countInput[i]!= 0) {
				BinaryTree node = new BinaryTree();
				node.simb = i;
				node.freq = countInput[i];
				priorityQueue.insert(node);
			}
		}

		//montar árvore
		BinaryTree BiTree = createTree(priorityQueue);

		//Montar Tabela
	//	TableCod[] tc = createTableToCompress(BiTree);

		//Compressão
		//StringBuilder result = compress(tc,input);

		return null;
	}

	public static short[] CountInput(short input[])
	{
		int size = input.length;
		short[] countInput = new short[256];
		for(int i =0; i < size;i++) {
			countInput[input[i]]++;
		}

		return countInput;
	}

	private static BinaryTree createTree(PriorityQueue priorityQueue)
	{
		while(priorityQueue.size != 1)
		{
			BinaryTree nodeLeft     = priorityQueue.remove();
			BinaryTree nodeRight    = priorityQueue.remove();
			BinaryTree nodeFather   = new BinaryTree();
			nodeFather.simb         = TOKEN_NULL;
			nodeFather.freq         = nodeLeft.freq + nodeRight.freq;
			nodeFather.left         = nodeLeft;
			nodeFather.right        = nodeRight;
			priorityQueue.insert(nodeFather);
		}
		return priorityQueue.remove();
	}


	private static TableCod[] createTableToCompress(BinaryTree biTree)
	{
		return null;
	}

	private static StringBuilder compress(TableCod[] tc,short[] input)
	{
		StringBuilder result = new StringBuilder();
		int size = input.length;
		for(int i =0; i < size; i ++)
		{
			result.append(tc[input[i]].valueCompressed);
		}
		return result;
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

	public static class PriorityQueue {
		BinaryTree[] vector;
		int maxSize;
		int size;


		public PriorityQueue(int maxSize) {
			this.maxSize = maxSize;
			this.vector = new BinaryTree[maxSize];
		}


		public boolean insert(BinaryTree object) {
			if (size == maxSize) {
				doubleCapacity();
			} else {

					for(int i = 0; i < size; i++){
						if(object.freq >= vector[i].freq){
							for(int j = size; j > i; j--){
								vector[j] = vector[j-1];
							}
							vector[i] = object;
							size++;
							return true;
						}
					}
					vector[size++] = object;
			}
			return true;
		}

		public BinaryTree remove() {
			return  vector[--size];
		}

		protected void doubleCapacity() {
			if (size == maxSize) {
				BinaryTree[] temp = new BinaryTree[size * 2];
				maxSize *= 2;

				for (int i = 0; i < size; i++) {
					temp[i] = vector[i];
				}
				vector = temp;
			}
		}
	}


	static short TOKEN_NULL = 300;
	public static class BinaryTree {
		public int freq;
		short simb;
		BinaryTree right;
		BinaryTree left;
	}

	public static class TableCod
	{
		String valueCompressed;
	}
}