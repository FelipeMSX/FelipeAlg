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

    static short qtdPackages;
    static short interval;
    static short packageSearch;// ponteiro para a posição do vetor,
    static short packagePonteiro;//Indica até onde ja foram processados os pacotes
    static FileData[] packages;

    static StringBuilder steps = new StringBuilder();// Armazena toda os passos que serão gravados no arquivo de saída.

    static String[] bufferOrdenar; // Armazena os dados do pacotes para mandar ordenar posteriomente.
    static short contadorDeDadosNoPacoteOrdenado;


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
        while(packagePonteiro < qtdPackages)// enquanto todos pacotes não forem processados faça.
        {

        }
    }

    public static void AnalisarPacotes()
    {

    }

    public static void adicionarAoBufferDePacotesOrdenados(FileData f)
    {

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
                packages = new FileData[qtdPackages];
                int count = 0;
                while (br.ready())
                {
                    packages[count] = examineLine(br.readLine());
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
        qtdPackages =  Short.parseShort(s[0]);
        interval    = Short.parseShort(s[1]);
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
