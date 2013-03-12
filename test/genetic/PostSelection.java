package genetic;

import java.util.LinkedList;

public class PostSelection
{
  private int postSelectType;
  
  
  public PostSelection(int type)
  {
	postSelectType = type;
  }
  
  public LinkedList<Indyvidual> postSelection(LinkedList<Indyvidual> oldPop, LinkedList<Indyvidual> newPop)
  {
    LinkedList<Indyvidual> curPop = new LinkedList<Indyvidual>();
    
    switch(postSelectType)
    {
      case 1:
        {
    	  curPop = fullReplace(newPop);
    	  break;
        }
      case 2:
        {
  	      curPop = partialReplace(oldPop, newPop);
  	      break;
        }
      case 3:
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
    
    
    return curPop;
  }

}
