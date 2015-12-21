package PAA;


import util.ElapsedTime;

import java.io.*;
import java.text.DecimalFormat;


/**
 * Created by Felipe Morais on 05/11/2015.
 */
public class Compressao {

	static StringBuilder steps = new StringBuilder();   // Armazena toda os passos que ser�o gravados no arquivo de sa�da.
	static final short RLE_MAX_COUNT = (short)255;
	static int RLE_Auxiliary;
	static int Huffman_Auxiliary;
	static short HuffmanCount = 0;
	static int TOTAL;

	public static void main(String args[]) throws IOException {

		if(args.length !=0) {
			readInputFile(args[0],args[1]);
		}
	}


	public static void RLEtoOutput(short[] result,int originalSize, int resultSize )
	{
		int size = result.length-1;
		//Calcular porcentagem

		double RLE = (double)resultSize/(double)originalSize*100;

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
	public static String[] RunLengthEncoding(String[] input)
	{
		int size = input.length;
		String[] resultInput = new String[input.length*2];
		int resultCount = 0;
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < size; i++) {
			short runLength = (short)1;
			while (i + 1 < size && input[(i)].compareTo(input[i+1]) == 0  && runLength != RLE_MAX_COUNT) {
				runLength++;
				i++;
			}

			resultInput[resultCount++] = Short.toString(runLength);
			resultInput[resultCount++] = input[i];

			RLE_Auxiliary += 2;
		}
		return resultInput;
	}

	public static StringBuilder Huffman(String input[])
	{

		ElapsedTime et = new ElapsedTime();
		//Montar histograma
		Histograma[] countInput = CountInput(input);
		PriorityQueue priorityQueue = new PriorityQueue(80);
		BinaryTree[] leafs = new BinaryTree[HuffmanCount];
		int countLeaf= 0;
		for(short i = 0; i < 256 ; i++) {
			if(countInput[i]!= null) {
				//Inserir na fila de prioridade m�nima
				BinaryTree node = new BinaryTree();
				node.simb = countInput[i].code;
				node.freq = countInput[i].count;
				priorityQueue.insert(node);
				//Lita dos n�s folhas
				leafs[countLeaf++] = node;
			}

		}


		//montar �rvore
		BinaryTree BiTree = createTree(priorityQueue);

		//Montar Tabela
		TableCod[] tc = createTableToCompress(BiTree,leafs);

		//Compress�o
		StringBuilder result = compress(tc,input);

		return result;
	}


	public static Histograma[] CountInput(String input[])
	{
		int size = input.length;
		Histograma[] hs = new Histograma[(short)256];

		for(int i =0; i < size;i++) {
			int value = Integer.parseInt(input[i],16);
			if(hs[value] != null){
				hs[value].count++;
			}else{
				Histograma h = new Histograma(input[i],(short)1);
				hs[value] = h ;
				HuffmanCount++;
			}
		}

		return hs;
	}

	public static class Histograma{
		String code;
		short count;

		public Histograma(String code, short count){
			this.code = code;
			this.count = count;
		}
	}

	private static BinaryTree createTree(PriorityQueue priorityQueue)
	{
		while(priorityQueue.size != 1)
		{
			BinaryTree nodeLeft     = priorityQueue.remove();
			BinaryTree nodeRight    = priorityQueue.remove();
			BinaryTree nodeFather   = new BinaryTree();
			nodeFather.simb         = null;
			nodeFather.freq         = nodeLeft.freq + nodeRight.freq;
			nodeFather.left         = nodeLeft;
			nodeFather.right        = nodeRight;
			nodeLeft.father 		= nodeFather;
			nodeRight.father 		= nodeFather;
			priorityQueue.insert(nodeFather);
		}
		return priorityQueue.remove();
	}


