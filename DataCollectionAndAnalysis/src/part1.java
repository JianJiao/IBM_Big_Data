import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class part1 {
	public static class Employee {
		String title;

		String wsdl;

	}
	public static int length3 = 0;
	public static int length = 0;
	public static int length1 = 0;
	public static int length2 = 0;
	public static int count0 = 0;
	public static int count1 = 0;
	public static int count2 = 0;
	// public static File file2 = new File("resources.rdf");
	public static List<Employee> empList = new ArrayList<>();
	static HashMap<String, String> banWord = new HashMap<String, String>();
	public static void main(String[] args) throws Exception, IOException,
			ParserConfigurationException, Exception {
		 buildSentimentList();
		Decode();

	}

	public static void Decode() throws SAXException, IOException {
		// File file=new File("findNearbyWikipedia");
		// String content;
		
		
		File file3 = new File("output");
		PrintWriter pw = new PrintWriter(new FileWriter(file3));
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = new File("workflow");
		File file2 = new File("doubi.xml");
		BufferedReader br = new BufferedReader(new FileReader(file));
		BufferedWriter bw = new BufferedWriter(new FileWriter(file2));
		String input;
		while ((input = br.readLine()) != null) {
			String title = input;
			input = br.readLine();
			br.readLine();
			length1++;
			System.out.println(length1);
			// if (length1==496){continue;}

			/* if (length1==200||length1==287||length1==424||length1==543||length1==559||length1==697||length1==1293||length1==2399){continue;}*/
			if (input.equals("null"))
				continue;
			
			// String reg = "";
			// Pattern pattern = new Pattern(reg, );
			// Matcher m = new Matcher(pattern);
			if (input.matches("^PK.*"))
				continue;
			// if(input.matches("^<workflow.*")) continue;
			if (input.matches("^.wf:Sequential.*")) {
				count0++;
				continue;
			}
			if (input.matches("^<html.*")) {
				count1++;
				System.out.println(count0 + "\t" + count1);
				continue;
			}
			if ((!(input.matches("^<.xml.*")) && (!input
					.matches("^<workflow.*"))))
				continue;
			bw.write(input + "\n");
			bw.close();
			// bw=new BufferedWriter(new FileWriter(file2));

			// BufferedReader br2 = new BufferedReader(new
			// FileReader(file2));
			// input=br2.readLine();

			Document doc = db.parse(file2);
			StringBuffer sb = new StringBuffer();
			if (input.matches("^<.xml.*")) {
				NodeList list0 = doc.getElementsByTagName("s:wsdl");
                  length3++;
				// System.out.println(title);
				// NodeList list1=doc.getElementsByTagName("wikipediaUrl");
				length = list0.getLength();
				ArrayList a = new ArrayList();
				String b;
				for (int i = 0; i < length; i++) {
					b = list0.item(i).getFirstChild().getNodeValue();
					// temp1[i]=list.item(i).getFirstChild().getNodeValue();
					// name[i]=list0.item(i).getFirstChild().getNodeValue();
					if (!a.contains(b)) {
						a.add(b);
					}
					// System.out.println(a);
				}
				int jq = a.size();
			/*	
				for (int i = 0; i < jq; i++) {
					pw.print("workflow"+length3+"\t");
					pw.flush();
					pw.print(a.get(i) + "\t");
					pw.flush();
					
					
				}
				*/
				Iterator it = banWord.entrySet().iterator();
				while(it.hasNext()){
				    Entry  entry=(Entry)it.next();
				   String key=entry.getKey().toString();
				    String value=entry.getValue().toString();
				   // key1=key.toString();
				   if(input.matches(".*"+key+".*")) {
					   pw.print("workflow"+length3+"\t"+value+"\n");
					   pw.flush();
				   }
				}
				//pw.print("\n");
				//pw.flush();
				length2++;
				// System.out.println(length2);
				bw = new BufferedWriter(new FileWriter(file2));
			} else {
				NodeList list0 = doc.getElementsByTagName("wsdl");
				// NodeList
				// list=doc.getElementsByTagName("s:workflowdescription");

				/*
				 * if(list.getLength()!=0){
				 * title=list.item(0).getAttributes().getNamedItem
				 * ("title").getNodeValue(); } else{ if
				 * (doc.getElementsByTagName("name").getLength()==0) {bw=new
				 * BufferedWriter(new FileWriter(file2));continue;}
				 * title=doc.getElementsByTagName
				 * ("name").item(0).getFirstChild().getNodeValue(); }
				 */
				// System.out.println(title);
				// NodeList list1=doc.getElementsByTagName("wikipediaUrl");
				length = list0.getLength();
				ArrayList a = new ArrayList();
				String b;
				length3++;
				/*
				for (int i = 0; i < length; i++) {
					b = list0.item(i).getFirstChild().getNodeValue();
					// temp1[i]=list.item(i).getFirstChild().getNodeValue();
					// name[i]=list0.item(i).getFirstChild().getNodeValue();
					if (!a.contains(b)) {
						a.add(b);
					}
					// System.out.println(a);
				}
				int jq = a.size();
				*/
				/*
				for (int i = 0; i < jq; i++) {
					pw.print("workflow"+length3+"\t");
					pw.flush();
					pw.print(a.get(i) + "\t");
					pw.flush();
					
				}
				pw.print("\n");
				pw.flush();*/
				Iterator it = banWord.entrySet().iterator();
				while(it.hasNext()){
				    Entry  entry=(Entry)it.next();
				   String key=entry.getKey().toString();
				    String value=entry.getValue().toString();
				   // key1=key.toString();
				   if(input.matches(".*"+key+".*")) {
					   pw.print("workflow"+length3+"\t"+value+"\n");
					   pw.flush();
				   }
				}
				length2++;
				// System.out.println(length2);
				bw = new BufferedWriter(new FileWriter(file2));

			}

		}
       System.out.print(count1+"\t"+count2);
		pw.close();
	}
	private static void buildSentimentList() throws IOException {
		
			File file4=new File("rest-id.txt");
			
			BufferedReader br = new BufferedReader(new FileReader(file4));
			String line;
			while ((line = br.readLine()) != null) {
				String[] split = line.split("\t");
				
				banWord.put(split[1], split[0]);
		
	}
			br.close();
}
}
