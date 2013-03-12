package genetic;

import java.util.LinkedList;

public class PostSelection
{
  private int postSelectType;
  private int popSize;
  
  
  public PostSelection(int type)
  {
	postSelectType = type;
  }
  
  public LinkedList<Indyvidual> postSelection(LinkedList<Indyvidual> oldPop, LinkedList<Indyvidual> newPop)
  {
    LinkedList<Indyvidual> curPop = new LinkedList<Indyvidual>();
    popSize = newPop.size();
    
    switch(postSelectType)
    {
      case 1:
        {
    	  curPop = fullReplace(newPop);
    	  break;
        }
      case 2:
        {
  	      curPop = eliteReplace(oldPop, newPop);
  	      break;
        }
    }
    return curPop;
  }
  
  private LinkedList<Indyvidual> fullReplace(LinkedList<Indyvidual> pop)
  {
	LinkedList<Indyvidual> curPop = new LinkedList<Indyvidual>();
	curPop = pop;
	return curPop;
  }
  
  
  private LinkedList<Indyvidual> partialReplace(LinkedList<Indyvidual> oldPop, LinkedList<Indyvidual> newPop)
  {
    LinkedList<Indyvidual> curPop = new LinkedList<Indyvidual>();
    
    
    return curPop;
  }
  
  
  private LinkedList<Indyvidual> eliteReplace(LinkedList<Indyvidual> oldPop, LinkedList<Indyvidual> newPop)
  {
    LinkedList<Indyvidual> curPop = new LinkedList<Indyvidual>();
    int oldInd=0;
    int newInd=0;
    int i=0;
    //System.out.println("OldPop "+oldPop.size());
    //System.out.println("NewPop "+newPop.size());
    while(i<popSize)
    {
      if (oldPop.get(oldInd).getFitness()>newPop.get(newInd).getFitness() && oldInd<oldPop.size())
      {
        curPop.add(oldPop.get(oldInd));
        oldInd++;
      }
      else
      {
        curPop.add(newPop.get(newInd));
        newInd++;
      }
      i++;
      //System.out.println("Petla " + i + "  OldPop");
    }
    //System.out.println("CurPop " + curPop.size());
    return curPop;
  }

}
