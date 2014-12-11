package preProcession;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WebserviceProcess {
	
	public static void myProcess() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("wsdls-id.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("webservice.txt"));
		String line;
		while((line = br.readLine())!=null){
			line = line.split("\t")[0];
			bw.write(line+"\n");	
		}
		br.close();
		bw.close();
	}
	
	
	
	public static void main(String[] args) throws IOException{
		myProcess();
	}

}
