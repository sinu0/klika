package genetic;

import java.util.LinkedList;
import java.util.Random;

public class PreSelection
{
  private int selectType;
  
  
  public PreSelection(int type)
  {
	selectType = type;
  }
  
  public void setSelectType(int type)
  {
	selectType = type;
  }
  
  public LinkedList<Indyvidual> selection(LinkedList<Indyvidual> pop)
  {
	LinkedList<Indyvidual> newPop = new LinkedList<Indyvidual>();
	switch (selectType)
	{
	  case 1:
	    {
		  newPop = rouletteSelection(pop);
		  break;
	    }
	  case 2:
	    {
		  newPop = tournamentSelection(pop);
		  break;
	    }
	}
	return newPop;
  }
  
  
  private LinkedList<Indyvidual> rouletteSelection(LinkedList<Indyvidual> pop)
  {
    LinkedList<Indyvidual> newPop = new LinkedList<Indyvidual>();
    int popSize = pop.size();
    
    //sumowanie przystosowan
    double sumFit = 0;
    for (int i=0;i<popSize;i++)
    {
      sumFit+=pop.get(i).getFitness();
    }
    //System.out.println("Suma przystosowan - " + sumFit);
    
    //usuniecie osobnikow o przystosowaniu 0
    delZero(pop);
    
    //losowanie kolejnych osobnikow do nowej populacji
    Random r = new Random();
    for (int i=0;i<popSize;i++)
    {
      double rand = r.nextDouble();  //losowanie liczby do pomnozenia przez sume przystosowan
      double choice = rand * sumFit;  //obliczanie miejsca, w ktorym bedzie osobnik do nowej populacji
      double check = 0;   //suma dostosowan osobnikow, ktore przeszly przez petle while
      int ind=0;
      while (check<choice && ind<pop.size()-1)
      {
        check+=pop.get(ind).getFitness();
        ind++;
      }
      newPop.add(pop.get(ind));
    }
	return newPop;
  }
  
  
  private LinkedList<Indyvidual> tournamentSelection(LinkedList<Indyvidual> pop)
  {
	LinkedList<Indyvidual> newPop = new LinkedList<Indyvidual>();
	
    return newPop;
  }
  
  
  
  private void delZero(LinkedList<Indyvidual> pop)
  {
    int j=0;
	while (j<pop.size())
	{
	  if (pop.get(j).getFitness()==0)
	    pop.remove(j);
	  else
	  	j++;
	}
  }
  
  
  

}
