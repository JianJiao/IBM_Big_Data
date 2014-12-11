/*
 * SimpleGraphView.java
 *
 * Created on March 8, 2007, 7:49 PM; Updated May 29, 2007
 *
 * Copyright March 8, 2007 Grotto Networking
 */

package graph;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

/**
 *
 * @author Dr. Greg M. Bernstein
 */
public class SimpleGraphView {
    Graph<String, String> g;
    /** Creates a new instance of SimpleGraphView 
     * @throws IOException */
    public SimpleGraphView() throws IOException {
        // Graph<V, E> where V is the type of the vertices and E is the type of the edges
        g = new SparseMultigraph<String, String>();
        // Add some vertices. From above we defined these to be type Integer.
        BufferedReader egbr = new BufferedReader(new FileReader("WfToWf.txt"));
        String line;
        int i=1;
        while((line=egbr.readLine())!=null){
        	line=line.trim();
        	String[] words=line.split("\t");
        	String fromNode=words[0];
        	String toNode=words[1];
        	g.addVertex(fromNode);
        	g.addVertex(toNode);
        	g.addEdge("Edge-"+i,fromNode, toNode);
        	i++;
        }
    }
    

    public static void main(String[] args) throws IOException {
        SimpleGraphView sgv = new SimpleGraphView(); //We create our graph in here
        // The Layout<V, E> is parameterized by the vertex and edge types
        Layout<String, String> layout = new KKLayout(sgv.g);
        layout.setSize(new Dimension(700,700)); // sets the initial size of the layout space
        // The BasicVisualizationServer<V,E> is parameterized by the vertex and edge types
        BasicVisualizationServer<String,String> vv = new BasicVisualizationServer<String,String>(layout);
        vv.setPreferredSize(new Dimension(750,750)); //Sets the viewing area size
        
        JFrame frame = new JFrame("Simple Graph View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv); 
        frame.pack();
        frame.setVisible(true);       
    }
    
}