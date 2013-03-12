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
	//System.out.println("Po preselekcji " + newPop.size());
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
    //delZero(pop);

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
	System.out.println("dziala " + pop.size());  
	
	LinkedList<Indyvidual> newPop = new LinkedList<Indyvidual>();
	int popSize = pop.size();
	int tabCount = popSize/4;  //tyle tablic 4-osobnikowych
	int newPopInd=0;
	LinkedList<Indyvidual[]> tabList = new LinkedList<Indyvidual[]>();
	int check=tabCount;
	
	for (int i=0;i<tabCount;i++)
	{
	  Indyvidual[] tmpTab = new Indyvidual[4];
	  tmpTab[0]=pop.get(newPopInd);
	  tmpTab[1]=pop.get(newPopInd+1);
	  tmpTab[2]=pop.get(newPopInd+2);
	  tmpTab[3]=pop.get(newPopInd+3);
	  newPopInd=newPopInd+4;
	  tabList.add(tmpTab);
	}
	if (tabCount*4!=popSize)
	{
	  int size=popSize-newPopInd;
      Indyvidual[] tmpTab = new Indyvidual[size];
      for (int i=0;i<size;i++)
      {
        tmpTab[i]=pop.get(newPopInd+i);
      }
      tabList.add(tmpTab);
      check++;
	}
	
	for (int i=0;i<tabCount;i++)
	{
	  Indyvidual[] tmpTab=tabList.get(i);
	  double max1=tmpTab[0].getFitness();
	  double max2=tmpTab[1].getFitness();
	  int add1=0;
	  int add2=1;
	  if (max2>max1)
	  {
	    double tmp=max1;
	    max1=max2;
	    max2=tmp;
	    tmp=add1;
	    add1=add2;
	    add2=(int) tmp;
	  }

	  for (int g=2;g<4;g++)
	  {
	    if (tmpTab[g].getFitness()>max2)
	    {
          if (tmpTab[g].getFitness()>max1)
		  {
		    max2=max1;
		    add2=add1;
		    max1=tmpTab[g].getFitness();
		    add1=g;
		  }
		  else
		  {
 		    max2=tmpTab[g].getFitness();
		    add2=g;
		  }
	    }
	  }
	  newPop.add(tmpTab[add1]);
	  newPop.add(tmpTab[add2]);
	  
	  //System.out.println("Max1 "+max1 + "  Add1 " + add1);
	  //System.out.println("Max1 "+max2 + "  Add2 " + add2);
	  //System.out.println();
	}
	
	if (tabCount!=check)
	{
	  Indyvidual[] tmpTab=tabList.getLast();
	  
	  if (tmpTab.length==1)
	  {
		newPop.add(tmpTab[0]);
	  }
	  
	  
	  if (tmpTab.length==2)
	  {
		newPop.add(tmpTab[0]);
		newPop.add(tmpTab[1]);
	  }
	  
	  
	  if (tmpTab.length>2)
	  {
		double max1=tmpTab[0].getFitness();
		double max2=tmpTab[1].getFitness();
		int add1=0;
	    int add2=1;
	    
		if (max2>max1)
		{
		  double tmp=max1;
		  max1=max2;
		  max2=tmp;
		  tmp=add1;
		  add1=add2;
		  add2=(int) tmp;
		}

		for (int g=2;g<tmpTab.length;g++)
		{
		  if (tmpTab[g].getFitness()>max2)
		  {
	        if (tmpTab[g].getFitness()>max1)
		    {
		      max2=max1;
			  add2=add1;
			  max1=tmpTab[g].getFitness();
			  add1=g;
			}
			else
			{
	 		  max2=tmpTab[g].getFitness();
			  add2=g;
			}
		  }
		}
		newPop.add(tmpTab[add1]);
		newPop.add(tmpTab[add2]);
		//System.out.println("Max1 "+max1 + "  Add1 " + add1);
		//System.out.println("Max1 "+max2 + "  Add2 " + add2);
	    //System.out.println();
	  }
	  
	}
	else
	  System.out.println("takie same");
	
	
	System.out.println("Nowa populacja " + newPop.size());
	/*
	for (int k=0;k<check;k++)
	{
	  for (int l=0;l<tabList.get(k).length;l++)
	  {
	    System.out.println(tabList.get(k)[l].getFitness());
	  }
	  System.out.println();
	}
	*/

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
