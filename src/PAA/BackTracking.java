package PAA;

import java.io.*;

/**
 * Created by FelipeMSX on 01/12/2015.
 */
public class BackTracking {

    // Em ordem de proridade(Direita,Frente,Esquerda,Atr�s).
    /*
        Ao avan�ar para algum lugar, o seu lugar anterior � deixado um marcador indicando para onde avan�ou.
        Isso permitir� saber quais possi��es ainda rest�o para avan�ar.
        -1 indica que j� foram esgotadas todas as possibilidades.
     */
    //Constantes
    static final byte RIGHT     = 10;
    static final byte FORWARD   = 11;
    static final byte LEFT      = 12;
    static final byte BACK      = 13;
    static final byte INVALID   = -1;
    static final byte START_LOCATION = 0;
    static final byte WALLBLOCK = 1;
    static final byte FREEWAY   = 0;

    //Variav�is usadas a cada rodada do algoritmo
    static byte[][] maze            = new byte [100][100]; // N�o ir� ser preciso ficar limpando a matriz toda hora.
    static byte [] solutionList     = new byte [1000];
    static short solutionPos        = 0;
    static byte rowsCount           = 0;
    static byte columnsCount        = 0;
    static byte initialPositionX    = 0;
    static byte initialPositionY    = 0;

    static short numberMaze = 0;
    static short currentMazePos = 0;
    static StringBuilder steps = new StringBuilder();   // Armazena toda os passos que ser�o gravados no arquivo de sa�da.


    public static void main(String[] args) throws IOException {
        if(args.length !=0) {
            readInputFile(args[0],args[1]);
            runBackTracking();

        }
    }

    public static void runBackTracking(){
        createListName(currentMazePos);
        createInitialStep(initialPositionX,initialPositionY);
        //Empilha posi��o de in�cio ao conjunto solu��o.
        solutionList[solutionPos++] = 0;
        byte currentLocationX = initialPositionX;
        byte currentLocationY = initialPositionY;

        //Se o vetor n�o conter mais nenhum elemento � pq n�o existe solu��o poss�vel.
        while(solutionPos != 0) {

            byte previousDirection = maze[currentLocationX][currentLocationY];
            //Verifica se a solu��o � v�lida
            if(isValidSolution(maze,currentLocationX,currentLocationY)) {
                steps.append("SAIDA ["+currentLocationX+","+currentLocationY+"]\n");
                return;
            }
            //N�o pode mais avan�ar. Dar o blackFrozeins.
            if(previousDirection == INVALID) {
                //Se posi��o for igual a inicial terminar.
                if(isInitialPosition(currentLocationX,currentLocationY)) {
                    solutionPos = 0;
                    break;
                }

                short current = solutionList[--solutionPos];
                switch (current)
                {
                    case(RIGHT):
                        steps.append("BT ["+currentLocationX+","+(currentLocationY-1)+"]<-["+currentLocationX+","+currentLocationY+"]\n");
                        currentLocationY--;
                        break;
                    case(LEFT):
                        steps.append("BT ["+currentLocationX+","+(currentLocationY+1)+"]<-["+currentLocationX+","+currentLocationY+"]\n");
                        currentLocationY++;
                        break;
                    case(FORWARD):
                        steps.append("BT ["+(currentLocationX+1)+","+currentLocationY+"]<-["+currentLocationX+","+currentLocationY+"]\n");
                        currentLocationX++;
                        break;
                    case(BACK):
                        steps.append("BT ["+(currentLocationX-1)+","+currentLocationY+"]<-["+currentLocationX+","+currentLocationY+"]\n");
                        currentLocationX--;
                        break;
                    default: break;
                }
            }
            //Verificar se pode ir para direita,
            else if(previousDirection == FREEWAY) { //Tentar ir para direita.
                //N�o vai passar da fronteira do vetor?
                // Se o caminhon j� foi percorrido"n�o voltar para um caminho anterior" dar como inv�lida a op��o
                if(currentLocationY+1 < columnsCount && maze[currentLocationX][currentLocationY+1] != INVALID
                        && (isPreviousPath(maze,currentLocationX,currentLocationY+1)) ) {
                    solutionList[solutionPos++] = RIGHT;
                    //Empilhar caminho;
                    steps.append("D ["+currentLocationX+","+currentLocationY+"]->["+currentLocationX+","+(currentLocationY+1)+"]\n");
                    maze[currentLocationX][currentLocationY++] = RIGHT;
                }else {
                    //J� verificou se pode ir ou n�o para direita, independimente se avan�ou ou n�o.
                    maze[currentLocationX][currentLocationY] = RIGHT;
                }
            }
            else if(previousDirection == RIGHT) { // Tentar ir para cima
                if(currentLocationX-1 >= 0 && maze[currentLocationX-1][currentLocationY] != INVALID
                        && (isPreviousPath(maze,currentLocationX-1,currentLocationY)) ) {
                    solutionList[solutionPos++] = FORWARD;
                    //Empilhar solu��o.
                    steps.append("F ["+currentLocationX+","+currentLocationY+"]->["+(currentLocationX-1)+","+currentLocationY+"]\n");
                    maze[currentLocationX--][currentLocationY] = FORWARD;
                }else {
                    maze[currentLocationX][currentLocationY] = FORWARD;
                }
            }
            else if(previousDirection == FORWARD) { // tentar ir para esquerda
                if(currentLocationY-1 >= 0 && maze[currentLocationX][currentLocationY-1] != INVALID
                        && (isPreviousPath(maze,currentLocationX,currentLocationY-1)) ) {
                    solutionList[solutionPos++] = LEFT;
                    //Empilhar solu��o.
                    steps.append("E ["+currentLocationX+","+currentLocationY+"]->["+currentLocationX+","+(currentLocationY-1)+"]\n");
                    maze[currentLocationX][currentLocationY--] = LEFT;
                }else {
                    maze[currentLocationX][currentLocationY] = LEFT;
                }
            }
            //Depois de ir pra esquerda, s� resta tentar ir para atr�s.
            else if(previousDirection == LEFT) { // Tentar ir para baixo.
                if(currentLocationX+1 < rowsCount && maze[currentLocationX+1][currentLocationY] != INVALID
                        && (isPreviousPath(maze,currentLocationX+1,currentLocationY))) {
                    solutionList[solutionPos++] = BACK;
                    //Empilhar solu��o;
                    steps.append("T ["+currentLocationX+","+currentLocationY+"]->["+(currentLocationX+1)+","+currentLocationY+"]\n");
                    maze[currentLocationX++][currentLocationY] = BACK;
                }else {
                    maze[currentLocationX][currentLocationY] = INVALID;
                }
            }
            //se tentar ir para tr�s, e n�o conseguir, marcar como caminho inv�lido e dar BT.
            else if(previousDirection == BACK) {// Invalidar caminho.
                maze[currentLocationX][currentLocationY] = INVALID;
            }
        }
        steps.append("SEM SAIDA\n");

    }

