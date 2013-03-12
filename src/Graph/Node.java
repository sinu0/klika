/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

/**
 *
 * @author -
 */
public class Node  {
    private int id; //numer wierzcho³ka
    boolean selected=false;

    public Node(int _id)
    {
        id=_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString()
    {
        return  Integer.toString(id);
    }
}
