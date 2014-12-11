package graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
 
public class AllNodes {
  public static void main(String[] args) throws IOException {
    DirectedSparseGraph<MyNode, MyLink> g = new DirectedSparseGraph<MyNode, MyLink>();
    AllNodes jl = new AllNodes();
    //read 3 input files
    BufferedReader wfbr = new BufferedReader(new FileReader("workflow.txt"));
    BufferedReader wsbr = new BufferedReader(new FileReader("webservice.txt"));
    BufferedReader egbr = new BufferedReader(new FileReader("edge.txt"));
    BufferedReader popbr = new BufferedReader(new FileReader("pop.txt"));
    BufferedReader rebr = new BufferedReader(new FileReader("rest-id.txt"));
    BufferedReader repopbr= new BufferedReader(new FileReader("popularity.txt"));
    BufferedReader eg2br = new BufferedReader(new FileReader("edge2.txt"));
    String line;
    HashMap<String, MyNode> nodeMap = new HashMap<String, MyNode>();
    MyNode node;
    //for workflows
    while((line = wfbr.readLine())!=null){
    	line = line.trim();
    	node = jl.new MyNode(line);
    	g.addVertex(node);
    	nodeMap.put(line, node);
    }
    //for soap services
    while((line = wsbr.readLine())!=null){
    	line = line.trim();
    	node = jl.new MyNode(line);
    	g.addVertex(node);
    	nodeMap.put(line, node);
    }
    //for rest services
    while((line=rebr.readLine())!=null){
    	line=line.trim();
    	String strReId = line.split("\t")[0];
    	node = jl.new MyNode(strReId);
    	g.addVertex(node);
    	nodeMap.put(strReId, node);
    }
    int i = 1;
    String strWfId;
    String strWsId;
    String strEgId;
    MyNode fromNode, toNode;
    MyLink link;
    //for the saop edges
    while((line = egbr.readLine())!=null){
    	String[] words = line.split("\t");
    	strWfId = words[0];
    	fromNode = (MyNode) nodeMap.get(strWfId);
    	strWsId = words[1];
    	toNode = (MyNode) nodeMap.get(strWsId);
    	strEgId = "Edge" + i;
    	link = jl.new MyLink(strEgId);
    	g.addEdge(link, fromNode, toNode);
    	i++;
    }
    //for the rest edges
    while((line = eg2br.readLine())!=null){
    	String[] words = line.split("\t");
    	strWfId = words[0];
    	fromNode = (MyNode) nodeMap.get(strWfId);
    	strWsId = words[1];
    	toNode = (MyNode) nodeMap.get(strWsId);
    	strEgId = "Edge" + i;
    	link = jl.new MyLink(strEgId);
    	g.addEdge(link, fromNode, toNode);
    	i++;
    }
    // set soap service popularity
    while((line=popbr.readLine())!=null){
    	line = line.trim();
    	String[] words = line.split("\t");
    	strWsId = words[0];
    	int pop = Integer.parseInt(words[1]);
    	node =(MyNode) nodeMap.get(strWsId);
    	node.setPopularity(pop);
    }
    // set rest service popularity
    while((line=repopbr.readLine())!=null){
    	line = line.trim();
    	String[] words = line.split("\t");
    	String strReId = words[0];
    	int pop = Integer.parseInt(words[1]);
    	node =(MyNode) nodeMap.get(strReId);
    	node.setPopularity(pop);
    }
    wfbr.close();
    wsbr.close();
    egbr.close();
    popbr.close();
    rebr.close();
    repopbr.close();
    eg2br.close();
    
    VisualizationViewer<MyNode, MyLink> vv =
      new VisualizationViewer<MyNode, MyLink>(
        new KKLayout<MyNode, MyLink>(g), new Dimension(700,700));
    
    // Set up a new stroke Transformer for the edges
//    float dash[] = {10.0f};
//    final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
//         BasicStroke.JOIN_MITER, 10.0f);
//    Transformer<String, Stroke> edgeStrokeTransformer = new Transformer<String, Stroke>() {
//        public Stroke transform(String s) {
//            return edgeStroke;
//        }
//    };
//    vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
    
//    Transformer<String, String> transformer = new Transformer<String, String>() {
//      @Override public String transform(String arg0) { return arg0; }
//    };
    //vv.getRenderContext().setVertexLabelTransformer(transformer);
//    transformer = new Transformer<String, String>() {
//      @Override public String transform(String arg0) { return arg0; }
//    };
    //vv.getRenderContext().setEdgeLabelTransformer(transformer);
    vv.getRenderer().setVertexRenderer(new MyRenderer());
    
 
    // The following code adds capability for mouse picking of vertices/edges. Vertices can even be moved!
    final DefaultModalGraphMouse<String,Number> graphMouse = new DefaultModalGraphMouse<String,Number>();
    vv.setGraphMouse(graphMouse);
    graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
 
    JFrame frame = new JFrame();
    frame.getContentPane().add(vv);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
 
  static class MyRenderer implements Renderer.Vertex<MyNode, MyLink> {
    @Override public void paintVertex(RenderContext<MyNode, MyLink> rc,
        Layout<MyNode, MyLink> layout, MyNode vertex) {
      GraphicsDecorator graphicsContext = rc.getGraphicsContext();
      Point2D center = layout.transform(vertex);
      Shape shape = null;
      Color color = null;
      String str = vertex.toString().substring(0,2);
      if(str.equals("wo")) {
        shape = new Rectangle((int)center.getX()-2, (int)center.getY()-2, 5, 5);
        color = new Color(127, 127, 0);
      } else if(str.equals("ws")) {
      	int popularity = vertex.getPopularity()+1;
        shape = new Ellipse2D.Double(center.getX()-2, center.getY()-2, popularity, popularity);
        color = new Color(0, 127, 127);
      } else if(str.equals("re")) {
    	  int popularity = vertex.getPopularity()+1;
         shape = new Rectangle((int)center.getX()-2, (int)center.getY()-2, popularity, popularity+5);
         color = new Color(127, 0, 127);
       } 
      graphicsContext.setPaint(color);
      graphicsContext.fill(shape);
    }
  }
  class MyNode{
	  String id;
	  String title;
	  int popularity;
	  
	  public MyNode(String id){
		  this.id=id;
	  }
	  public MyNode(String id, String title){
		  this.id=id;
		  this.title=title;
	  }
	  public MyNode(String id, String title, int popularity){
		  this.id=id;
		  this.title=title;
		  this.popularity=popularity;
	  }
	  
	  public void setId(String id){
		  this.id=id;
	  }
	  public String getId(){
		  return id;
	  }
	  public void setTitle(String title){
		  this.title=title;
	  }
	  public String getTitle(){
		  return title;
	  }
	  public void setPopularity(int popularity){
		  this.popularity=popularity;
	  }
	  public int getPopularity(){
		  return popularity;
	  }
	  
	  public String toString(){
		  return id;
	  }
  }
  
  class MyLink{
	  String id;
	  String title;
	  int width;
	  
	  public MyLink(String id){
		  this.id=id;
	  }
	  public MyLink(String id, String title){
		  this.id=id;
		  this.title=title;
	  }
	  public MyLink(String id, String title, int width){
		  this.id=id;
		  this.title=title;
		  this.width=width;
	  }
	  
	  public void setId(String id){
		  this.id=id;
	  }
	  public String getId(){
		  return id;
	  }
	  public void setTitle(String title){
		  this.title=title;
	  }
	  public String getTitle(){
		  return title;
	  }
	  public void setWidth(int width){
		  this.width=width;
	  }
	  public int getWidth(){
		  return width;
	  }
	  public String toString(){
		  return id;
	  }
	  
  }
}