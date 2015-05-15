package PAA;

/**
 * Created by Felipe on 12/05/2015.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class HeapSort {

    static short qtdPacotes;
    static short intervalo;
    static FileData[] pacotesForaDeOrdem;
    static FileData[] pacotesEntrada;
    static short ordemEsperada; // indica qual o número pacote é esperado;
    static String[] bufferOrdenar; // Armazena os dados do pacotesEntrada para mandar ordenar assim que o limite de pacotesEntrada for atingindo.
    static int qtdBufferItens = 0;
    static StringBuilder steps = new StringBuilder();// Armazena toda os passos que serão gravados no arquivo de saída.
    static String sortedDatas;

    public static void main(String[] args) throws IOException
    {
        if(args.length !=0)
        {
            loadFileData(args[0]);

             bufferOrdenar = new String[5000];
              prepareSteps();
//            writeSteps(args[1]);
            System.out.println();
        }
    }

    public static void prepareSteps()
    {
        int count = 0;
        int intercaloCount = 0;
        while(count != qtdPacotes)// enquanto todos pacotesEntrada não forem processados faça.
        {
            intercaloCount++;
            count++;
            if(intercaloCount == intervalo)
            {
                AnalisarPacotes(count);
                intercaloCount = 0;
            }
            else//Se a mandar analisar mas não atingir o limite é para executar;
            if( count == qtdPacotes && intercaloCount > 0)
            {
                AnalisarPacotes(count);
            }
        }
    }

    public static void AnalisarPacotes(int inicio)
    {
        for(int i = inicio - intervalo; i < inicio; i++  )//Ao final do for irei ter os dados no bufferOrdenar para ir pro heap.
        {
            FileData f = pacotesEntrada[i];
            TransferirPacotesbufferForadeOrdemToBufferHeap(); // evita que pacotes sejam colocados do bufferForadeOrdem sem necessidade;
            if (f.ordemPackage == ordemEsperada)// Se pacote for o esperado colocar no buffer;
            {
                    colocarPacoteNoBufferParaOHeap(f);
                    ordemEsperada++;
            }else
            {
                colocarPacoteNoBufferForaDeOrdem(f);
            }
        }
        //Mandar Ordenar no Heap;

    }

    public static void TransferirPacotesbufferForadeOrdemToBufferHeap()
    {
        //Se Ao adocionar os pacotes e existir pacotes de ordem
        FileData f = buscarPacoteNobufferForaDeOrdem();
        while(f!= null)
        {
            colocarPacoteNoBufferParaOHeap(f);
            ordemEsperada++;
            f = buscarPacoteNobufferForaDeOrdem();
        }
    }

    public static FileData buscarPacoteNobufferForaDeOrdem()
    {
        if(pacotesForaDeOrdem[ordemEsperada] != null && pacotesForaDeOrdem[ordemEsperada].ordemPackage == ordemEsperada)
        {
            FileData f = pacotesForaDeOrdem[ordemEsperada];
            pacotesForaDeOrdem[ordemEsperada] = null;
            return f;
        }else
            return null;
    }

    public static void colocarPacoteNoBufferForaDeOrdem(FileData f)
    {
        pacotesForaDeOrdem[f.ordemPackage] = f;
    }

    public static void colocarPacoteNoBufferParaOHeap(FileData f)
    {
        if(qtdBufferItens+f.dados.length > bufferOrdenar.length)
        {
            dobrarCapacidade();
            colocarPacoteNoBufferParaOHeap(f);
        }else
        {
            int tamanho = f.dados.length;
            for (int i = 0; i < tamanho; i++) {
                bufferOrdenar[qtdBufferItens++] = f.dados[i];
            }
        }
    }

    public static void dobrarCapacidade()
    {
        String[] temp = new String[bufferOrdenar.length*2];
        for(int i= 0; i < 0; i++)
            temp[i] = bufferOrdenar[i];

        bufferOrdenar = temp;
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
            try
            {
                //Lê os contêiners cadastrados
                examineFirstLine(br.readLine());
                pacotesEntrada = new FileData[qtdPacotes];
                pacotesForaDeOrdem = new FileData[qtdPacotes];
                int count = 0;
                while (br.ready())
                {
                    pacotesEntrada[count] = examineLine(br.readLine());
                    count++;
                }

                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public static void examineFirstLine(String line)
    {
        String[] s = line.split(" ");
        qtdPacotes =  Short.parseShort(s[0]);
        intervalo = Short.parseShort(s[1]);
    }
    public static FileData examineLine(String line)
    {
        FileData f = new FileData();
        f.ordemPackage = Short.parseShort(line.substring(0, 1));
        String l = line.substring(4,line.length());
        f.dados  = l.split(" ");

        return f;
    }

    // Responsável por criar cada passo da função.
    public static String createStep(String ID, String correctCNPJ, String wrongCNPJ)
    {
        return null;
    }

    public int getFather(int i)
    {
        return (i-1)/2;
    }

    public int getLeft(int i)
    {
        return 2*i+1;
    }

    public int getRight(int i)
    {
        return 2*i+2;
    }

    public static class FileData
    {
        public short ordemPackage;
        public String[] dados;

    }
}
