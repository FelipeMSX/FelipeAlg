package PAA;

import java.io.*;

/**
 * Created by FelipeMSX on 01/12/2015.
 */
public class BackTracking {

    // Em ordem de proridade(Direita,Frente,Esquerda,Atr�s).
    //Constantes
    static final byte RIGHT     = 0;
    static final byte FORWARD   = 1;
    static final byte LEFT      = 2;
    static final byte BACK      = 3;
    static final byte INVALID   = -1;
    static final byte CURRENT_LOCATION = 8;

    //Variav�is usadas a cada rodada do algoritmo
    static byte[][] maze = new byte [100][100]; // N�o ir� ser preciso ficar limpando a matriz toda hora.
    static byte rowsCount          = 0;
    static byte columnsCount       = 0;
    static byte initialPositionX   = 0;
    static byte initialPositionY   = 0;

    static int numberMaze = 0;
    static StringBuilder steps = new StringBuilder();   // Armazena toda os passos que ser�o gravados no arquivo de sa�da.


    public static void main(String[] args) throws IOException {
        if(args.length !=0) {
            readInputFile(args[0]);
            runBackTracking();
            createSteps();
            writeSteps(args[1]);
        }
    }

    public static void runBackTracking(){

    }
    //Verifica se o passo � v�lido de acordo com as restri��es do problema.
    //N�o pode avan�ar para a parede
    //
    public boolean canContinue() {
        return false;
    }

    //Verifica se a solu��o encontrada � a final.
    //Verifica se a solu��o final n�o � o ponto de partida.
    //Verifica se a sa�da est� na borda do labirinto"0".
    public static boolean isValidSolution(int x, int y) {
        return(isBorder(x,y) && !isInitialPosition(x,y));
    }

    //Dado as coordenadas informa se est� na borda ou n�o.
    private static boolean isBorder(int x, int y) {
        return (y == 0 || x == 0 || y == columnsCount || x == rowsCount);
    }

    private static boolean isInitialPosition(int x, int y) {
        return (x == initialPositionX && y == initialPositionY);
    }

    //Ap�s anlisar todos os caminhos poss�veis, e n�o encontrado a solu��o, � necess�rio retrocedor.
    //"Queima" o caminho com -1, para n�o repeti-lo,
    public void backTrackingStep(Object c) {

    }

    public static void createSteps() {


    }

    public static void writeSteps(String filePath) throws IOException {
        FileWriter fw = new FileWriter( filePath );
        fw.write(steps.toString());
        fw.close();
    }

    public static void readInputFile(String filePath) throws FileNotFoundException {
        // Contem o caminho do arquivo
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        try {

            numberMaze = Integer.parseInt(br.readLine());

            while (br.ready()) {
                String rowsColumns[] = br.readLine().split(" ");
                rowsCount = Byte.parseByte(rowsColumns[1]);
                columnsCount = Byte.parseByte(rowsColumns[0]);
                //Montar a matriz tempor�ria.
                byte rowPosition = 0;
                while(rowPosition != rowsCount) {
                    String line[] = br.readLine().split(" ");

                    for (byte columnPosition = 0; columnPosition < line.length; columnPosition++) {
                       if(line[columnPosition].equals("X")){
                           maze[rowPosition][columnPosition] = CURRENT_LOCATION;
                           initialPositionX = rowPosition;
                           initialPositionY = columnPosition;
                       }else{
                            maze[rowPosition][columnPosition] = Byte.parseByte(line[columnPosition]);
                       }
                    }
                    rowPosition++;
                }

                //Resolver o labirinto...
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Armazenar a lista de solu��es
    public class ListSolution{
        public ListSolution fatherNode;

    }

}
