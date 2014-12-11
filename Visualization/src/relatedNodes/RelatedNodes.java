package relatedNodes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
 
public class RelatedNodes {
  public static void main(String[] args) throws IOException {
    DirectedSparseGraph<MyNode, MyLink> g = new DirectedSparseGraph<MyNode, MyLink>();
    RelatedNodes rn = new RelatedNodes();
    BufferedReader egbr = new BufferedReader(new FileReader("edge.txt"));
    BufferedReader popbr = new BufferedReader(new FileReader("pop.txt"));
    BufferedReader repopbr= new BufferedReader(new FileReader("popularity.txt"));
    BufferedReader eg2br = new BufferedReader(new FileReader("edge2.txt"));
    String line;
    HashMap<String, MyNode> nodeMap = new HashMap<String, MyNode>();
    MyNode node;

    int i = 1;
    String strWfId;
    String strWsId;
    String strEgId;
    MyNode fromNode, toNode;
    MyLink link;
    //for the saop edges
    while((line = egbr.readLine())!=null){
    	line = line.trim();
    	String[] words = line.split("\t");
    	strWfId = words[0];
    	strWsId = words[1];
    	if (!nodeMap.containsKey(strWfId)) {
    	fromNode = rn.new MyNode(strWfId);
    	nodeMap.put(strWfId, fromNode);
    	}
    	else {
    		fromNode = nodeMap.get(strWfId);
    	}
    	if (!nodeMap.containsKey(strWsId)) {
    	toNode = rn.new MyNode(strWsId);
    	nodeMap.put(strWsId, toNode);
    	}
    	else {
    		toNode = nodeMap.get(strWsId);
    	}
    	strEgId = "Edge" + i;
    	link = rn.new MyLink(strEgId);
    	g.addEdge(link, fromNode, toNode);
    	i++;
    }
    //for the rest edges
    while((line = eg2br.readLine())!=null){
    	line = line.trim();
    	String[] words = line.split("\t");
    	strWfId = words[0];
    	strWsId = words[1];
    	if (!nodeMap.containsKey(strWfId)) {
    	fromNode = rn.new MyNode(strWfId);
    	nodeMap.put(strWfId, fromNode);
    	}
    	else {
    		fromNode = nodeMap.get(strWfId);
    	}
    	if (!nodeMap.containsKey(strWsId)) {
    	toNode = rn.new MyNode(strWsId);
    	nodeMap.put(strWsId, toNode);
    	}
    	else {
    		toNode = nodeMap.get(strWsId);
    	}
    	strEgId = "Edge" + i;
    	link = rn.new MyLink(strEgId);
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

    egbr.close();
    popbr.close();
    repopbr.close();
    eg2br.close();
    
//    KKLayout<MyNode, MyLink> layout = new KKLayout<MyNode, MyLink>(g);
//    layout.setExchangeVertices(true);
//    layout.setAdjustForGravity(false);
//    layout.setLengthFactor(2.4);
//    layout.setDisconnectedDistanceMultiplier(0.8);
//    layout.setMaxIterations(2000);
//    layout.setSize(new Dimension(1000,800));
//    SpringLayout<MyNode, MyLink> prelayout = new SpringLayout<MyNode, MyLink>(g);
//    prelayout.setForceMultiplier(1.0/3.0);
//    prelayout.setRepulsionRange(100);
//    prelayout.setStretch(0.7);
//    Layout<MyNode, MyLink> layout = new AggregateLayout<MyNode, MyLink>(prelayout);
//    layout.setSize(new Dimension(500,500));
    
    FRLayout<MyNode, MyLink> layout = new FRLayout<MyNode, MyLink>(g);
    layout.setRepulsionMultiplier(0.8);
    layout.setAttractionMultiplier(6);
    layout.setSize(new Dimension(1000,1000));
    
    VisualizationViewer<MyNode, MyLink> vv = new VisualizationViewer<MyNode, MyLink>(layout);
    vv.getRenderer().setVertexRenderer(new MyRenderer());
    vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
    vv.setPreferredSize(new Dimension(1850,1050));
    
//    vv.setBounds(10, 10, 10, 10);
//    vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller() {
//        public String transform(MyNode node) {
//            return node.id;
//        }});
    
    GraphZoomScrollPane pane = new GraphZoomScrollPane(vv);
 
    // The following code adds capability for mouse picking of vertices/edges. Vertices can even be moved!
    DefaultModalGraphMouse<MyNode, MyLink> graphMouse = new DefaultModalGraphMouse<MyNode, MyLink>();
    vv.setGraphMouse(graphMouse);
    graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
//    graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
 
    JFrame frame = new JFrame();
    frame.getContentPane().add(vv);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    frame.add(pane);
    frame.pack();
    frame.setVisible(true);
  }
 
  static class MyRenderer implements Renderer.Vertex<MyNode, MyLink> {
    @Override
    public void paintVertex(RenderContext<MyNode, MyLink> rc,
       Layout<MyNode, MyLink> layout, MyNode vertex) {
      GraphicsDecorator graphicsContext = rc.getGraphicsContext();
      Point2D center = layout.transform(vertex);
      Shape shape = null;
      Color color = null;
      String str = vertex.toString().substring(0,2);
      if(str.equals("wo")) {
        shape = new Rectangle((int)center.getX()-5, (int)center.getY()-5, 10, 10);
//    	  shape = new Rectangle((int)center.getX()-3, (int)center.getY()-3, 6, 6);
        color = new Color(127, 127, 0);
      } else if(str.equals("ws")) {
      	int popularity = vertex.getPopularity()+10;
        shape = new Ellipse2D.Double(center.getX()-(popularity/2), center.getY()-(popularity/2), popularity, popularity);
        color = new Color(0, 127, 127);
      } else if(str.equals("re")) {
    	  int popularity = vertex.getPopularity()+10;
         shape = new Rectangle((int)center.getX()-(popularity*2/5), (int)center.getY()-((popularity)/2), popularity*4/5, popularity);
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