    //Verifica se a solu��o encontrada � a final.
    //Verifica se a solu��o final n�o � o ponto de partida.
    //Verifica se a sa�da est� na borda do labirinto"0".
    public static boolean isValidSolution(byte[][] maze,int x, int y) {
        return(maze[x][y] == 0 && isBorder(x,y) && !isInitialPosition(x,y));
    }

    //Dado as coordenadas informa se est� na borda ou n�o.
    private static boolean isBorder(int x, int y) {
        return (y == 0 || x == 0 || (y+1) == columnsCount || (x+1) == rowsCount);
    }

    //Verifica se � parede.
    private static boolean isWall(byte[][] maze,int x, int y) {
        return maze[x][y] == WALLBLOCK ;
    }

    //Se o caminho for diferente de 0, � porque j� passou por ele
    private static boolean isPreviousPath(byte[][] maze,int x, int y ) {
        return maze[x][y] == FREEWAY;
    }

    private static boolean isInitialPosition(int x, int y) {
        return (x == initialPositionX && y == initialPositionY);
    }


    public static void readInputFile(String filePath,String filePathWrite) throws IOException {
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter( filePathWrite );
        try {

            numberMaze = Short.parseShort(br.readLine());

            while (br.ready()) {
                String rowsColumns[] = br.readLine().split(" ");
                rowsCount = Byte.parseByte(rowsColumns[1]);
                columnsCount = Byte.parseByte(rowsColumns[0]);
                steps =  new StringBuilder();
                //Montar a matriz tempor�ria.
                byte rowPosition = 0;
                while(rowPosition != rowsCount) {
                    String line[] = br.readLine().split(" ");

                    for (byte columnPosition = 0; columnPosition < line.length; columnPosition++) {
                       if(line[columnPosition].equals("X")){
                           maze[rowPosition][columnPosition] = START_LOCATION;
                           initialPositionX = rowPosition;
                           initialPositionY = columnPosition;
                       }else{
                            maze[rowPosition][columnPosition] = Byte.parseByte(line[columnPosition]);
                       }
                    }
                    rowPosition++;
                }

                //Resolver o labirinto...
                runBackTracking();
                currentMazePos++;
                if(currentMazePos == numberMaze)
                {
                    steps.delete(steps.length()-1,steps.length());
                }
                fw.write(steps.toString());

            }

            br.close();
            fr.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createListName(short position) {
        steps.append("L"+position+":\n");
    }

    public static void createInitialStep(byte initialPosX, byte initialPosY) {
        steps.append("INICIO ["+initialPosX+","+initialPosY+"]\n");
    }

}
