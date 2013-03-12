package genetic;

import java.util.Collections;
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
	//genTest();
	//preSelectTest();
	//mutTest();
	//crosTest();
	  postSelect();


  }

  
  private static void postSelect()
  {
    int postSelectType = 2;
    int popSize=10;
    PostSelection postSelect = new PostSelection(postSelectType);
    Komparator<Indyvidual> komp = new Komparator<Indyvidual>();
    Frame fr = new Frame();
	Jung jung = fr.getJung();
	Graph<Node, Edge> graph = jung.generateGraph(5, 10, 10);
    Fitness fit = new Fitness(graph);
	    
    
    LinkedList<Indyvidual> pop = new LinkedList<Indyvidual>();
    for (int i=0;i<popSize;i++)
	{
	  Indyvidual osobnik = new Indyvidual(15);
	  osobnik.init();
	  pop.add(osobnik);
	}
    fit.fitness(pop);
    Collections.sort(pop, komp);
	
    
    LinkedList<Indyvidual> pop2 = new LinkedList<Indyvidual>();
    for (int i=0;i<popSize;i++)
	{
	  Indyvidual osobnik = new Indyvidual(15);
	  osobnik.init();
	  pop2.add(osobnik);
	}
    fit.fitness(pop2);
    Collections.sort(pop2, komp);
    
    
	
	
	for (int i=0;i<popSize;i++)
	{
	  System.out.println("Pop " + pop.get(i).getFitness());
	}
	System.out.println();
	
	for (int i=0;i<popSize;i++)
	{
	  System.out.println("Pop2 " + pop2.get(i).getFitness());
	}
	System.out.println();
	
	LinkedList<Indyvidual> pop3 = postSelect.postSelection(pop, pop2);
	
	for (int i=0;i<popSize;i++)
	{
	  System.out.println("Pop3 " + pop3.get(i).getFitness());
	}
	
  }

  private static void preSelectTest()
  {
    int selectType = 2;
    int popSize = 8;
	PreSelection preSel = new PreSelection(selectType);

	Frame fr = new Frame();
	Jung jung = fr.getJung();
	Graph<Node, Edge> graph = jung.generateGraph(5, 10, 10);


	LinkedList<Indyvidual> pop = new LinkedList<Indyvidual>();

	for (int i=0;i<popSize;i++)
	{
	  Indyvidual osobnik = new Indyvidual(15);
	  osobnik.init();
	  pop.add(osobnik);
	}

	//System.out.println("popSize" + pop.size());
	Fitness fit = new Fitness(graph);
	fit.fitness(pop);
	//System.out.println("ok2");


	for (int i=0;i<popSize;i++)
	{
	  //System.out.println("Lol");
	  //pop.get(i).printFit();
	}
	//System.out.println("przed selekcja");
	LinkedList<Indyvidual> newPop = preSel.selection(pop);
	//System.out.println("po selekcja");
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println("Populcja po preselekcji " + popSize);
	for (int i=0;i<popSize;i++)
	{
	  //newPop.get(i).printFit();
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



	//Genetic gen = new Genetic(graph, popSize, mutProb, crosProb, preSelect, crossTyp, postSelect, null);
	for (int i=0;i<100;i++)
   // gen.goAlgo();
	//gen.printPop();
	System.out.println("Done");
	System.out.println("Wierzcholkow - " + graph.getVertexCount());


  }

  private static void mutTest()
  {
    int popSize = 1;
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
    int popSize = 3;
	double crossingProb = 0.8;
	int crossingType = 1;

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
