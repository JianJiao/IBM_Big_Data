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


public class part5 {
	public static void main(String args[]) throws IOException{
		File file = new File("out233");
		String input;
		File file2=new File("edge2");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		HashMap<String, String> m = new HashMap<String, String>();
		BufferedReader br = new BufferedReader(new FileReader(file2));
		while((input=br.readLine())!=null){
			String[] line=input.split("\t");
			
		     
		       if(m.get(line[1])!=null){
		    	   String ss=m.get(line[1]);
		    	   ss=ss+"\t"+line[0];
		           m.put(line[1],ss);
		       }
		       else{
		    	   m.put(line[1], line[0]);
		       }
		       
		}
		Iterator it = m.entrySet().iterator();
		while(it.hasNext()){
		    Entry  entry=(Entry)it.next();
		   String key=entry.getKey().toString();
		    String value=entry.getValue().toString();
		    String[] str=value.split("\t");
		    ArrayList<String> str1=new ArrayList<String>();
		    
		    for(int i=0;i<str.length;i++){
		    	str1.add(str[i]);
		    }
		    Collections.sort(str1);
		    for(int i=0;i<str.length;i++){
		    	for(int j=i+1;j<str.length;j++){
		    		bw.write(str1.get(i)+"\t"+str1.get(j)+"\n");
		    	}
		    }
		   
		}
		br.close();
		bw.close();
	}

}
