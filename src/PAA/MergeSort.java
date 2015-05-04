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
    static int coutVetor = 0;
    static StringBuilder steps = new StringBuilder();
    static FileData[] ConteinersOrdenar;
    static FileData[] ConteinersSaida;
    static final String CPNJ_TOKE = "<->";
    static FileData dataM;

    public static void main(String[] args) throws IOException {

        if(args.length !=0){
            loadFileData(args[0]);
            mergesort(ConteinersOrdenar);
			System.out.println("teste2");

            for(int i =0 ; i < coutVetor-1; i++)
            {
              steps.append(ConteinersSaida[i].saidaImpressao);
            }
            steps.append(ConteinersSaida[coutVetor-1].saidaImpressao.replace("\n",""));
			FileWriter fw = new FileWriter(args[1]);
			fw.write(steps.toString());
			fw.close();
        }
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
                    ConteinersCadastrados[count] = analiseLine(br.readLine());
                    count++;
                }

                //Lista de Contêiners com erros
                int fileSize2 = Integer.parseInt(br.readLine());
                ConteinersOrdenar = new FileData[fileSize2];
                while (br.ready())
                {
                    FileData data = analiseLine(br.readLine());

                    FileData dataAchado = null;
                    for (int i = 0; i < fileSize; i++)
                    {
                        if(data.ID.equals(ConteinersCadastrados[i].ID))
                        {
                            dataAchado = ConteinersCadastrados[i];
                            break;
                        }
                    }
                    if (!data.CNPJ.equals(dataAchado.CNPJ))
                    {
                        data.mergePrioridade = 1000;
                        data.saidaImpressao = criarPasso(data.ID, dataAchado.CNPJ, data.CNPJ);
                        ConteinersOrdenar[coutVetor] = data;
                        coutVetor++;
                    } else
                    {
                        float pesoDiferancaPercentual = calcularDezPorcento(data.peso, dataAchado.peso);
                        if (analisarDezPorcento(pesoDiferancaPercentual)) {
                            data.mergePrioridade = (short)Math.round(pesoDiferancaPercentual*100) ;
                            data.saidaImpressao = criarPasso(data.ID, dataAchado.peso, data.peso, pesoDiferancaPercentual);
                            ConteinersOrdenar[coutVetor] = data;
                            coutVetor++;
                        }
                    }
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
        ConteinersSaida = new FileData[coutVetor];
        merge(input, 0, coutVetor-1);
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
                if(input[i].mergePrioridade > input[j].mergePrioridade)
                {
                    ConteinersSaida[k++] = input[i++];
                }else
                {
                    ConteinersSaida[k++] = input[j++];

                }
        }
        for(int w = ini ; w <= fim; w++){
            input[w] = ConteinersSaida[w];
        }

    }

    // Responsável por criar cada passo da função.
    public static String criarPasso(String ID,String CNPJCorreto, String CNPJErrado)
    {
        return (ID+": " + CNPJCorreto + CPNJ_TOKE + CNPJErrado+"\n");
    }

    // Responsável por criar cada passo da função.
    public static String criarPasso(String ID ,int pesoCorreto, int pesoErrado, float percentual)
    {
        int diferenca = Math.abs(pesoCorreto - pesoErrado);
        return (ID+": "+diferenca+"kg"+"("+ Math.round((percentual*100))+"%)"+"\n");
    }

    public boolean CNPJDiferente(String cnpj1, String cpnj2)
    {
        return (cnpj1.equals(cpnj2));
    }

    public static float calcularDezPorcento(int pessoErrado, int pessoCorreto)    {
        int diferenca = Math.abs(pessoErrado - pessoCorreto);
        return (diferenca/(float)pessoCorreto);
    }

    public static boolean analisarDezPorcento(float num)
    {
        return (num > 0.1f) ;
    }

    public static class FileData
    {
        private String ID;
        private String CNPJ;
        private int peso;
        private short mergePrioridade;
        private String saidaImpressao;
    }

}
