package genetic;

import java.util.LinkedList;

import Graph.Edge;
import Graph.Node;

import edu.uci.ics.jung.graph.Graph;

public class Genetic
{
  private LinkedList<Indyvidual> oldPop;
  private LinkedList<Indyvidual> tmpPop;
  private LinkedList<Indyvidual> newPop;
  private Graph<Node, Edge> graph;
  int currGener;

  //pola klas
  private PreSelection preSelect;
  private Crossing cross;
  private Mutation mut;
  private Fitness fit;
  private Statistics stat;
  private PostSelection postSelect;

  //pola paramatrow dla klas
  int popSize;
  int nodes;  //ilosc wierzcholkow=wielkosc tablicy
  double mutProb;
  double crossProb;
  int preSelectType;
  int crossType;
  int postSelectType;





  public Genetic(Graph<Node, Edge> _graph, int _popSize, double _mutProb, double _crosProb, int _preSelect,
		  int _crossTyp, int _postSelect)
  {
	graph = _graph;
	nodes = graph.getVertexCount();
	currGener = 0;
	oldPop = new LinkedList<Indyvidual>();

	popSize = _popSize;
	mutProb = _mutProb;
	crossProb = _crosProb;
	preSelectType = _preSelect;
	crossType = _crossTyp;
	postSelectType = _postSelect;

	preSelect = new PreSelection(preSelectType);
	cross = new Crossing(crossType, crossProb, popSize);
	mut = new Mutation(mutProb);
	fit = new Fitness(graph);
	stat = new Statistics();
	postSelect = new PostSelection(postSelectType);

	initPop();
  }

  private void initPop()
  {
	for (int i=0;i<popSize;i++)
	{
	  Indyvidual osobnik = new Indyvidual(nodes);
	  osobnik.init();
	  oldPop.add(osobnik);
	  //osobnik.printTab();
	  fit.fitness(oldPop);
	}
  }

  public void goAlgo()
  {
	tmpPop = preSelect.selection(oldPop);
	if (tmpPop.size()==0)
	  System.out.println("NULL tmpPop");
	newPop=cross.crossing(tmpPop);
	mut.mutate(newPop);
	fit.fitness(newPop);
	stat.findStat(newPop);
	oldPop=postSelect.postSelection(oldPop, newPop);  //postselekcja
	//oldPop = newPop;
  }

  public void printPop()
  {
    for (int i=0;i<popSize;i++)
    {
      newPop.get(i).printTab();
      System.out.println(newPop.get(i).getFitness());
      System.out.println();
    }
  }


}
