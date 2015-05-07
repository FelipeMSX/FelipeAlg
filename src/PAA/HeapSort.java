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

    static int i = 0;
    static int j = 0;
    static int k = 0;
    static StringBuilder steps = new StringBuilder();// Armazena toda os passos que serão gravados no arquivo de saída.


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

        if (fr != null)
        {
            BufferedReader br = new BufferedReader(fr);
            try
            {
                //Lê os contêiners cadastrados
                int fileSize = Integer.parseInt(br.readLine());
              //  FileData[] ConteinersCadastrados = new FileData[fileSize];
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
    }

    public static void examineLine(String line){

    }


    // Responsável por criar cada passo da função.
    public static String createStep(String ID, String correctCNPJ, String wrongCNPJ)
    {
        return null;
    }


}
