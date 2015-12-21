package PAA;

import java.io.*;
import java.math.BigInteger;

/**
 * Created by Felipe Morais on 21/12/2015.
 */
public class Criptografia {

    static StringBuilder steps = new StringBuilder();   // Armazena toda os passos que serão gravados no arquivo de saída.
    static int a = 12;
    static int b = 45;
    static int p = 101;
    static int g = 16;
    static int M =1103515245;
    static int D = 12345;
    static int jj = 0;
    static int key = 0;
    public static void main(String args[]) throws IOException
    {
//        if(args.length !=0) {
//            readInputFile(args[0]);
//            runKMP();
//            sortDiseases(Diseases);
//            createSteps();
//            writeSteps(args[1]);
//        }
        TrocaDeChavesDiffieHellman();

    }

    private static void TrocaDeChavesDiffieHellman(){
        int A = (int)aritmetricaModular(g,a,p);
        int B = (int)aritmetricaModular(g,b,p);
        int sA = (int)aritmetricaModular(B,a,p);
        int sB = (int)aritmetricaModular(A,b,p);
        int testea = (int)'a';
        System.out.println("aCode:"+((int)'a'));
        System.out.println("bCode:"+((int)'b'));
        System.out.println("CCode:"+((int)'C'));

        String msg = "a";
        long keyStream = 0;
        keyStream = G(sA);
        System.out.println(keyStream);
        System.out.println(keyStream^'a');
        keyStream = G(sA);
        System.out.println(" sdfsadf" +keyStream);
        System.out.println(keyStream^'b');
    }

    public static void readInputFile(String filePath) throws FileNotFoundException {
        // Contem o caminho do arquivo
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        try
        {
            //Lê os cont?iners cadastrados

            int count = 0;
            while (br.ready())
            {
          //      Diseases[count] = examineLine(br.readLine());
                count++;
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static long aritmetricaModular(long base, long exponent, final long modulo) {
        // plan: exploit the binary representation of the exponent, see for example http://en.wikipedia.org/w/index.php?title=Modular_exponentiation&oldid=517653653#Right-to-left_binary_method
        long result = 1;
        while (exponent > 0) {
            if (exponent %2 == 1) // then exponent is odd
                result = (result*(base)) % modulo;
            exponent = exponent >> 1;
            base = (base * (base)) %(modulo);
        }
        return result;
    }
//
    //Encriptar
    private static long G(int k) {
        key = k;
        if(jj == 0)
            key = M * key + D;

         int z = (key >> jj) & 0b11111111;
        jj = (jj + 1) % 8;
        return z;
    }
    //DesenC
//
//    function modular_pow(base, exponent, modulus)
//    result := 1
//            while exponent > 0
//            if (exponent mod 2 == 1):
//    result := (result * base) mod modulus
//    exponent := exponent >> 1
//    base = (base * base) mod modulus
//    return result
}
