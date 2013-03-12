package genetic;

import java.util.LinkedList;

public class Statistics
{
  private LinkedList<Double> minList;
  private LinkedList<Double> avgList;
  private LinkedList<Double> maxList;


  public Statistics()
  {
	minList = new LinkedList<Double>();
	avgList = new LinkedList<Double>();
	maxList = new LinkedList<Double>();
  }



  public LinkedList<Double> getMinList()
  {
	return minList;
  }

  public LinkedList<Double> getAvgList()
  {
	return avgList;
  }

  public LinkedList<Double> getMaxList()
  {
	return maxList;
  }

  public void findStat(LinkedList<Indyvidual> pop)
  {
	findMin(pop);
	findAvg(pop);
	findMax(pop);
  }

  private void findMin(LinkedList<Indyvidual> pop)
  {
	double min=pop.get(0).getFitness();
    for (int i=1;i<pop.size();i++)
    {
      if (pop.get(i).getFitness()<min)
        min=pop.get(i).getFitness();
    }
    System.out.println(min);
    minList.add(min);
  }

  private void findAvg(LinkedList<Indyvidual> pop)
  {
    double sum=0;
    for (int i=0;i<pop.size();i++)
    {
      sum+=pop.get(i).getFitness();
    }
    double avg = sum/pop.size();
    System.out.println(avg);
    avgList.add(avg);

  }

  private void findMax(LinkedList<Indyvidual> pop)
  {
    double max=pop.get(0).getFitness();
	for (int i=1;i<pop.size();i++)
	{
	  if (pop.get(i).getFitness()>max)
	    max=pop.get(i).getFitness();
	}
	System.out.println(max);
	System.out.println();
	maxList.add(max);
  }

}
