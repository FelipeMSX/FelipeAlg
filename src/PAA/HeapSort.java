package PAA;

/**
 * Created by Felipe on 29/04/2015.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class HeapSort {

    static short qtdPackages;
    static short interval;
    static String packages;
    static StringBuilder steps = new StringBuilder();// Armazena toda os passos que ser�o gravados no arquivo de sa�da.

    public static void main(String[] args) throws IOException {

        if(args.length !=0){
            loadFileData(args[0]);

            prepareSteps();
            writeSteps(args[1]);
        }
    }

    public static void prepareSteps()
    {

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
                //L� os cont�iners cadastrados
                int fileSize = Integer.parseInt(br.readLine());
                int count = 0;
                while (br.ready() && count < fileSize)
                {

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

    }
    public static void examineLine(String line){

    }

    // Respons�vel por criar cada passo da fun��o.
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
}
