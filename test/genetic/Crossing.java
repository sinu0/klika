package genetic;

import java.util.LinkedList;
import java.util.Random;

public class Crossing
{
  private int crossingType;
  private int count;
  private double crossingProb;
  private int popSize;
  
  public Crossing (int type, double prob, int size)
  {
	crossingType = type;
	count=0;
	crossingProb = prob;
	popSize = size;
  }
  
  public void setCrossingType(int type)
  {
	crossingType = type;
  }
  
  public int getCount()
  {
	return count;
  }
  
  public void setCrossingProb(double prob)
  {
	crossingProb = prob;
  }
  
  public LinkedList<Indyvidual> crossing(LinkedList<Indyvidual> pop)
  {
	LinkedList<Indyvidual> newPop = null;
	count=0;
	switch (crossingType)
	{
	  case 1:
	    {
	      newPop = onePointTwoChildren(pop);
		  break;
	    }
	  case 2:
	    {
	      newPop = onePointOneChild(pop);
	      break;		  
	    }
	  case 3:
	    {
	      newPop = manyPointsOneChild(pop);
		  break;
	    }
	  case 4:
	    {
	      newPop = manyPointsTwoChildren(pop);
	      break;
	    }
	}
	return newPop;
  }
  
  private LinkedList<Indyvidual> onePointTwoChildren(LinkedList<Indyvidual> pop)
  {
	int nodes = pop.get(0).getChromosome().length;  //dlugosc tablicy chromosomow
	Random r = new Random();
	int div = r.nextInt(nodes);  //punkt podzialu
	int size = 0;  //rozmiar aktualnej,nowej populacji
	//System.out.println("Podzial - " + div);
	LinkedList<Indyvidual> newPop = new LinkedList<Indyvidual>();  //lista na nowa populacje
	while (size<popSize)
	{
	  double prob = r.nextDouble();
	  if (prob<=crossingProb)
	  {
		count++;
	    int par1 = r.nextInt(popSize);
	    int par2 = r.nextInt(popSize);
	    boolean[] chrom1 = new boolean[nodes];
	    boolean[] chrom2 = new boolean[nodes];
	    for (int i=0;i<div;i++)
	      chrom1[i]=pop.get(par1).getChromosome()[i];
	    for (int i=div;i<nodes;i++)
		  chrom1[i]=pop.get(par2).getChromosome()[i];
	    for (int i=0;i<div;i++)
		  chrom2[i]=pop.get(par2).getChromosome()[i];
	    for (int i=div;i<nodes;i++)
		  chrom2[i]=pop.get(par1).getChromosome()[i];
	    Indyvidual child1 = new Indyvidual(nodes);
	    child1.setChromosome(chrom1);
	    Indyvidual child2 = new Indyvidual(nodes);
	    child2.setChromosome(chrom2);
	    if (size<popSize)
	    {
		  newPop.add(child1);
		  size++;
	    }
	    if (size<popSize)
	    {
		  newPop.add(child2);
		  size++;
	    }
	  }
	}
	return newPop;
  }
  
  private LinkedList<Indyvidual> onePointOneChild(LinkedList<Indyvidual> pop)
  {
    int nodes = pop.get(0).getChromosome().length;  //dlugosc tablicy chromosomow
	Random r = new Random();
	int div = r.nextInt(nodes);  //punkt podzialu
	int size = 0;  //rozmiar aktualnej,nowej populacji
	System.out.println("Podzial - " + div);
	LinkedList<Indyvidual> newPop = new LinkedList<Indyvidual>();  //lista na nowa populacje
	while (size<popSize)
	{
	  double prob = r.nextDouble();
	  if (prob<=crossingProb)
	  {
		count++;
	    int par1 = r.nextInt(popSize);
		int par2 = r.nextInt(popSize);
		boolean[] chrom = new boolean[nodes];
		for (int i=0;i<div;i++)
		  chrom[i]=pop.get(par1).getChromosome()[i];
		for (int i=div;i<nodes;i++)
		  chrom[i]=pop.get(par2).getChromosome()[i];
		Indyvidual child = new Indyvidual(nodes);
		child.setChromosome(chrom);
		newPop.add(child);
		size++;
	  }
	}
	return newPop;
  }
  
  private LinkedList<Indyvidual> manyPointsOneChild(LinkedList<Indyvidual> pop)
  {
    int nodes = pop.get(0).getChromosome().length;  //dlugosc tablicy chromosomow
	Random r = new Random();
	int size = 0;  //rozmiar aktualnej,nowej populacji
	LinkedList<Indyvidual> newPop = new LinkedList<Indyvidual>();  //lista na nowa populacje
	while (size<popSize)
	{
      double prob = r.nextDouble();
	  if (prob<=crossingProb)
	  {
	    count++;
		int par1 = r.nextInt(popSize);
		int par2 = r.nextInt(popSize);
		boolean[] chrom = new boolean[nodes];
		for (int i=0;i<nodes;i++)
		{
		  boolean choice = r.nextBoolean();
		  if (choice==false)
		    chrom[i]=pop.get(par1).getChromosome()[i];
		  if (choice==true)
			chrom[i]=pop.get(par2).getChromosome()[i];		  
		}
		Indyvidual child = new Indyvidual(nodes);
		child.setChromosome(chrom);
		newPop.add(child);
		size++;
	  }	  
	}
	return newPop;
  }
  
  private LinkedList<Indyvidual> manyPointsTwoChildren(LinkedList<Indyvidual> pop)
  {
    int nodes = pop.get(0).getChromosome().length;  //dlugosc tablicy chromosomow
	Random r = new Random();
	int size = 0;  //rozmiar aktualnej,nowej populacji
	LinkedList<Indyvidual> newPop = new LinkedList<Indyvidual>();  //lista na nowa populacje
	while (size<popSize)
	{
	  double prob = r.nextDouble();
	  if (prob<=crossingProb)
	  {
	    count++;
		int par1 = r.nextInt(popSize);
		int par2 = r.nextInt(popSize);
		boolean[] chrom1 = new boolean[nodes];
		boolean[] chrom2 = new boolean[nodes];
		for (int i=0;i<nodes;i++)
		{
		  boolean choice = r.nextBoolean();
		  if (choice==false)
		  {
		    chrom1[i]=pop.get(par1).getChromosome()[i];
		    chrom2[i]=pop.get(par2).getChromosome()[i];
		  }
		  if (choice==true)
		  {
			chrom1[i]=pop.get(par2).getChromosome()[i];
			chrom2[i]=pop.get(par1).getChromosome()[i];
		  }
		}
		Indyvidual child1 = new Indyvidual(nodes);
	    child1.setChromosome(chrom1);
	    Indyvidual child2 = new Indyvidual(nodes);
	    child2.setChromosome(chrom2);
	    if (size<popSize)
	    {
		  newPop.add(child1);
		  size++;
	    }
	    if (size<popSize)
	    {
		  newPop.add(child2);
		  size++;
	    }
		
	  }
	}
	return newPop;
  }
  
  

}
