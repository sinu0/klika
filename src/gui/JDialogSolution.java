/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package gui;

import Graph.Edge;
import Graph.Node;
import GraphIO.Io;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import genetic.Indyvidual;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JMenuBar;

/**
 *
 * @author - Klasa ktora wyœwietla rozwiazanie algorytmu Genetic
 */
public class JDialogSolution extends JDialog {

    private Jung newjung;
    private JPanelControl controls;
    private Graph<Node, Edge> graph;
    private int HashTab[];
    private Node nodeTab[];
    private Graph<Node, Edge> subGraph;
    private JComboBox jComboLayout;
    private JComboBox modeMenu;
    private JPanel jPanelJungControls;
    private Frame frame;
    private JLabel jLabelNodeCount;
    private Task updateDialog;

    public JDialogSolution(Frame _frame, JPanelControl _controls) {
        super(_frame, "Rozwi¹zanie");
        newjung = new Jung();
        newjung.init();
        controls = _controls;
        frame = _frame;
        newjung.setG(new UndirectedSparseGraph<Node, Edge>());
        add(newjung.getVv(), BorderLayout.CENTER);
        updateDialog = new Task();

        //zmiana layoutu grafu
        String[] comboList = {"FRLayout2", "KKLayout", "CircleLayout",
            "ISOMLayout", "FRLayout2", "SpringLayout2",
            "SpringLayout", "StaticLayout", "SpringLayout2"};
        jComboLayout = new JComboBox(comboList);
        jComboLayout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                newjung.changeLayout(cb.getSelectedIndex());
            }
        });

        //zmiana rodzaju edycji grafu
        modeMenu = newjung.getGm().getModeComboBox();
        jPanelJungControls = new JPanel();
        jPanelJungControls.setLayout(new BoxLayout(jPanelJungControls, BoxLayout.LINE_AXIS));
        jComboLayout.setBorder(BorderFactory.createTitledBorder("Uk³ad grafu:"));
        modeMenu.setBorder(BorderFactory.createTitledBorder("tryb myszki:"));

        Box box = Box.createHorizontalBox();
        box.add(jComboLayout);
        box.add(Box.createRigidArea(new Dimension(10, 10)));
        box.add(modeMenu);
        box.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        jLabelNodeCount = new JLabel();
        jLabelNodeCount.setFont(jLabelNodeCount.getFont().deriveFont(20));
        jLabelNodeCount.setBorder(BorderFactory.createEmptyBorder(4, 10, 2, 60));

        Box box1 = Box.createHorizontalBox();
        box1.add(jLabelNodeCount);
        box1.setBorder(BorderFactory.createTitledBorder("Iloœæ wierzcho³ków:"));

        jPanelJungControls.add(box1);
        jPanelJungControls.add(Box.createRigidArea(new Dimension(5, 0)));
        jPanelJungControls.add(box);


        jPanelJungControls.setBorder(BorderFactory.createRaisedBevelBorder());
        add(jPanelJungControls, BorderLayout.SOUTH);
        setMinimumSize(new Dimension(400, 400));
        Toolkit tool=Toolkit.getDefaultToolkit();
        Dimension dim=tool.getScreenSize();
        dim.width=dim.width-400;
        dim.height=0;
        setLocation(dim.width, dim.height);
        //dodanie MenuBara z funkcja zapisu rozwiazania do pliku
        JMenuBar menu = new JMenuBar();
        JMenuItem ItemSave = new JMenuItem("Zapisz rozwi¹zanie");
        menu.add(ItemSave);

        ItemSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (subGraph == null || subGraph.getVertexCount() < 1) {
                    JOptionPane.showMessageDialog(frame, "Brak grafu do zapisania");
                } else {
                    saveSolution();
                }
            }
        });
        setJMenuBar(menu);
        newjung.getGm().setMode(Mode.TRANSFORMING);
               

    }

    public void reInitDialog() {
        //pobranie gotowych tablic z klasy genetic w celu poleszenia wydajnosci
        //generowania grafu
        frame.getjProgressBar().setString("Preparing solution..");
        nodeTab = controls.getGen().getFit().getNodeTab();
        HashTab = controls.getGen().getFit().getHashTab();

        graph = frame.jung.getG();
        //jezeli algorytm nie wygeneriwa³ zadnej populacji to wyswietl puste okno
        //w przeciwnym wypadku wygeneruj rozwiazanie
        if (controls.getGen().getNewPop() != null) {
            subGraph = buildGraph(graph);
            jLabelNodeCount.setText(Integer.toString(subGraph.getVertexCount()));
            newjung.setG(subGraph);
            newjung.changeLayout(1);
            jComboLayout.setSelectedIndex(1);
        } else {

            newjung.setG(new UndirectedSparseGraph<Node, Edge>());
            newjung.changeLayout(1);
            jComboLayout.setSelectedIndex(1);
            jLabelNodeCount.setText("0");
        }
        frame.getjProgressBar().setString("Done");

    }

    private Graph buildGraph(Graph<Node, Edge> graph) {

        //najlepsze rozwiazanie z ostatniej populacji
        int best = controls.getGen().getStat().getMax();
        //pobranie najlepszego osobnika
        Indyvidual ind = controls.getGen().getNewPop().get(best);
        boolean[] chrom = ind.getChromosome();
        subGraph = new UndirectedSparseGraph<Node, Edge>();
        //dodanie wierzcho³kow ktore jest rozwiazaniem
        for (int i = 0; i < chrom.length; i++) {
            if (chrom[i] == true) {
                subGraph.addVertex(new Node(i));

            }
        }
        Node[] nodeTab2 = new Node[subGraph.getVertexCount()];
        subGraph.getVertices().toArray(nodeTab2);  //tablica nowych wierzcholkow

        for (int i = 0; i < nodeTab2.length; i++) {

            for (int j = i + 1; j < nodeTab2.length; j++) {
                //szukanie krawedzi pomiedzy wierzcho³kami
                Edge e = (Edge) graph.findEdge(
                        nodeTab[HashTab[nodeTab2[i].getId()]],
                        nodeTab[HashTab[nodeTab2[j].getId()]]);
                if (e != null) {
                    //System.out.println("Dodawanie krawedzi");
                    subGraph.addEdge(e, nodeTab2[i], nodeTab2[j], EdgeType.UNDIRECTED);
                }
            }
            // System.out.println();
        }
        return subGraph;
    }

    public Frame getFrame() {
        return frame;
    }

    public JPanelControl getControls() {
        return controls;
    }
    //zadanie ktore jest wykonywane w celu wyswietlenia bierzacego najlepszego
    //osbnika
    class Task extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() {

            nodeTab = controls.getGen().getFit().getNodeTab();
            HashTab = controls.getGen().getFit().getHashTab();
            graph = frame.jung.getG();
            subGraph = buildGraph(graph);
            jLabelNodeCount.setText(Integer.toString(subGraph.getVertexCount()));
            newjung.setG(subGraph);
            newjung.changeLayout(jComboLayout.getSelectedIndex());

            return null;
        }
    }

    public void goUpdate() {
        if (updateDialog.isDone()) {
            updateDialog = new Task();
            updateDialog.execute();
        }
    }

    public void stopUpdate() {
        boolean cancel = updateDialog.cancel(true);


    }

    protected void saveSolution() {

        JFileChooser file = new JFileChooser();
        int returnVal = file.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File f = file.getSelectedFile();
            try {


                String string = f.getPath();
                if(!string.contains(".txt"))
                { 
                    string = string + ".txt";
                }
                Io.saveGraph(subGraph, string);
            } catch (IOException ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
