/*
 * To change this template, choose Tools | Templates
 * and Io the template in the editor.
 */
package GraphIO;

import Graph.Edge;
import Graph.EdgeFactory;
import Graph.Node;
import Graph.VertexFactory;
import edu.uci.ics.jung.graph.Graph;


import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.io.PajekNetReader;
import edu.uci.ics.jung.io.PajekNetWriter;
import gui.Jung;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author -
 */
public class Io {

    public static Graph getGraph(File file,Jung jung) throws IOException {

        jung.setNewEdge(new EdgeFactory());
        jung.setNewNode(new VertexFactory());
        PajekNetReader< Graph<Node,Edge>,Node,Edge> pnr =
                new PajekNetReader< Graph<Node,Edge>,Node,Edge>(jung.getNewNode(), jung.getNewEdge());
        Graph<Node,Edge> g =
                new UndirectedSparseGraph<Node, Edge>();
        pnr.load(file.getPath(), g);
        return g;

    }

    public static void saveGraph(Graph<Node, Edge> g, String file) throws IOException {
        PajekNetWriter<Node,Edge> pnw=new PajekNetWriter();
        pnw.save(g, file);
    }
}
