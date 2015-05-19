package PAA;

/**
 * Created by Felipe on 29/04/2015.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class MergeSort {

    static int i = 0;
    static int j = 0;
    static int k = 0;
    static int countReg = 0;// quantidade de registros que serão processados no merge.
    static StringBuilder steps = new StringBuilder();// Armazena toda os passos que serão gravados no arquivo de saída.
    static FileData[] ConteinersOrdenar; // Vetor que contém todos os registros a ordenar.
    static FileData[] ConteinersSaida; // Vetor que armazena os registros armazenado.
    static final String CPNJ_TOKE = "<->";

    public static void main(String[] args) throws IOException {

        if(args.length !=0){
            loadFileData(args[0]);
            mergesort(ConteinersOrdenar);
            prepareSteps();
            writeSteps(args[1]);
        }
    }

    public static void prepareSteps()
    {
        for(int i =0 ; i < countReg -1; i++)
        {
            steps.append(ConteinersSaida[i].output);
        }
        steps.append(ConteinersSaida[countReg - 1].output.replace("\n", ""));
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

        if (fr != null)
        {
            BufferedReader br = new BufferedReader(fr);
            try
            {
                //Lê os contêiners cadastrados
                int fileSize = Integer.parseInt(br.readLine());
                FileData[] ConteinersCadastrados = new FileData[fileSize];
                int count = 0;
                while (br.ready() && count < fileSize)
                {
                    ConteinersCadastrados[count] = examineLine(br.readLine());
                    count++;
                }

                //Lista de Contêiners com erros
                int fileSize2 = Integer.parseInt(br.readLine());
                int controladorF = fileSize-1;
                ConteinersOrdenar = new FileData[fileSize2];
                while (br.ready())
                {
                    FileData data = examineLine(br.readLine());

                    FileData dataAchado = null;
                    for (int i = 0; i < controladorF; i++)
                    {
                        if(data.ID.equals(ConteinersCadastrados[i].ID))
                        {
                            dataAchado = ConteinersCadastrados[i];
                            ConteinersCadastrados[i] = ConteinersCadastrados[controladorF];
                            controladorF--;
                            i = controladorF;

                        }
                    }

                    if (!data.CNPJ.equals(dataAchado.CNPJ))
                    {
                        data.mergePriority = 1000;
                        data.output = createStep(data.ID, dataAchado.CNPJ, data.CNPJ);
                        ConteinersOrdenar[countReg] = data;
                        countReg++;
                    } else
                    {
                        float pesoDiferancaPercentual = calculateTenPercent(data.weight, dataAchado.weight);
                        if (AnaliseTenPercent(pesoDiferancaPercentual)) {
                            data.mergePriority = (short)Math.round(pesoDiferancaPercentual*100) ;
                            data.output = createStep(data.ID, dataAchado.weight, data.weight, pesoDiferancaPercentual);
                            ConteinersOrdenar[countReg] = data;
                            countReg++;
                        }
                    }
                }
                System.out.println("Fim");
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
        data.ID 			= analise[0];
        data.CNPJ 			= analise[1];
        data.weight = Integer.parseInt(analise[2]);

        return data;

    }

    //Prepara a função antes de ser chamada
    public static void mergesort(FileData input[]){
        ConteinersSaida = new FileData[countReg];
        merge(input, 0, countReg -1);
    }

    private static void merge(FileData input[], int ini, int end) {
        if(ini < end) {
            int meio = ini + (end - ini) / 2;
            merge(input, ini, meio);
            merge(input, meio + 1, end);
            intercalate(input, ini, meio, end);
        }
    }

    private static void intercalate(FileData input[], int init, int middle, int end) {
        i = init;
        j = middle+1;
        k = init; // controlador do vetor output, a cada adição no vetor, é incrementado

        while (i <= middle || j <= end ){

            // Se já passou do fim, significa que não possui mais elementos do meio pro fim para inserir no vetor
            if(j > end)
            {
               ConteinersSaida[k++] = input[i++];
            }
            else
                // Se i > meio, significa que não existe mais elementos do inicio ao fim para comparar, agora é só adicioar do meio +1 ao fim.
                if(i > middle)
                {
                    ConteinersSaida[k++] = input[j++];
                }
                else
                if(input[i].mergePriority > input[j].mergePriority)
                {
                    ConteinersSaida[k++] = input[i++];
                }else
                {
                    ConteinersSaida[k++] = input[j++];

                }
        }
        for(int w = init ; w <= end; w++){
            input[w] = ConteinersSaida[w];
        }

    }

    // Responsável por criar cada passo da função.
    public static String createStep(String ID, String correctCNPJ, String wrongCNPJ)
    {
        return (ID+": " + correctCNPJ + CPNJ_TOKE + wrongCNPJ+"\n");
    }

    // Responsável por criar cada passo da função.
    public static String createStep(String ID, int correctWeight, int wrongWeight, float percentage)
    {
        int difference = Math.abs(correctWeight - wrongWeight);
        return (ID+": "+difference+"kg"+"("+ Math.round((percentage*100))+"%)"+"\n");
    }

    public static float calculateTenPercent(int wrongWeight, int correctWeight)    {
        int difference = Math.abs(wrongWeight - correctWeight);
        return (difference/(float)correctWeight);
    }

    public static boolean AnaliseTenPercent(float num)
    {
        return (num > 0.1f) ;
    }

    public static class FileData
    {
        private String ID;
        private String CNPJ;
        private int weight;
        private short mergePriority;
        private String output; // armazena o conteúdo a ser gerado no arquivo de saída.
    }

}
