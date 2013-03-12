package genetic;

import java.util.Comparator;

public class Komparator<T> implements Comparator<Indyvidual> {

	public int compare(Indyvidual o1, Indyvidual o2)
	{
	  if (o1.getFitness()>o2.getFitness())
	    return -1;
	  if (o1.getFitness()==o2.getFitness())
		return 0;
	  if (o1.getFitness()<o2.getFitness())
		return 1;
      return 0;
	}

}