	private static TableCod[] createTableToCompress(BinaryTree biTree,BinaryTree[] leafs)
	{
		TableCod[] tableCod = new TableCod[256];
		int size = HuffmanCount;
		for(int i = 0; i < size;i++){
			TableCod tCode = new TableCod();
			tCode.code = leafs[i].simb;
			StringBuilder code = new StringBuilder();
			BinaryTree leaf = leafs[i];
			BinaryTree leafAnterior = null;
			if(leafs.length == 1){
				code.insert(0,0);
			}else{

				while(leaf.father != null){
					leafAnterior = leaf;
					leaf = leaf.father;
					if(leaf.left.equals(leafAnterior)){
						code.insert(0,0);
					}
					else{
						code.insert(0,1);
					}
				}
			}
			tCode.valueCompressed = code.toString();
			tableCod[Short.parseShort(tCode.code,16)] = tCode;

		}
		return tableCod;
	}

	private static StringBuilder compress(TableCod[] tc,String[] input)
	{
		StringBuilder result = new StringBuilder();
		int size = input.length;
		for(int i = 0; i < size; i ++)
		{
			result.append(tc[Short.parseShort(input[i],16)].valueCompressed);
		}

		Huffman_Auxiliary = (int)Math.ceil((double)result.length()/(double)8);
		return result;
	}



	public static String formatOutput(String disease, int percentage)
	{
		//String.format("%s: %d%%","H1Z1",123));
		return disease+": "+percentage+'%';
	}

