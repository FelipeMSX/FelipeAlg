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
    static short stepsAleatorio;
    static short stepsHoarePadrao;
    static short stepsHoareMediana;
    static short stepsHoareAleatorio;
    public static void main(String[] args) throws IOException
    {
        if(args.length !=0)
        {
            loadFileData(args[0]);
            writeSteps(args[1]);
        }
    }

    public static void prepareSteps(int vectorOrdem, int qtdNumbers,int qtdVectorTotal)
    {
        int[] Ordem = new int[6];
        Ordem[0] = stepsPadrao;
        Ordem[1] = stepsMediana;
        Ordem[2] = stepsAleatorio;
        Ordem[3] = stepsHoarePadrao;
        Ordem[4] = stepsHoareMediana;
        Ordem[5] = stepsHoareAleatorio;
        short temp = stepsPadrao;
        quicksortPadrao(Ordem,0,5);

        stepsPadrao = temp;
        steps.append(vectorOrdem+": N("+qtdNumbers +") ");
        for(int i =0; i < 6; i++)
        {
           if(Ordem[i] == stepsPadrao  && stepsPadrao != -1) {
               steps.append("PP(" + stepsPadrao + ") ");
               stepsPadrao = -1;
           }
           else
           if(Ordem[i] == stepsMediana && stepsMediana != -1) {
               steps.append("PM(" + stepsMediana + ") ");
               stepsMediana = -1;
           }
           else
           if(Ordem[i] == stepsAleatorio &&  stepsAleatorio != -1) {
               steps.append("PA(" + stepsAleatorio + ") ");
               stepsAleatorio = -1;
           }
           else
           if(Ordem[i] == stepsHoarePadrao  && stepsHoarePadrao != -1) {
               steps.append("HP(" + stepsHoarePadrao + ") ");
               stepsHoarePadrao = -1;
           }
           else
           if(Ordem[i] == stepsHoareMediana && stepsHoareMediana != -1) {
               steps.append("HM(" + stepsHoareMediana + ") ");
            stepsHoareMediana = -1;
           }
           else
           if(Ordem[i] == stepsHoareAleatorio && stepsHoareAleatorio != -1) {
               steps.append("HA(" + stepsHoareAleatorio + ") ");
               stepsHoareAleatorio = -1;
           }

        }

        if(qtdVectorTotal != vectorOrdem)
            steps.append("\n");

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
            int count = 0;
            while (br.ready()) {
                int qtdNumbers = Integer.parseInt(br.readLine());
                int vector[] = new int[qtdNumbers];
                examineLine(vector, br.readLine());

                gerarEstatistica(vector, qtdNumbers - 1);
                prepareSteps(count, qtdNumbers,qtdVectors);
                count++;
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void gerarEstatistica(int[] input, int n)
    {
        int[] tempCopia = input.clone();

        stepsPadrao =0;
        quicksortPadrao(input, 0, n);
        input = tempCopia.clone();

        /*
        stepsMediana = 0;
        quicksortMediana(input,0,n);
        input = tempCopia.clone();

        stepsAleatorio = 0;
        quicksortAleatorio(input,0,n);
        input = tempCopia.clone();
        */
        stepsHoarePadrao = 0;
        quicksortHoare(input, 0, n);
        input = tempCopia.clone();

        /*
        stepsHoareMediana = 0;
        quicksortHoareMediana(input,0,n);
        input = tempCopia.clone();

        stepsHoareAleatorio = 0;
        quicksortHoareAleatorio(input,0,n);
        */


    }

    public static void swapItem(int[] input, int positionX, int positionY)
    {
        int temp = input[positionX];
        input[positionX] = input[positionY];
        input[positionY] = temp;
    }

    public static void examineLine(int[] vector,String line)
    {
        String[] analise 	= line.split(" ");
        for (int i = 0; i < analise.length; i++)
            vector[i] = Integer.parseInt(analise[i]);

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

    private static void quicksortAleatorio(int input[], int inicio, int fim) {
        stepsAleatorio++;// Número de chamadas
        if(inicio < fim) {
            int pivo = particionarPivoAleatorio(input, inicio, fim);
            quicksortAleatorio(input, inicio, pivo - 1);
            quicksortAleatorio(input, pivo + 1, fim);
        }
    }
    //PA particionar por pivô aleatório
    public static int particionarPivoAleatorio(int input[], int init, int end)
    {
        int pivo = calcPivoMediana(input,(end-init)) ;
        int i = init - 1, j;
        for(j = init; j < end; j++) {
            if(input[j] <= pivo) {
                i = i + 1;
                stepsAleatorio++;
                swapItem(input,i,j);
            }
        }
        stepsAleatorio++;
        swapItem(input,i+1,end);
        return i + 1;
    }

    private static void quicksortHoare(int input[], int inicio, int fim) {
        stepsHoarePadrao++;// Número de chamadas
        if(inicio < fim) {
            int pivo = particionarHoare(input, inicio, fim);
            quicksortHoare(input, inicio, pivo);
            quicksortHoare(input, pivo + 1, fim);

        }
    }

    //HP hoare padrão
    public static int particionarHoare(int input[], int inicio, int fim)
    {
        int pivo = input[inicio];
        int i = inicio;
        int j = fim;
        while(i < j) {
            while(j > i && input[j] >= pivo)
                j--;
            while(i < j && input[i] < pivo)
                i++;
            if(i < j) {
                stepsHoarePadrao++;
                swapItem(input, i, j);
            }
        }
        return j;
    }

    private static void quicksortHoareMediana(int input[], int inicio, int fim) {
        stepsHoareMediana++;// Número de chamadas
        if(inicio < fim) {
            int pivo = particionarHoareMediana(input, inicio, fim);
            quicksortHoareMediana(input, inicio, pivo);
            quicksortHoareMediana(input, pivo + 1, fim);

        }
    }

    //HM hoare por mediana de 3
    public static int particionarHoareMediana(int input[], int inicio, int fim)
    {
        int pivo = calcPivoMediana(input,0);
        int i = inicio;
        int j = fim;
        while(i < j) {
            while(j > i && input[j] >= pivo)
                j--;
            while(i < j && input[i] < pivo)
                i++;
            if(i < j) {
                stepsHoareMediana++;
                swapItem(input, i, j);
            }
        }
        return j;
    }

    private static void quicksortHoareAleatorio(int input[], int inicio, int fim) {
        stepsHoareAleatorio++;// Número de chamadas
        if(inicio < fim) {
            int pivo = particionarHoarePivoAleatorio(input, inicio, fim);
            quicksortHoareAleatorio(input, inicio, pivo);
            quicksortHoareAleatorio(input, pivo + 1, fim);

        }
    }
    //HA e hoare por pivô aleatório
    public static int particionarHoarePivoAleatorio(int input[], int inicio, int fim)
    {
        int pivo = calcPivoAleatorio(input,0);
        int i = inicio;
        int j = fim;
        while(i < j) {
            while(j > i && input[j] >= pivo)
                j--;
            while(i < j && input[i] < pivo)
                i++;
            if(i < j) {
                stepsHoareAleatorio++;
                swapItem(input, i, j);
            }
        }
        return j;
    }

    public static int calcPivoMediana(int vector[], int n)
    {
        int v1 = vector[n/4];
        int v2 = vector[n/2];
        int v3 = vector[3*n/4];

        return ( 0);
    }

    public static int calcPivoAleatorio(int vector[], int init)
    {
        return vector[init + Math.abs(vector[init]) % vector.length ];
    }
}
