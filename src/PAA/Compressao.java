package PAA;


import java.io.*;


/**
 * Created by Felipe Morais on 05/11/2015.
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
		int i = 1;
		while(i < TOTAL) {
			RLE_Auxiliary = 0;
			//System.out.println(RunLengthEncoding(inputValues[i].decodeValue));
		//	System.out.println((double)RLE_Auxiliary/(double)inputValues[i].decodeValue.length);
			Huffman(inputValues[i].decodeValue);

			inputValues[i].decodeValue = null;
			i++;
		}
	}

	public static String RunLengthEncoding(short[] input)
	{
		int size = input.length;
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < size; i++) {
			short runLength = (short)1;
			while (i + 1 < size && input[(i)] == input[i+1] && runLength != RLE_MAX_COUNT) {
				runLength++;
				i++;
			}
			result.append(String.format("0x%02X", runLength));
			result.append(String.format(" 0x%02X ", input[i]));
			RLE_Auxiliary += 2;
		}
		result.deleteCharAt(result.length() - 1);
		return result.toString();
	}

	public static String Huffman(short input[])
	{
		//StringBuilder result = new StringBuilder();
		//Montar histograma
		short[] countInput = CountInput(input);
		PriorityQueue priorityQueue = new PriorityQueue(40);
		for(short i =0; i < 255 ; i++) {
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
		createHuffmanTable(BiTree);
		//Compressão
		return null;
	}


	public static void createHuffmanTable(BinaryTree biTree) {
		//Encontrar todos os nós folhas;
      //  StringBuilder nodeValue =  new StringBuilder();
        BinaryTree root = biTree;
        BinaryTree left = biTree.left;
        while(left != root){

        }

	}
	public static short[] CountInput(short input[]) {
		int size = input.length;
		short[] countInput = new short[255];
		for(int i =0; i < size;i++)
			countInput[input[i]]++  ;

		return countInput;
	}

	private static BinaryTree createTree(PriorityQueue priorityQueue) {
		while(priorityQueue.size != 1)
		{
			BinaryTree nodeLeft = priorityQueue.remove();
			BinaryTree nodeRight = priorityQueue.remove();
			BinaryTree nodeFather = new BinaryTree();
			nodeFather.simb = TOKEN_NULL;
			nodeFather.freq = nodeLeft.freq + nodeRight.freq;
			nodeFather.left = nodeLeft;
			nodeFather.right = nodeRight;
			priorityQueue.insert(nodeFather);
		}
		return priorityQueue.remove();
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


		boolean isLeaf() {
			return right == null && left == null;
		}
	}


	//Table de Compressão de Huffman
	public static class HTable
	{
		short symbol;
		short compressCode;
	}

}
