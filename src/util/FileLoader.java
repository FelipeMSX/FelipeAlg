/**
 * 
 */
package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author FelipeMSX
 *
 */
public class FileLoader {

	private FileReader fr;
	private BufferedReader br;
	private final int MAX = 100000;
	private long dataFile[] = new long[MAX];
	
	public void loader(String filename){
		try {
			fr = new FileReader(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if (fr != null){
			br = new BufferedReader( fr );		
			try {
				String linha;
				int count = 0;
				
				while( count < MAX){
					linha = br.readLine();
					dataFile[count] = Long.parseLong(linha);
					count++;
				}
				
			br.close();	
			fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isLoader(){
		return (this.dataFile != null);
	}

	public long[] getDataFile() {
		return dataFile;
	}

	public void setDataFile(long[] dataFile) {
		this.dataFile = dataFile;
	}

	public int getMAX() {
		return MAX;
	}
	
}
