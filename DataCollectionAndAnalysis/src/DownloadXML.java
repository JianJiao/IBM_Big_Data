import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class DownloadXML {
	static String title;
	static String type;
	public static void main(String[] args) throws Exception {
		for (int i = 4;i <=1000;i++){
			String dns = "http://www.myexperiment.org/workflow.xml?id=" + i;
			writeLog("\n"+"\n"+"DNS="+dns);
			String dns2 = loadhtml(dns);
			if(dns2 != null){
				String dns3 = analyseXML();
				if(dns3 != null){
				String content = loadhtml(dns3);
		        File file=new File("workflow");
				FileWriter fileWritter = new FileWriter(file,true);
				BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				bufferWritter.write(title+"\n"+type+"\n"+content+"\n"+"\n");
				//bufferWritter.write("heol"+"\n");

				//bufferWritter.write("\n");

				bufferWritter.close();
				//fileWritter.close();
				//System.out.println(content);
				}
			}
		}
	}

	public static String analyseXML() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// Get the DOM Builder
		DocumentBuilder builder = factory.newDocumentBuilder();
		// Load and Parse the XML document
		// document contains the complete XML as a Tree.
        File file=new File("ff.xml");
        BufferedReader bufferedreader = new BufferedReader(new
    				FileReader(file));
        Document document = builder.parse(file);
		NodeList nodeList = document.getDocumentElement().getChildNodes();
		String dns3 = new String();
		dns3 = null;
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			String dns2 = node.getNodeName();
			if(dns2.equals("title")){
				title = null;
				title = node.getTextContent();
				writeLog("\t"+"TITLE="+title);
			}
			if(dns2.equals("content-uri")){
				dns3 = node.getTextContent();
				System.out.println(dns3);
			}
			if(dns2.equals("content-type")){
				dns2 = node.getTextContent();
				type = dns2;
				writeLog("\t"+"CONTENT-TYPE="+dns2);
				dns2 = null;
				//String[] filetype = dns2.split("\\+");
				//if(filetype.length > 1 && filetype[1].equals("zip"))
					//return null;
			}
		}
		//writeLog("\n");
		return dns3;
	}
	public static void writeLog(String data) throws IOException {
		File file=new File("logfile");
		FileWriter fileWritter = new FileWriter(file,true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(data);
		bufferWritter.close();
	}

	public static String loadhtml(String dns) throws IOException {
		URL getRps = new URL(dns);
		URLConnection getRpsConnect = getRps.openConnection();
		HttpURLConnection httpConnection = (HttpURLConnection) getRpsConnect;
		int responseCode = httpConnection.getResponseCode();
		if(responseCode == 401){
			String data = "\t"+"ERROR-TYPE="+"401 Unauthorized";
			writeLog(data);
			return null;
		}
		if(responseCode == 404){
			String data = "\t"+"ERROR-TYPE="+"404 Not Found";
			writeLog(data);
			return null;
		}
		// get the html content
		String content = getHtmlContent(dns);
		//System.out.println(content);
		File file = new File("ff.xml");
		//clear all
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		//write into the html content
		FileWriter fileWritter = new FileWriter(file,true);
		fileWritter.write(content +"\n");
		fileWritter.close();
		return content;
	}

	public static String getHtmlContent(String htmlurl) {
		URL url;
		String temp;
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL(htmlurl);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream(), "gbk"));// 读取网页全部内容
			while ((temp = in.readLine()) != null) {
				sb.append(temp);
			}
			in.close();
		} catch (final MalformedURLException me) {
			System.out.println("你输入的URL格式有问题!");
			me.getMessage();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
