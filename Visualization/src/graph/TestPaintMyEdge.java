package graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.Polygon;

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
 
public class TestPaintMyEdge {
  static int edgeCount = 0;

  
	
  public static void main(String[] args) {
    DirectedSparseGraph<MyNode, MyLink> g = new DirectedSparseGraph<MyNode, MyLink>();
    TestPaintMyEdge tst = new TestPaintMyEdge();
    MyNode n1, n2, n3, n4, n5;
    n1 = tst.new MyNode(1,"Square"); n2 = tst.new MyNode(9,"Rectangle"); n3 = tst.new MyNode(3,"Circle");  
    n4 = tst.new MyNode(4,"Square"); n5 = tst.new MyNode(5,"Circle");
    g.addVertex(n1);
    g.addVertex(n2);
    g.addVertex(n3);
    g.addEdge(tst.new MyLink(2.0,48),n1,n2);
    g.addEdge(tst.new MyLink(2.0,48),n3,n1);
//    g.addVertex("Square");
//    g.addVertex("Rectangle");
//    g.addVertex("Circle");
//    g.addEdge("Edge1", "Square", "Rectangle");
//    g.addEdge("Edge2", "Square", "Circle");
//    g.addEdge("Edge3", "Circle", "Square");
    KKLayout<MyNode,MyLink> layout=new KKLayout<MyNode,MyLink>(g);
    layout.setExchangeVertices(true);
    layout.setAdjustForGravity(false);
    layout.setDisconnectedDistanceMultiplier(2.0);
    layout.setLengthFactor(1.0);
    layout.setSize(new Dimension(350,350));
    VisualizationViewer<MyNode, MyLink> vv =
      new VisualizationViewer<MyNode, MyLink>(
        layout, new Dimension(600,600));
//    Transformer<String, String> transformer = new Transformer<String, String>() {
//      @Override public String transform(String arg0) { return arg0; }
//    };
//    vv.getRenderContext().setVertexLabelTransformer(transformer);
//    transformer = new Transformer<String, String>() {
//      @Override public String transform(String arg0) { return arg0; }
//    };
//    vv.getRenderContext().setEdgeLabelTransformer(transformer);
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
      String vShape = vertex.getShape();
      System.out.println(vShape);
      if(vShape.equals("Square")) {
    	System.out.println("Square");
        shape = new Rectangle((int)center.getX()-10, (int)center.getY()-10, 20, 20);
        color = new Color(127, 127, 0);
      } else if(vShape.equals("Rectangle")) {
    	 System.out.println("Rectangle");
        shape = new Rectangle((int)center.getX()-10, (int)center.getY()-20, 20, 40);
        color = new Color(127, 0, 127);
      } else if(vShape.equals("Circle")) {
        shape = new Ellipse2D.Double(center.getX()-10, center.getY()-10, 20, 20);
        color = new Color(0, 127, 127);
      }
      graphicsContext.setPaint(color);
      graphicsContext.fill(shape);
    } 
  }
  
  class MyNode {
      int id;
      String shape;
      public MyNode(int id) {
          this.id = id;
      }
      public MyNode(int id, String shape){
      	this.id = id;
      	this.shape = shape;
      }
      
      public void setShape(String shape){
      	this.shape = shape;
      }
      
      public String getShape(){
      	return shape;
      }
      
      public int getId(){
      	return id;
      }
      
      public String toString() {
          return "V"+id;
      }        
  }
  
  class MyLink {
      double capacity;
      double weight;
      int id;
      
      public MyLink(double weight, double capacity) {
          this.id = edgeCount++;
          this.weight = weight;
          this.capacity = capacity;
      } 

      public String toString() {
          return "E"+id;
      }
      
  }
}