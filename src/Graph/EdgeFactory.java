/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import org.apache.commons.collections15.Factory;

/**
 *
 * @author -
 */
public class EdgeFactory implements Factory
{
    private int e = 0;
    public Edge create()
    {
        return (new Edge(e++));
    }
     public void clear()
   {
       e=0;
   }
}

