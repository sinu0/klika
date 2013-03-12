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
	//System.out.println("Crossing popsize " + pop.size() + " typ " + crossingType);
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
	int sizeToRandom = pop.size();
	int nodes = pop.get(0).getChromosome().length;  //dlugosc tablicy chromosomow
	Random r = new Random();
	int div = r.nextInt(nodes);  //punkt podzialu
	int size = 0;  //rozmiar aktualnej,nowej populacji
	int iter = 0;
	//System.out.println("Podzial - " + div);
	LinkedList<Indyvidual> newPop = new LinkedList<Indyvidual>();  //lista na nowa populacje
	//System.out.println("Onepoint poopSize " + pop.size() + " Popsize " + popSize);
	while (size<popSize)
	{
	  //System.out.println("Petla");
	  double prob = r.nextDouble();
	  if (prob<=crossingProb)
	  {
		count++;

		int par1 = iter;
	    int par2 = iter+1;
		if (iter==sizeToRandom-1)
		{
	      par2=0;
	      iter=-1;
		}
		
		//System.out.println("Par2 " + par2);
	    boolean[] chrom1 = new boolean[nodes];
	    boolean[] chrom2 = new boolean[nodes];
	    //System.out.println("Przed kopiowaniem");
                System.arraycopy(pop.get(par1).getChromosome(), 0, chrom1, 0, div);
                //System.out.println("Przed kopiowaniem 2");
                System.arraycopy(pop.get(par2).getChromosome(), div, chrom1, div, nodes - div);
                System.arraycopy(pop.get(par2).getChromosome(), 0, chrom2, 0, div);
                System.arraycopy(pop.get(par1).getChromosome(), div, chrom2, div, nodes - div);
        //System.out.println("Srodek");
	    Indyvidual child1 = new Indyvidual(nodes);
	    child1.setChromosome(chrom1);
	    Indyvidual child2 = new Indyvidual(nodes);
	    child2.setChromosome(chrom2);
	    
	    if (size<popSize)
	    {
		  newPop.add(child1);
		  size++;
		  //System.out.println("Dodaje 1 - size "+ size);
	    }
	    if (size<popSize)
	    {
		  newPop.add(child2);
		  size++;
		  //System.out.println("Dodaje 2 - size "+ size);
	    }
	    iter++;
	    //System.out.println("Przed koncem warunku - iter " + iter);
	  }
	  //System.out.println("Po warunku");
	}
	//System.out.println("czy zwraca");
	return newPop;
  }

  private LinkedList<Indyvidual> onePointOneChild(LinkedList<Indyvidual> pop)
  {
	int sizeToRandom = pop.size();
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
		int par1 = r.nextInt(sizeToRandom);
		int par2 = r.nextInt(sizeToRandom);
		boolean[] chrom = new boolean[nodes];
                System.arraycopy(pop.get(par1).getChromosome(), 0, chrom, 0, div);
                System.arraycopy(pop.get(par2).getChromosome(), div, chrom, div, nodes - div);
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
	int sizeToRandom = pop.size();
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
	    int par1 = r.nextInt(sizeToRandom);
		int par2 = r.nextInt(sizeToRandom);
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
	int sizeToRandom = pop.size();
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
	    int par1 = r.nextInt(sizeToRandom);
		int par2 = r.nextInt(sizeToRandom);
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
