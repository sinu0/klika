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
public class VertexFactory implements Factory
{
    private int n = 0;
    public Node create()
    {
        return (new Node(n++));
    }
   public void clear()
   {
       n=0;
   }
}