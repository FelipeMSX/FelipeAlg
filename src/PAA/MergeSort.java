package PAA;

/**
 * Created by Felipe on 29/04/2015.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;


public class MergeSort {


    static int i = 0;
    static int j = 0;
    static int k = 0;
    static HashMap<String, FileData> ConteinersCadastro;
    static FileData[] ConteinersAnalisar;
    static FileData[] ConteinersSaida;
    static StringBuilder steps = new StringBuilder();
    static final String CPNJ_TOKE = "<->";
    static FileData dataM;

    public static void main(String[] args) throws IOException {

        if(args.length !=0){
            loadFileData(args[0]);
            System.out.println();

            //mergesort(ConteinersAnalisar);
//			System.out.println(fileInfo[0].produtoCod);
//			FileWriter fw = new FileWriter(args[1]);
//			fw.write(steps.toString());
//			fw.close();
        }
    }

    public static void loadFileData(String filePath) throws FileNotFoundException
    {
        // Contem o caminho do arquivo
        FileReader fr = new FileReader(filePath);

        if (fr != null){
            BufferedReader br = new BufferedReader( fr );
            try {
                //Lê os contêiners cadastrados
                int fileSize = Integer.parseInt(br.readLine());
                ConteinersCadastro = new HashMap<String,FileData>(fileSize);
                int count = 0;
                while( br.ready() && count < fileSize){
                    FileData data = analiseLine(br.readLine());
                    ConteinersCadastro.put(data.ID, data);

                    count++;
                }

                //Lista de Contêiners com erros
                fileSize = Integer.parseInt(br.readLine());
                ConteinersAnalisar = new FileData[fileSize];
                count = 0;
                while( br.ready() && count < fileSize){
                    ConteinersAnalisar[count] =  analiseLine(br.readLine());

                    count++;
                }

                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static FileData analiseLine(String line){
        FileData data 		= new FileData();
        String[] analise 	= line.split(" ");
        data.ID 			= analise[0];
        data.CNPJ 			= analise[1];
        data.peso 			= Integer.parseInt(analise[2]);

        return data;

    }

    //Prepara a função antes de ser chamada
    public static void mergesort(FileData input[]){
        ConteinersSaida = new FileData[input.length];
        merge(input, 0, input.length-1);
    }

    private static void merge(FileData input[], int ini, int fim) {
        if(ini < fim) {
            int meio = ini + (fim - ini) / 2;
            merge(input, ini, meio);
            merge(input, meio + 1, fim);
            intercalar(input, ini, meio, fim);
        }
    }

    private static void intercalar(FileData input[], int ini,int meio, int fim) {
        i = ini;
        j = meio+1;
        k = ini; // controlador do vetor output, a cada adição no vetor, é incrementado

        while (i <= meio || j <= fim ){
            dataM = ConteinersCadastro.get(input[i]);
            // Se já passou do fim, significa que não possui mais elementos do meio pro fim para inserir no vetor
            if(j > fim)
            {
                ConteinersSaida[k++] = input[i++];
            }
            else
                // Se i > meio, significa que não existe mais elementos do inicio ao fim para comparar, agora é só adicioar do meio +1 ao fim.
                if(i > meio)
                {
                    ConteinersSaida[k++] = input[j++];
                }
                else
                if(!input[i].CNPJ.equals(dataM.CNPJ))
                {
                    criarPasso(input[i].ID, dataM.CNPJ, input[i].CNPJ);
                }
            // Quando os proprietários tiverem COD iguais, é necessário decidir pela prioridade


        }
        for(int w = ini ; w <= fim; w++){
            input[w] = ConteinersSaida[w];
        }

    }

    // Responsável por criar cada passo da função.
    public static void criarPasso(String ID,String CNPJCorreto, String CNPJErrado)
    {
        steps.append(ID+": " + CNPJCorreto + CPNJ_TOKE + CNPJErrado+"\n");
    }

    // Responsável por criar cada passo da função.
    public static void criarPasso(String ID ,int pesoCorreto, int pesoErrado, float percentual)
    {
        int diferenca = Math.abs(pesoCorreto - pesoCorreto);
        steps.append(ID+": "+diferenca+"kg"+"("+(int)(percentual*100)+"%)"+"\n");
    }

    public boolean CNPJDiferente(String cnpj1, String cpnj2)
    {
        return (cnpj1.equals(cpnj2));
    }

    public static float calcularDezPorcento(int numerador, int denomidador)
    {
        int diferenca = Math.abs(numerador - denomidador);
        return (diferenca/(float)numerador);
    }

    public static boolean analisarDezPorcento(float num)
    {
        return (num > 0.1) ? true : false;
    }

    public static class FileData
    {
        private String ID;
        private String CNPJ;
        private int peso;
    }

}