	public static void readInputFile(String filePath,String destinePath) throws IOException
	{
		// Contem o caminho do arquivo
		ElapsedTime et = new ElapsedTime();
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter( destinePath );

		try
		{
			TOTAL               = Integer.parseInt(br.readLine());
			int count = 0;
			while (br.ready())
			{
				Huffman_Auxiliary = 0;
				RLE_Auxiliary = 0;
				HuffmanCount = 0;
				// L� uma linha
				String[] inputValues = examineLine(br.readLine());
				//calcula RLE
				String[] resultRLE = RunLengthEncoding(inputValues);

				//Calcula Huffman
				StringBuilder resultHuffman = Huffman(inputValues);
				//Examina qual � o maior
				DecimalFormat df = new DecimalFormat("#.00");
				double huffmanimpact = (100*((double)Huffman_Auxiliary/(double)inputValues.length));
				double RLEImpact     = (100*((double)RLE_Auxiliary/(double)inputValues.length)+0.005d);

				if(huffmanimpact < RLEImpact){
					fw.write(gravarHuffman(resultHuffman,huffmanimpact,count).toString()+"\n");
				}
				else if(huffmanimpact > RLEImpact){

				}else{
					fw.write(gravarHuffman(resultHuffman,huffmanimpact,count).toString()+"\n");
				}
				//grava o maior ou o empate.
				count++;
			}

			br.close();
			fr.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Ler Arquivo:"+et.calculateElapsedTimeInMilliSeconds());
	}

	private static  StringBuilder gravarHuffman(StringBuilder result,double porcentagem,int listCount){
		StringBuilder step = new StringBuilder();
		step.append(listCount+": [HUF "+porcentagem+"] ");
		int count = 0;
		int size = result.length();
		int quebrar = 0;
		while(count != size){
			count++;
			if(quebrar == 7){
				String binaryStr = result.substring(count-7,count);
				int decimal = Integer.parseInt(binaryStr,2);
				String hexStrTemp = Integer.toString(decimal,16);
				String hexStr = (hexStrTemp.length() == 1) ? "0x0" + hexStrTemp.toUpperCase()+" " :"0x" + hexStrTemp.toUpperCase()+" ";
				step.append(hexStr);
				quebrar = 0;
			}

			quebrar++;
		}
		if(quebrar != 0){
			String binaryStr = result.substring(count-quebrar,count);
			int decimal = Integer.parseInt(binaryStr,2);
			String hexStrTemp = Integer.toString(decimal,16);
			String hexStr = (hexStrTemp.length() == 1) ? "0x0" + hexStrTemp.toUpperCase()+" " :"0x" + hexStrTemp.toUpperCase()+" ";
			step.append(hexStr);
		}
		return step;
	}

	public static String[] examineLine(String line){
		int count = line.indexOf(" ");
		int len = Integer.parseInt(line.substring(0, count));
		String[] data = new String[len];
		int cursor = count+3;//Posi��o inicial;
		for(int i = 0; i < len; i++){
			data[i] =  line.substring(cursor,cursor+2);
			cursor+=5;
		}
		return data;
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

	public static class BinaryTree {
		public int freq;
		String simb;
		BinaryTree father;
		BinaryTree right;
		BinaryTree left;
	}

	public static class TableCod
	{
		String code;
		String valueCompressed;
	}


	public static short hexToString(String hex) {
		switch(hex) {
			case "00":
				return 0;
			case "01":
				return 1;
			case "02":
				return 2;
			case "03":
				return 3;
			case "04":
				return 4;
			case "05":
				return 5;
			case "06":
				return 6;
			case "07":
				return 7;
			case "08":
				return 8;
			case "09":
				return 9;
			case "0A":
				return 10;
			case "0B":
				return 11;
			case "0C":
				return 12;
			case "0D":
				return 13;
			case "0E":
				return 14;
			case "0F":
				return 15;
			case "10":
				return 16;
			case "11":
				return 17;
			case "12":
				return 18;
			case "13":
				return 19;
			case "14":
				return 20;
			case "15":
				return 21;
			case "16":
				return 22;
			case "17":
				return 23;
			case "18":
				return 24;
			case "19":
				return 25;
			case "1A":
				return 26;
			case "1B":
				return 27;
			case "1C":
				return 28;
			case "1D":
				return 29;
			case "1E":
				return 30;
			case "1F":
				return 31;
			case "20":
				return 32;
			case "21":
				return 33;
			case "22":
				return 34;
			case "23":
				return 35;
			case "24":
				return 36;
			case "25":
				return 37;
			case "26":
				return 38;
			case "27":
				return 39;
			case "28":
				return 40;
			case "29":
				return 41;
			case "2A":
				return 42;
			case "2B":
				return 43;
			case "2C":
				return 44;
			case "2D":
				return 45;
			case "2E":
				return 46;
			case "2F":
				return 47;
			case "30":
				return 48;
			case "31":
				return 49;
			case "32":
				return 50;
			case "33":
				return 51;
			case "34":
				return 52;
			case "35":
				return 53;
			case "36":
				return 54;
			case "37":
				return 55;
			case "38":
				return 56;
			case "39":
				return 57;
			case "3A":
				return 58;
			case "3B":
				return 59;
			case "3C":
				return 60;
			case "3D":
				return 61;
			case "3E":
				return 62;
			case "3F":
				return 63;
			case "40":
				return 64;
			case "41":
				return 65;
			case "42":
				return 66;
			case "43":
				return 67;
			case "44":
				return 68;
			case "45":
				return 69;
			case "46":
				return 70;
			case "47":
				return 71;
			case "48":
				return 72;
			case "49":
				return 73;
			case "4A":
				return 74;
			case "4B":
				return 75;
			case "4C":
				return 76;
			case "4D":
				return 77;
			case "4E":
				return 78;
			case "4F":
				return 79;
			case "50":
				return 80;
			case "51":
				return 81;
			case "52":
				return 82;
			case "53":
				return 83;
			case "54":
				return 84;
			case "55":
				return 85;
			case "56":
				return 86;
			case "57":
				return 87;
			case "58":
				return 88;
			case "59":
				return 89;
			case "5A":
				return 90;
			case "5B":
				return 91;
			case "5C":
				return 92;
			case "5D":
				return 93;
			case "5E":
				return 94;
			case "5F":
				return 95;
			case "60":
				return 96;
			case "61":
				return 97;
			case "62":
				return 98;
			case "63":
				return 99;
			case "64":
				return 100;
			case "65":
				return 101;
			case "66":
				return 102;
			case "67":
				return 103;
			case "68":
				return 104;
			case "69":
				return 105;
			case "6A":
				return 106;
			case "6B":
				return 107;
			case "6C":
				return 108;
			case "6D":
				return 109;
			case "6E":
				return 110;
			case "6F":
				return 111;
			case "70":
				return 112;
			case "71":
				return 113;
			case "72":
				return 114;
			case "73":
				return 115;
			case "74":
				return 116;
			case "75":
				return 117;
			case "76":
				return 118;
			case "77":
				return 119;
			case "78":
				return 120;
			case "79":
				return 121;
			case "7A":
				return 122;
			case "7B":
				return 123;
			case "7C":
				return 124;
			case "7D":
				return 125;
			case "7E":
				return 126;
			case "7F":
				return 127;
			case "80":
				return 128;
			case "81":
				return 129;
			case "82":
				return 130;
			case "83":
				return 131;
			case "84":
				return 132;
			case "85":
				return 133;
			case "86":
				return 134;
			case "87":
				return 135;
			case "88":
				return 136;
			case "89":
				return 137;
			case "8A":
				return 138;
			case "8B":
				return 139;
			case "8C":
				return 140;
			case "8D":
				return 141;
			case "8E":
				return 142;
			case "8F":
				return 143;
			case "90":
				return 144;
			case "91":
				return 145;
			case "92":
				return 146;
			case "93":
				return 147;
			case "94":
				return 148;
			case "95":
				return 149;
			case "96":
				return 150;
			case "97":
				return 151;
			case "98":
				return 152;
			case "99":
				return 153;
			case "9A":
				return 154;
			case "9B":
				return 155;
			case "9C":
				return 156;
			case "9D":
				return 157;
			case "9E":
				return 158;
			case "9F":
				return 159;
			case "A0":
				return 160;
			case "A1":
				return 161;
			case "A2":
				return 162;
			case "A3":
				return 163;
			case "A4":
				return 164;
			case "A5":
				return 165;
			case "A6":
				return 166;
			case "A7":
				return 167;
			case "A8":
				return 168;
			case "A9":
				return 169;
			case "AA":
				return 170;
			case "AB":
				return 171;
			case "AC":
				return 172;
			case "AD":
				return 173;
			case "AE":
				return 174;
			case "AF":
				return 175;
			case "B0":
				return 176;
			case "B1":
				return 177;
			case "B2":
				return 178;
			case "B3":
				return 179;
			case "B4":
				return 180;
			case "B5":
				return 181;
			case "B6":
				return 182;
			case "B7":
				return 183;
			case "B8":
				return 184;
			case "B9":
				return 185;
			case "BA":
				return 186;
			case "BB":
				return 187;
			case "BC":
				return 188;
			case "BD":
				return 189;
			case "BE":
				return 190;
			case "BF":
				return 191;
			case "C0":
				return 192;
			case "C1":
				return 193;
			case "C2":
				return 194;
			case "C3":
				return 195;
			case "C4":
				return 196;
			case "C5":
				return 197;
			case "C6":
				return 198;
			case "C7":
				return 199;
			case "C8":
				return 200;
			case "C9":
				return 201;
			case "CA":
				return 202;
			case "CB":
				return 203;
			case "CC":
				return 204;
			case "CD":
				return 205;
			case "CE":
				return 206;
			case "CF":
				return 207;
			case "D0":
				return 208;
			case "D1":
				return 209;
			case "D2":
				return 210;
			case "D3":
				return 211;
			case "D4":
				return 212;
			case "D5":
				return 213;
			case "D6":
				return 214;
			case "D7":
				return 215;
			case "D8":
				return 216;
			case "D9":
				return 217;
			case "DA":
				return 218;
			case "DB":
				return 219;
			case "DC":
				return 220;
			case "DD":
				return 221;
			case "DE":
				return 222;
			case "DF":
				return 223;
			case "E0":
				return 224;
			case "E1":
				return 225;
			case "E2":
				return 226;
			case "E3":
				return 227;
			case "E4":
				return 228;
			case "E5":
				return 229;
			case "E6":
				return 230;
			case "E7":
				return 231;
			case "E8":
				return 232;
			case "E9":
				return 233;
			case "EA":
				return 234;
			case "EB":
				return 235;
			case "EC":
				return 236;
			case "ED":
				return 237;
			case "EE":
				return 238;
			case "EF":
				return 239;
			case "F0":
				return 240;
			case "F1":
				return 241;
			case "F2":
				return 242;
			case "F3":
				return 243;
			case "F4":
				return 244;
			case "F5":
				return 245;
			case "F6":
				return 246;
			case "F7":
				return 247;
			case "F8":
				return 248;
			case "F9":
				return 249;
			case "FA":
				return 250;
			case "FB":
				return 251;
			case "FC":
				return 252;
			case "FD":
				return 253;
			case "FE":
				return 254;
			case "FF":
				return 255;
			default:
				return -1;
		}
	}

}