package genetic;

import java.util.LinkedList;

import Graph.Edge;
import Graph.Node;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class Fitness {

    private Graph<Node, Edge> graph;
    private Node[] nodeTab;
    private int HashTab[]; //tablica hashujaca wierzcho³ki w grafie
    private int nodeCount; //ilosc wierzcholkow w grafie
    private int edgeCount; //ilosc krawedzi w grafie

    public Fitness(Graph<Node, Edge> _graph) {
        graph = _graph;
        edgeCount = graph.getEdgeCount();
        nodeCount = graph.getVertexCount();
        nodeTab = new Node[graph.getVertexCount()];
        graph.getVertices().toArray(nodeTab);
        HashTab = new int[graph.getVertexCount()];
        for (int j = 0; j < graph.getVertexCount(); j++) {
            HashTab[nodeTab[j].getId()] = j;

        }
    }

    public void fitness(LinkedList<Indyvidual> pop) {
        int popSize = pop.size();
        for (int i = 0; i < popSize; i++) {
            fitnessOne(pop.get(i));
        }
    }

    private void fitnessOne(Indyvidual ind)
    {
      Graph<Node, Edge> subGraph = new UndirectedSparseGraph<Node, Edge>();
      for (int i = 0; i < ind.getChromosome().length; i++) {
            if (ind.getChromosome()[i] == true) {
                subGraph.addVertex(new Node(i));

            }
        }

        if (subGraph.getVertexCount() > 2) {

            Node[] nodeTab2 = new Node[subGraph.getVertexCount()];
            subGraph.getVertices().toArray(nodeTab2);  //tablica nowych wierzcholkow



            for (int i = 0; i < nodeTab2.length; i++) {

                for (int j = i + 1; j < nodeTab2.length; j++) {

                    Edge e = (Edge) graph.findEdge(nodeTab[HashTab[nodeTab2[i].getId()]], nodeTab[HashTab[nodeTab2[j].getId()]]);
                    if (e != null) {
                        //System.out.println("Dodawanie krawedzi");
                        subGraph.addEdge(e, nodeTab2[i], nodeTab2[j], EdgeType.UNDIRECTED);
                    }
                }
                // System.out.println();
            }

            //obliczanie ilosci krawedzi
            int subNodeCount = subGraph.getVertexCount();
            int edgeSubFullCount = subNodeCount * (subNodeCount - 1) / 2;  //ilosc krawedzi w podgrafie, gdyby byl klika
            int edgeSubCount = subGraph.getEdgeCount();  //ilosc krawedzi w podgrafie
            int dif = edgeSubFullCount - edgeSubCount;  //ilosc brakujacych krawedzi w podgrafie do bycia klika

            //ilosc wierzcholkow w podgrafie
            //nodes - ilosc wierzcholkow w podgrafie


            //czy graf jest klika
            double weight;
            //w zaleznosci od tego czy podgraf jest klika czy nie dastaje odpowiednia
            //ilosc punktow
            if (dif == 0) {
                weight = 100 * subNodeCount * edgeSubCount;
            } else {
                //jezeli graf nie jest klika to punktowany podgraf jest ten ktory
                //posiada jak najmniej wierzcho³kow z jak najwieksza lisoscia krawedzi
                //czyli punktowany jes podgraf ktory jest najblizszy klice
                weight =  10*(nodeCount - subNodeCount) * edgeSubCount / (dif * subNodeCount);
            }

            //wzor obliczajacy dostosowanie
            double rating = weight * subNodeCount;


            if (rating < 0) {
                rating = 0;
            }

            ind.setFitness(rating);

        }

    }

    public int[] getHashTab() {
        return HashTab;
    }

    public Node[] getNodeTab() {
        return nodeTab;
    }
}
