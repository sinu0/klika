package genetic;

import java.util.LinkedList;

import Graph.Edge;
import Graph.Node;

import edu.uci.ics.jung.graph.Graph;
import gui.Jung;
import gui.Frame;

public class Tester
{

  public static void main(String[] args)
  {
	genTest();
	//preSelectTest();


  }


  private static void preSelectTest()
  {
    int selectType = 1;
    int popSize = 20;
	PreSelection preSel = new PreSelection(selectType);

	Frame fr = new Frame();
	Jung jung = fr.getJung();
	Graph<Node, Edge> graph = jung.generateGraph(5, 10, 10);


	LinkedList<Indyvidual> pop = new LinkedList<Indyvidual>();

	for (int i=0;i<popSize;i++)
	{
	  Indyvidual osobnik = new Indyvidual(20);
	  osobnik.init();
	  pop.add(osobnik);
	}


	Fitness fit = new Fitness(graph);
	fit.fitness(pop);


	for (int i=0;i<popSize;i++)
	{
	  pop.get(i).printFit();
	}
	LinkedList<Indyvidual> newPop = preSel.selection(pop);
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println("Populcja po preselekcji");
	for (int i=0;i<popSize;i++)
	{
	  newPop.get(i).printFit();
	}
  }

  private static void genTest()
  {
	int popSize=200;
	double mutProb=0.05;
	double crosProb=0.7;
	int preSelect=1;
	int crossTyp=1;
	int postSelect=1;

	Frame fr = new Frame();
	Jung jung = fr.getJung();
	Graph<Node, Edge> graph = jung.generateGraph(5, 10, 10);



	Genetic gen = new Genetic(graph, popSize, mutProb, crosProb, preSelect, crossTyp, postSelect);
	for (int i=0;i<100;i++)
    gen.goAlgo();
	//gen.printPop();
	System.out.println("Done");
	System.out.println("Wierzcholkow - " + graph.getVertexCount());


  }

  private static void mutTest()
  {
    int popSize = 2;
	double mutationProb = 0.05;
	Mutation mut = new Mutation(mutationProb);

	LinkedList<Indyvidual> pop = new LinkedList<Indyvidual>();
	for (int i=0;i<popSize;i++)
	{
	  Indyvidual osobnik = new Indyvidual(20);
	  osobnik.init();
	  pop.add(osobnik);
	  osobnik.printTab();
	}

	mut.mutate(pop);

	for (int i=0;i<popSize;i++)
	  pop.get(i).printTab();
  }

  private static void crosTest()
  {
    int popSize = 2;
	double crossingProb = 0.8;
	int crossingType = 4;

	LinkedList<Indyvidual> pop = new LinkedList<Indyvidual>();
	for (int i=0;i<popSize;i++)
	{
	  Indyvidual osobnik = new Indyvidual(20);
	  osobnik.init();
	  pop.add(osobnik);
	  osobnik.printTab();
	}
	Crossing cros = new Crossing(crossingType,crossingProb,popSize);
	LinkedList<Indyvidual> newPop = cros.crossing(pop);
	System.out.println("Nowa populacja");
	for (int i=0;i<popSize;i++)
	  newPop.get(i).printTab();
  }

}
