
package gui;

import Graph.Edge;
import Graph.EdgeFactory;
import Graph.Node;
import Graph.VertexFactory;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.util.Random;
import org.apache.commons.collections15.Transformer;

/**
 *
 * 
 */
public class Jung {

    private Graph<Node, Edge> g;
    private VisualizationViewer<Node, Edge> vv; //okno junga
    private EditingModalGraphMouse gm;//edycja grafu
    private VertexFactory newNode = null;
    private EdgeFactory newEdge = null;

    /**
     * Creates a new instance of SimpleGraphView
     */
    public Jung() {
        // Graph<V, E> where V is the type of the vertices and E is the type of the edges
        g = new UndirectedSparseGraph<Node, Edge>();
    }

    /**
     * @param args the command line arguments
     */
    protected void init() {
        newNode = new VertexFactory();
        newEdge = new EdgeFactory();
        Transformer<Node, Paint> vertexColor = new Transformer<Node, Paint>() {
            public Paint transform(Node i) {
                if (i.isSelected() == true) {
                    return Color.GREEN;
                }
                return Color.RED;
            }
        };
        //ustawianie wyglau grafu
        Layout<Node, Edge> layout = new CircleLayout<Node, Edge>(g);
        layout.setSize(new Dimension(100, 100));
        vv = new VisualizationViewer<Node, Edge>(layout);
        vv.setPreferredSize(new Dimension(100, 100));
        //kolorowanie wierzcholkow
        vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
        //koloroawnie wierzcho³kow
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        //ustaweinie etykety na krawedz
        //  vv.getRenderContext().setEdgeLabelTransformer(n )
        //edycja myszka
        gm = new EditingModalGraphMouse(vv.getRenderContext(), newNode, newEdge);
        vv.setGraphMouse(gm);
    }


    //funkcja ktora ustawia mozliwosc dodawania dodatkowych wiercho³kow

    public void changeLayout(int nazwa) {
        switch (nazwa) {
            case 0: {
                vv.setGraphLayout(new FRLayout2<Node, Edge>(g));
                break;
            }
            case 1: {
                vv.setGraphLayout(new KKLayout<Node, Edge>(g));
                break;
            }
            case 2: {
                vv.setGraphLayout(new CircleLayout<Node, Edge>(g));
                break;
            }
            case 3: {
                vv.setGraphLayout(new ISOMLayout<Node, Edge>(g));
                break;
            }        
            case 4: {
                vv.setGraphLayout(new SpringLayout<Node, Edge>(g));
                break;
            }
            case 5: {
                vv.setGraphLayout(new SpringLayout2<Node, Edge>(g));
                break;
            }
            default:
                break;
        }
    }
    //genertor grafu

    public Graph generateGraph(int cliqueSize, int randomNode, int randomEdge) {

        Graph<Node, Edge> newG = new UndirectedSparseGraph<Node, Edge>();
        newEdge.clear();
        newNode.clear();
        Node[] tabNode = new Node[cliqueSize + randomNode];
        //tworzenie kliki
        if (cliqueSize != 0 || cliqueSize != 1) {
            
            
            for (int i = 0; i < cliqueSize; i++) {
                newG.addVertex(newNode.create());
            }


            newG.getVertices().toArray(tabNode);
            for (int i = 1; i < cliqueSize; i++) {
                for (int j = i; j < cliqueSize; j++) {
                    newG.addEdge(newEdge.create(), tabNode[i - 1], tabNode[j],
                            EdgeType.UNDIRECTED);
                }
            }
            
        }
        Random rand = new Random();
        //dodaje wierzocholki oraz tworzy z nim losowa krawedz
        for (int i = 0; i < randomNode; i++) {
            newG.getVertices().toArray(tabNode);
            Node node = newNode.create();
            newG.addVertex(node);
            //if ((cliqueSize==0 & (i!=0 & i!=1)))
            newG.addEdge(newEdge.create(), node, tabNode[rand.nextInt(cliqueSize
                    + i - 1)], EdgeType.UNDIRECTED);

        }
        //tworzenie losowych krawedzi
        for (int i = 0; i < randomEdge; i++) {
            newG.addEdge(newEdge.create(), tabNode[rand.nextInt(cliqueSize +
                    randomNode - 2)], tabNode[rand.nextInt(cliqueSize +
                    randomNode - 2)], EdgeType.UNDIRECTED);
        }

        return newG;
    }

    public Graph<Node, Edge> getG() {
        return g;
    }

    public void setG(Graph<Node, Edge> g) {
        this.g = g;
    }

    public EditingModalGraphMouse getGm() {
        return gm;
    }

    public void setGm(EditingModalGraphMouse gm) {
        this.gm = gm;
    }

    public EdgeFactory getNewEdge() {
        return newEdge;
    }

    public void setNewEdge(EdgeFactory newEdge) {
        this.newEdge = newEdge;
    }

    public VertexFactory getNewNode() {
        return newNode;
    }

    public void setNewNode(VertexFactory newNode) {
        this.newNode = newNode;
    }

    public VisualizationViewer<Node, Edge> getVv() {
        return vv;
    }

    public void setVv(VisualizationViewer<Node, Edge> vv) {
        this.vv = vv;
    }

}