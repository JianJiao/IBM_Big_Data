import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class part3 {
	static HashMap<String, String> banWord = new HashMap<String, String>();
	public static int length3 = 0;
	public static int length = 0;
	public static int length1 = 0;
	public static int length2 = 0;
	public static int count0 = 0;
	public static int count1 = 0;
	public static int count2 = 0;
	// public static File file2 = new File("resources.rdf");
	//public static List<Employee> empList = new ArrayList<>();

	public static void main(String[] args) throws Exception, IOException,
			ParserConfigurationException, Exception {
		Hashmap();
		File file = new File("output1");
		BufferedReader br = new BufferedReader(new FileReader(file));
		File file2 = new File("doubi");
		//BufferedWriter bw = new BufferedWriter(new FileWriter(file2));
		PrintWriter pw = new PrintWriter(new FileWriter(file2));
		String input;
		while((input=br.readLine())!=null){
			String str5[]=input.split("\t");
			String key = str5[1];
			if (banWord.containsKey(key)){
				pw.write(str5[0]+"\t"+banWord.get(key)+"\n");
				pw.flush();
				
			}
			
		}
		pw.close();
		br.close();
	}
	
	public static void Hashmap() throws SAXException, IOException {
		File file1 = new File("output00.txt");
		
		
		
		BufferedReader br1 = new BufferedReader(new FileReader(file1));
		
		String input;
		while((input=br1.readLine())!=null){
			String[] str4=input.split("\t");
			banWord.put(str4[1], str4[0]);
			
		}
		br1.close();
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
			 if (length1==496){continue;}

			 if (length1==200||length1==287||length1==424||length1==543||length1==559||length1==697||length1==1293||length1==2399){continue;}
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
				pw.print("workflow"+length3+"\t");
				pw.flush();
				for (int i = 0; i < jq; i++) {
					
					//pw.print(a.get(i) + "\t");
					//pw.flush();
					
					
				}
				pw.print("\n");
				pw.flush();
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
				pw.print("workflow"+length3+"\t");
				pw.flush();
				for (int i = 0; i < jq; i++) {
					
					//pw.print(a.get(i) + "\t");
					//pw.flush();
					
				}
				pw.print("\n");
				pw.flush();
				length2++;
				// System.out.println(length2);
				bw = new BufferedWriter(new FileWriter(file2));

			}

		}
       System.out.print(count1+"\t"+count2);
		pw.close();
	}
}
