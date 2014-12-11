import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


public class part6 {
	public static void main(String args[]) throws IOException{
		File file = new File("out235");
		String input;
		File file2=new File("edge3");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		BufferedReader br = new BufferedReader(new FileReader(file2));
		String linput=null;
		while((input=br.readLine())!=null){
			String[] line=input.split("\t");
			linput=line[1]+"\t"+line[0];
		     
		       if((m.get(input)!=null)||(m.get(linput)!=null)){
		    	   if(m.get(input)!=null){
		    	   int ss=m.get(input);
		    	   ss=ss+1;
		           m.put(input,ss);}
		    	   else{
		    		   int ss=m.get(linput);
			    	   ss=ss+1;
			           m.put(linput,ss);}
		    	   
		       }
		       else{
		    	   m.put(input, 1);
		       }
		       
		}
		Iterator it = m.entrySet().iterator();
		while(it.hasNext()){
		    Entry  entry=(Entry)it.next();
		   String key=entry.getKey().toString();
		    String value=entry.getValue().toString();
		 bw.write(key+"\t"+value+"\n");
		   
		}
		br.close();
		bw.close();
	}

}
