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
    static FileData[] ConteinersSaida; // Vetor que armazena os registros armazenado.

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
            int fileSize = Integer.parseInt(br.readLine());
            FileData[] ConteinersCadastrados = new FileData[fileSize];
            int count = 0;
            while (br.ready() && count < fileSize) {
                ConteinersCadastrados[count] = examineLine(br.readLine());
                count++;
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
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

    // Responsável por criar cada passo da função.
    public static void createStep()
    {

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
