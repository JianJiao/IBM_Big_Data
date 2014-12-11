import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


public class part4 {
	public static void main(String args[]) throws IOException{
		File file = new File("output");
		String input;
		File file2=new File("outputnew2");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file2));
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		while((input=br.readLine())!=null){
			String[] line=input.split("\t");
			
		     
		       if(m.get(line[1])!=null){
		    	   int ss=m.get(line[1]);
		           m.put(line[1],ss+1);
		       }
		       else{
		    	   m.put(line[1], 1);
		       }
		       
		}
		Iterator it = m.entrySet().iterator();
		while(it.hasNext()){
		    Entry  entry=(Entry)it.next();
		   String key=entry.getKey().toString();
		    String value=entry.getValue().toString();
		   // key1=key.toString();
		    bw.write(key+"\t"+value+"\n");
		}
		br.close();
		bw.close();
	}

}
