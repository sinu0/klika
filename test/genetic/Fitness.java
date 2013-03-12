package genetic;

import java.util.Collection;
import java.util.LinkedList;

import Graph.Edge;
import Graph.Node;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import java.util.Collections;
import java.util.Comparator;

public class Fitness
{
  private Graph<Node, Edge> graph;

  public Fitness(Graph<Node, Edge> _graph)
  {
    graph=_graph;
  }



  public void fitness(LinkedList<Indyvidual> pop)
  {
    int popSize = pop.size();
    for (int i=0;i<popSize;i++)
      fitnessOne(pop.get(i));
  }





  private void fitnessOne(Indyvidual ind)
  {
	LinkedList<Integer> nodeList = new LinkedList<Integer>();
	         //lista indeksow wierzcholkow, ktore sa w podgrafie
	int chromSize = ind.getChromosome().length;  //dlugosc chromosomu
	for (int i=0;i<chromSize;i++)  //tworzenie listy numerow wierzcholkow, ktore maja byc w podgrafie
	{
	  if (ind.getChromosome()[i]==true)
	    nodeList.add(i);
	}


	//jesli podgraf nie ma zadnych wierzcholkow
	if (nodeList.size()==0)
	  ind.setFitness(0);


	//jesli jednak jakies wierzcholki sa
	if (nodeList.size()!=0)
	{
	  //podgraf
	  Graph<Node, Edge> subGraph = new UndirectedSparseGraph<Node, Edge>();

	  //dodawanie wierzcholkow
	  int nodes = nodeList.size();  //ilosc wierzcholkow w podgrafie
	  for (int i=0;i<nodes;i++)
	  {
		Node n = new Node(nodeList.get(i));
	    subGraph.addVertex(n);
	  }


	  //dodawanie krawedzi
	  Collection <Node> nodeCol = subGraph.getVertices();  //lista wierzcholkow w grafie
      Object[] nodeTab = nodeCol.toArray();   //tablica wierzcholkow w grafie
	  for (int i=0;i<nodeCol.size();i++)
	  {
	    for (int j=i;j<nodeCol.size();j++)
	    {
	      Edge e = (Edge) graph.findEdge((Node)nodeTab[i], (Node)nodeTab[j]);
	      if (e!=null)
	      {
	        subGraph.addEdge(e, (Node)nodeTab[i], (Node)nodeTab[j], EdgeType.UNDIRECTED);
	      }
	    }
	  }
         
	  //obliczanie ilosci krawedzi
	  int edgeSubFullCount = nodes*(nodes-1)/2;  //ilosc krawedzi w podgrafie, gdyby byl klika
	  int edgeSubCount = subGraph.getEdgeCount();  //ilosc krawedzi w podgrafie
	  int edgeCount = graph.getEdgeCount();  //ilosc krawedzi w grafie
	  int nodeCount = graph.getVertexCount();  //ilosc wierzcholkow w grafie
	  //nodes - ilosc wierzcholkow w podgrafie

	  //czy graf jest klika
	  int dif = edgeSubFullCount - edgeSubCount;  //ilosc brakujacych krawedzi
	  double weight = 1;
	  if (dif==0)
	    weight = 5;
	  //if (dif!=0) weight = 1;

	  //wzor obliczajacy dostosowanie
	  double rating = edgeCount - dif + weight * nodeCount;


	  //dodanie obliczonego dopasowania
	  if (rating<0)
	    rating = 0;
	  ind.setFitness(rating);
	  //System.out.println("Dostosowanie - " + rating);
	}

  }

}
