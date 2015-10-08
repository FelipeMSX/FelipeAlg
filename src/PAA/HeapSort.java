package PAA;

/**
 * Created by Felipe on 12/05/2015.
 */

import java.io.*;


public class HeapSort {

    static int qtdPacotesTotal;
    static short intervalo;
    static FileData[] pacotesEntrada;
    static int sqPacoteEsperado = 0;
    static int stepsCount;

    static StringBuilder steps = new StringBuilder();// Armazena toda os passos que serão gravados no arquivo de saída.
    static StringBuilder PreSteps = new StringBuilder();

    public static void main(String[] args) throws IOException
    {
        if(args.length !=0)
        {
            loadFileData(args[0]);


            writeSteps(args[1]);
            System.out.println();
        }
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
                pacotesEntrada = new FileData[qtdPacotesTotal];
                int qtdPacotesAnalisados = 0;
                int countIntervalo = 0;
                while (br.ready())
                {
                    pacotesEntrada[qtdPacotesAnalisados] = examineLine(br.readLine());
                    qtdPacotesAnalisados++;
                    countIntervalo++;
                    if(countIntervalo == intervalo)// quando atingir o limite é preciso analisar
                    {
                        heapsort(pacotesEntrada,qtdPacotesAnalisados);
                        createPreStep(pacotesEntrada);
                        createIntervalStep(PreSteps);
                        countIntervalo = 0;
                    }
                }

                if(countIntervalo > 0)
                {
                    heapsort(pacotesEntrada, qtdPacotesAnalisados);
                    createPreStep(pacotesEntrada);
                    createIntervalStep(PreSteps);
                }

                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public static void createPreStep(FileData input[])
    {
        if(pacotesEntrada[sqPacoteEsperado] != null && pacotesEntrada[sqPacoteEsperado].ordemPackage == sqPacoteEsperado)
        {
            FileData f = pacotesEntrada[sqPacoteEsperado++];
            int temp = f.dados.length ;
            for(int i = 0; i < temp; i++ )
            {
                PreSteps.append(" "+f.dados[i]);
        }
            if(sqPacoteEsperado != qtdPacotesTotal)
            createPreStep(pacotesEntrada);
        }
    }

    public static void createIntervalStep(StringBuilder PreSteps)
    {
        steps.append(stepsCount +":" + PreSteps.toString()+"\n");
        stepsCount++;
    }

    public static void maxHeapify(FileData input[], int i, int n) {
        int P = i;
        int E = getLeft(i);
        int D = getRight(i);
        if (E < n && input[E].ordemPackage > input[P].ordemPackage) P = E;
        if (D < n && input[D].ordemPackage > input[P].ordemPackage) P = D;
        if (P != i)
        {
            swapItem(input, i, P);
            maxHeapify(input, P, n);
        }
    }

    public static void heapsort(FileData input[], int n)// heapmax
    {
        buildMaxHeap(input,n);
        int i;
        for(i = n - 1; i > 0; i--)
        {
            swapItem(input,0,i);
            maxHeapify(input, 0, i);
        }
    }
    private static void buildMaxHeap(FileData[] input, int n)
    {
        for (int i = n/2 - 1; i >= 0; i--)
            maxHeapify(input, i , n);
    }

    public static void swapItem(FileData[] input, int positionX, int positionY)
    {
        FileData temp = input[positionX];
        input[positionX] = input[positionY];
        input[positionY] = temp;
    }


    public static void examineFirstLine(String line)
    {
        String[] s = line.split(" ");
        qtdPacotesTotal =  Short.parseShort(s[0]);
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

    public static int getFather(int i)
    {
        return (i-1)/2;
    }

    public static int getLeft(int i)
    {
        return 2*i+1;
    }

    public static int getRight(int i)
    {
        return 2*i+2;
    }

    public static class FileData
    {
        public short ordemPackage;
        public String[] dados;

    }
}
