package genetic;

import java.util.LinkedList;
import java.util.Random;

public class Mutation
{
  private double mutationProb;
  private int count;
  
  
  public Mutation(double prob)
  {
	mutationProb = prob;
	count=0;
  }
  
  public void setMutationProb(double prob)
  {
    mutationProb = prob;
  }
  
  private void mutateOne(Indyvidual ind)
  {
    boolean[] chrom = ind.getChromosome();
    int nodes = chrom.length;
    Random r = new Random();
    for (int i=0;i<nodes;i++)
    {
      double prob = r.nextDouble();
      if (prob<=mutationProb)
      {
        chrom[i]=!chrom[i];
        count++;
      }
    }
    //System.out.println("Mutowan - " + count);
  }
  
  public void mutate(LinkedList<Indyvidual> pop)
  {
    int size = pop.size();
    for (int i=0;i<size;i++)
      mutateOne(pop.get(i));
  }

  
}
