/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

/**
 *
 * @author -
 */
public class Edge {
    private int id; //numer krawedzi

    Edge(int _id)
    {
        id=_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "E" + id;
    }
}
