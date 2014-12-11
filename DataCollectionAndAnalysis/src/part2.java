
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;



















import org.w3c.dom.*;
import org.xml.sax.SAXException;


public class part2 {
	public static class Employee{
		  String title;
		 
	      String wsdl;
		 
		  
		  
		}
  public static int length=0;
  public static int length1=0;
  public static int length2=0;
  //public static File file2 = new File("resources.rdf");
  public static List<Employee> empList = new ArrayList<>();
	public static void main(String[] args) throws Exception, IOException, ParserConfigurationException, Exception{  
        Decode();
       
       
              
           
        	
	
       
    }  
      
	public  static void Decode() throws SAXException, IOException
	{
		//File file=new File("findNearbyWikipedia");
		String content;
		File file3=new File("output");
		PrintWriter pw=new PrintWriter(new FileWriter(file3));
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = new File("1.t2flow");
		//File file2= new File("doubi.xml");
	//	 BufferedReader br = new BufferedReader(new
 		//		FileReader(file));
		 //BufferedWriter bw=new BufferedWriter(new FileWriter(file2));
		 String input;
		
		 //bw=new BufferedWriter(new FileWriter(file2));
		
		// BufferedReader br2 = new BufferedReader(new
	 		//		FileReader(file2));
		 //input=br2.readLine();
		 
		Document doc=db.parse(file);
		StringBuffer sb = new StringBuffer();
		
		
			NodeList list0=doc.getElementsByTagName("wsdl");
			//NodeList list=doc.getElementsByTagName("s:workflowdescription");
			String title="haha";
			/*if(list.getLength()!=0){
			title=list.item(0).getAttributes().getNamedItem("title").getNodeValue();
			}
			else{
				if (doc.getElementsByTagName("name").getLength()==0) {bw=new BufferedWriter(new FileWriter(file2));continue;}
		title=doc.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
			}
			*/
			//System.out.println(title);
			//NodeList list1=doc.getElementsByTagName("wikipediaUrl");
			length=list0.getLength();
			ArrayList a=new ArrayList();
			String b;
			for(int i=0;i<length;i++)
			{
				b=list0.item(i).getFirstChild().getNodeValue();
				//temp1[i]=list.item(i).getFirstChild().getNodeValue();
			 	//name[i]=list0.item(i).getFirstChild().getNodeValue();
				if(!a.contains(b))
				{
			     a.add(b);
				}
				//System.out.println(a);
			}
			int jq=a.size();
			pw.print(title+"\t");
			pw.flush();
			for(int i=0;i<jq;i++)
			{
				pw.print(a.get(i)+"\t");
				pw.flush();
			}
			pw.print("\n");
			pw.flush();
			length2++;
			System.out.println(length2);
			//bw=new BufferedWriter(new FileWriter(file2));
			pw.close();
		}
		 
		
		 
	
		 
	
}
	
