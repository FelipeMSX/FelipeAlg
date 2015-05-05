package PAA;

/**
 * Created by Felipe on 04/05/2015.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class QuickSort {

    static StringBuilder steps = new StringBuilder();// Armazena toda os passos que serão gravados no arquivo de saída.
    static short stepsPadrao;
    static short stepsMediana;
    public static void main(String[] args) throws IOException
    {
        if(args.length !=0)
        {
            loadFileData(args[0]);
            prepareSteps();
            writeSteps(args[1]);
        }
    }

    public static void prepareSteps()
    {
//        for(int i =0 ; i < countReg -1; i++)
//        {
//            steps.append(ConteinersSaida[i].output);
//        }
//        steps.append(ConteinersSaida[countReg - 1].output.replace("\n", ""));
    }
    public static void writeSteps(String filePath) throws IOException
    {
        FileWriter fw = new FileWriter( filePath );
        fw.write(steps.toString());
        fw.close();
    }

    public static void loadFileData(String filePath) throws FileNotFoundException
    {
        // Contem o caminho do arquivo
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        try {
            //Lê os contêiners cadastrados
            int qtdVectors= Integer.parseInt(br.readLine());
            while (br.ready()) {
                int qtdNumbers = Integer.parseInt(br.readLine());
                int vector[] = new int[qtdNumbers];
                examineLine(vector,br.readLine());
                /*
                stepsPadrao =0;
                quicksortPadrao(vector, 0, vector.length - 1);
                */
                stepsMediana = 0;
                quicksortMediana(vector,0,vector.length-1);
                System.out.println(stepsMediana);
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void swapItem(int[] input, int positionX, int positionY)
    {
        int temp = input[positionX];
        input[positionX] = input[positionY];
        input[positionY] = temp;
    }

    public static void examineLine(int[] vector,String line){

        String[] analise 	= line.split(" ");
        for (int i = 0; i < analise.length; i++)
        {
        vector[i] = Integer.parseInt(analise[i]);

        }
    }

    // Responsável por criar cada passo da função.
    public static void createStep()
    {

    }

    private static void quicksortPadrao(int input[], int inicio, int fim) {
        stepsPadrao++;// Número de chamadas
        if(inicio < fim) {
            int pivo = particionarPadrao(input, inicio, fim);
            quicksortPadrao(input, inicio, pivo - 1);
            quicksortPadrao(input, pivo + 1, fim);

        }
    }


    //PP : particionar padrão
    public static int particionarPadrao(int input[], int init, int end)
    {
        int pivo = input[end]; // pivô escolhido no final
        int i = init - 1, j;
        for(j = init; j < end; j++) {
            if(input[j] <= pivo) {
                i = i + 1;
                stepsPadrao++;
                swapItem(input,i,j);
            }
        }
        stepsPadrao++;
        swapItem(input,i+1,end);
        return i + 1;
    }


    private static void quicksortMediana(int input[], int inicio, int fim) {
        stepsMediana++;// Número de chamadas
        if(inicio < fim) {
            int pivo = particionarMediana(input, inicio, fim);
            quicksortMediana(input, inicio, pivo - 1);
            quicksortMediana(input, pivo + 1, fim);

        }
    }

    //PM particionar por mediana de 3
    public static int particionarMediana(int input[], int init, int end)
    {
        int pivo = calcPivoMediana(input,(end-init)) ;
        int i = init - 1, j;
        for(j = init; j < end; j++) {
            if(input[j] <= pivo) {
                i = i + 1;
                stepsMediana++;
                swapItem(input,i,j);
            }
        }
        stepsMediana++;
        swapItem(input,i+1,end);
        return i + 1;
    }

    //PA particionar por pivô aleatório
    public static void particionarPivoAleatorio()
    {

    }

    private static void quicksortHoarePadrao(int input[], int inicio, int fim) {
        stepsMediana++;// Número de chamadas
        if(inicio < fim) {
            int pivo = particionarMediana(input, inicio, fim);
            quicksortHoarePadrao(input, inicio, pivo - 1);
            quicksortHoarePadrao(input, pivo + 1, fim);

        }
    }

    //HP hoare padrão
    public static void particionarHoarePadrao()
    {

    }

    //HM hoare por mediana de 3
    public static void particionarHoareMediana()
    {

    }

    //HA e hoare por pivô aleatório
    public static void particionarHoarePivoAleatorio()
    {

    }


    public static int calcPivoMediana(int vector[], int n)
    {

        int v1 = vector[n/4];
        int v2 = vector[n/2];
        int v3 = vector[3*n/4];
        int[] temp = new int[3];
        temp[0] = v1;
        temp[1] = v2;
        temp[2] = v3;
        quicksortPadrao(temp, 0, 2);
        return ( temp[1]);
    }

    public static int calcPivoAleatorio(int vector[], int init)
    {
        return vector[init + Math.abs(vector[init]) % vector.length ];
    }

}
