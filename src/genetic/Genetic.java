package genetic;

import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.LinkedList;

import Graph.Edge;
import Graph.Node;

import edu.uci.ics.jung.graph.Graph;
import gui.JDialogSolution;
import gui.JPanelChart;
import gui.Jung;
import java.beans.PropertyChangeListener;
import java.lang.Void;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class Genetic extends SwingWorker<Boolean, String> implements PropertyChangeListener {

    private LinkedList<Indyvidual> oldPop;
    private LinkedList<Indyvidual> tmpPop;
    private LinkedList<Indyvidual> newPop;
    private Graph<Node, Edge> graph;
    int currGener;
    //pola klas
    private PreSelection preSelect;
    private Crossing cross;
    private Mutation mut;
    private Fitness fit;
    private Statistics stat;
    private PostSelection postSelect;
    //pola paramatrow dla klas
    int popSize;
    int nodes;  //ilosc wierzcholkow=wielkosc tablicy
    double mutProb;
    double crossProb;
    int preSelectType;
    int crossType;
    int postSelectType;
    int continueType;
    int iteration;
    int i;
    private Komparator<Indyvidual> komp;
    private Jung jung;
    private static boolean isWorking = false;
    private JDialogSolution solution;
    private Node[] nodeTab; //tablica wierzcho³kow grafu
    private int HashTab[]; //tablica odwzoruwujaca nr wierzcho³ka numer w tablicy nodeTab[]
    private String action;
    private int initPopvar;
    private boolean pause=false;
    double bestValue;    //najlepsza wartosc
    int iterChanged;  //iteracja w ktorej zmienila sie najlepsza wartosc
    
    int refresh;
    
    
    
    
    public Genetic(Graph<Node, Edge> _graph, int _popSize, double _mutProb, double _crosProb, int _preSelect,
            int _crossTyp, int _postSelect, int _iteration, JPanelChart _panel, Jung _jung,
            JDialogSolution _solution, int _contType, int _refresh) {

        solution = _solution;
        graph = _graph;
        nodes = graph.getVertexCount();
        currGener = 0;
        oldPop = new LinkedList<Indyvidual>();

        iteration = _iteration;
        popSize = _popSize;
        mutProb = _mutProb;
        crossProb = _crosProb;
        preSelectType = _preSelect;
        crossType = _crossTyp;
        postSelectType = _postSelect;
        jung = _jung;

        preSelect = new PreSelection(preSelectType);
        cross = new Crossing(crossType, crossProb, popSize);
        mut = new Mutation(mutProb);
        fit = new Fitness(graph);
        stat = new Statistics(_panel);
        postSelect = new PostSelection(postSelectType);
        


        komp = new Komparator<Indyvidual>();
        nodeTab = new Node[graph.getVertexCount()];
        nodeTab = graph.getVertices().toArray(nodeTab);
        HashTab = new int[graph.getVertexCount()];
        //zmienna do status bara
        action = "";
        addPropertyChangeListener(this);
        
        continueType = _contType;
        bestValue=0;
        iterChanged=0;
        refresh = _refresh;

    }

    private void initPop() {
        boolean done = true;
        solution.getFrame().getjProgressBar().setIndeterminate(true);
        action = "Initializing the population...";
        initPopvar = 0;
      
            oldPop.clear();
            for (int i = 0; i < popSize; i++) {
                Indyvidual osobnik = new Indyvidual(nodes);
                osobnik.init();
                oldPop.add(osobnik);
            }

            initPopvar++;
            action = "Initializing the population " + initPopvar;
            solution.getFrame().getjProgressBar().setString(action);
            fit.fitness(oldPop);
            Collections.sort(oldPop, komp);
          


        
solution.getFrame().getjProgressBar().setIndeterminate(false);

    }

    @Override
    protected Boolean doInBackground() throws Exception {
        isWorking = true;
        setProgress(0);
        i = 0;
        initPop();
        int shown = 0;
        HashTab = fit.getHashTab();
        action = "Runing...";
        //gdzies jest b³ad bo bez tego powinno dzia³aæ   
        solution.reInitDialog();//bez
        solution.goUpdate();//bez
        solution.stopUpdate();//bez
        boolean stop = true;
        if (continueType==2 || continueType==3)
          solution.getFrame().getjProgressBar().setIndeterminate(true);
        while (stop)
            //for (;i<iteration;i++)
        {
        	if (continueType==1)
            setProgress(  100*i/iteration);
            //publish(dupa);
            tmpPop = preSelect.selection(oldPop);
            Collections.sort(tmpPop, komp);
            newPop = cross.crossing(tmpPop);
            mut.mutate(newPop);
            fit.fitness(newPop);
            Collections.sort(newPop, komp);
            stat.findStat(newPop);
            stat.toPanel(i);
            while (pause) {
                try {
                    Thread.sleep(500); // half a second
                } catch (InterruptedException e) {
                }
            }
            if (shown * refresh == i) {
                // deselect();
                if (solution.getFrame().getjPanelControl().getjCheckBoxShowSolution().isSelected()) {
                    solution.goUpdate();
                }
                printVertex taskPrint = new printVertex(newPop);
                taskPrint.execute();

                shown++;
            }
            i++;

            oldPop = postSelect.postSelection(oldPop, newPop);  //postselekcja
            //oldPop = newPop;
            stop = continueAlgo();
            //System.out.println("Stop " + stop + "  " + iterChanged);
        }
        
        solution.stopUpdate();
        printVertex taskPrint = new printVertex(newPop);
        taskPrint.execute();
        isWorking = false;
        solution.getFrame().getjProgressBar().setIndeterminate(false);
        setProgress(  100);
        
        JOptionPane.showMessageDialog(null, "Done");
            if (solution.getControls().getjCheckBoxShowSolution().isSelected()) {
                solution.setVisible(true);
                solution.reInitDialog();

            }
            action = "Done";

            return true;


    }

    public void myStop() {
        i = iteration + 10;
        JOptionPane.showMessageDialog(null, "Stoped");
        if (solution.getControls().getjCheckBoxShowSolution().isSelected()) {
            solution.setVisible(true);
            solution.reInitDialog();

        }
        action = "Done";


    }

    public void printPop() {

        for (int i = 0; i < popSize; i++) {
            //newPop.get(i).printTab();
            //.println(newPop.get(i).getFitness());
            // System.out.println();
        }

        //System.out.println(graph.toString());
        //System.out.println(graph.getVertexCount());
    }
    
    
    private boolean continueAlgo()
    {
      boolean contValue = true;
      switch (continueType)
      {
        case 1:
          {
        	//System.out.println("Case 1");
        	contValue = iterCont();
            break;
          }
        case 2:
          {
        	//System.out.println("Case 2");
        	return changeCont();
            //break;
          }
        case 3:
          {
        	//System.out.println("Case 3");
        	contValue = (iterCont() && changeCont());
        	break;
          }
      }
      return contValue;
    }
    
    private boolean iterCont()
    {
      if (i<iteration)
    	return true;
      else
    	return false;
    }
    
    private boolean changeCont()
    {
      //System.out.println(iterChanged);
      if (oldPop.getFirst().getFitness()==bestValue)
      {
        iterChanged++;
      }
      else
      {
        iterChanged=0;
        bestValue=oldPop.getFirst().getFitness();
      }
      if (iterChanged>=100 || action.equals("Done"))
        return false;
      return true;
    }
    
    
    
//funkcja ktora ktora zaznacza najlepsze rozwiazanie na gówynym grafie
    class printVertex extends SwingWorker<Void, Void> {

        LinkedList<Indyvidual> pop;

        public printVertex(LinkedList<Indyvidual> _pop) {
            pop = _pop;
        }

        @Override
        protected Void doInBackground() {
            if (pop != null) {
                int best = stat.getMax();
                Indyvidual ind = pop.get(best);
                boolean[] chrom = ind.getChromosome();

                for (int i = 0; i < chrom.length; i++) {
                    //odznaczenie wszystkich wierzcho³ków
                    nodeTab[HashTab[i]].setSelected(false);
                    //zaznczenie najlepszego osbnika
                    if (chrom[i] == true) {
                        nodeTab[HashTab[i]].setSelected(true);
                    }
                }
                jung.getVv().repaint();
                return null;
            }
            return null;
        }
    }

    private void deselect() {
        for (int i = 0; i < nodeTab.length; i++) {
            nodeTab[i].setSelected(false);

        }
        jung.getVv().repaint();
    }

    static public boolean isIsWorking() {
        return isWorking;
    }

    static public void setIsWorking(boolean _isWorking) {
        isWorking = _isWorking;
    }

    public LinkedList<Indyvidual> getNewPop() {
        return newPop;
    }

    public Statistics getStat() {
        return stat;
    }

    public Fitness getFit() {
        return fit;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        //solution.getFrame().getjProgressBar().setStringPainted(true);
        //solution.getFrame().getjProgressBar().setString(action);
        if ("progress".equals(evt.getPropertyName()))
            solution.getFrame().getjProgressBar().setValue((Integer)evt.getNewValue());

    }
}
