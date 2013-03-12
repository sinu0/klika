package genetic;

import gui.JPanelChart;

import java.util.LinkedList;

public class Statistics
{
  //private LinkedList<Double> minList;
  //private LinkedList<Double> avgList;
  //private LinkedList<Double> maxList;
  //private int maxInd;  //indeks w populacji osobnika o najwyzszym przystosowaniu
  private JPanelChart panel;
  double minFit;
  double avgFit;
  double maxFit;


  public Statistics(JPanelChart _panel)
  {
	//minList = new LinkedList<Double>();
	//avgList = new LinkedList<Double>();
	//maxList = new LinkedList<Double>();
	panel = _panel;
  }

  /*
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
  */

  public void findStat(LinkedList<Indyvidual> pop)
  {
	findMin(pop);
	findAvg(pop);
	findMax(pop);
  }

  private void findMin(LinkedList<Indyvidual> pop)
  {
	minFit=pop.getLast().getFitness();
	/*
    for (int i=1;i<pop.size();i++)
    {
      if (pop.get(i).getFitness()<min)
        min=pop.get(i).getFitness();
    }
    */
   // System.out.println("Fitness Min " + minFit);
    //minList.add(min);
  }

  private void findAvg(LinkedList<Indyvidual> pop)
  {
    double sum=0;
    for (int i=0;i<pop.size();i++)
    {
      sum+=pop.get(i).getFitness();
    }
    avgFit = sum/pop.size();
    //System.out.println("Fitness avg " + avgFit);
    //avgList.add(avg);

  }

  private void findMax(LinkedList<Indyvidual> pop)
  {
    maxFit = pop.getFirst().getFitness();
    /*
    int maxI=0;
	for (int i=1;i<pop.size();i++)
	{
	  if (pop.get(i).getFitness()>max)
	  {
	    max=pop.get(i).getFitness();
	    maxI = i;
	  }
	}
	*/
	//System.out.println("Fitness Max " + maxFit);
	//System.out.println();
	//maxList.add(max);
	//maxInd = maxI;
  }

  public int getMax()
  {
    return 0;
  }

  public void toPanel(int iter)
  {
	panel.setMin(minFit, iter);
	panel.setAv(avgFit, iter);
	panel.setMax(maxFit, iter);
  }

}
