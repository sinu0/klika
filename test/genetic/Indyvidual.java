package genetic;

import java.util.Random;

public class Indyvidual
{
  private boolean chromosome[];  //chromosom
  private double fitness;   //dostosowanie osobnika
  private int nodes; //dlugosc chromosomu
  
  public Indyvidual(int _nodes)
  {
	nodes = _nodes;
    chromosome = new boolean [nodes];
    fitness = 0;
  }
  
  public void setChromosome(boolean chrom[])
  {
	chromosome = chrom;	
  }
  
  public boolean[] getChromosome()
  {
	return chromosome;
  }
  
  public void setFitness(double fit)
  {
	fitness = fit;
  }
  
  public double getFitness()
  {
	return fitness;
  }
  
  public void init()
  {
	Random r = new Random();
	for (int i=0;i<nodes;i++)
	{
	  chromosome[i]=r.nextBoolean();
	}
  }
  
  //testowa do wypisywania chromosomu
  public void printTab()
  {
	for (int i=0;i<nodes;i++)
	{
	  if (chromosome[i]==true)
		System.out.print("1 ");
	  else
		System.out.print("0 ");
	}
	System.out.println();
  }
  
  public void printFit()
  {
    System.out.println("Dopasowanie - " + fitness);
  }

}
