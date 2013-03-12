/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * TODO przerobienie ca³ego panelu na ³adniejszy
 */
package gui;

import genetic.Genetic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

/**
 *
 * @author -
 */
public class JPanelControl extends JPanel {

    private JButton JButtonSolve;
    private JButton JButtonStop;
    private JButton JButtonPause;
    private JDialog JDialogFitness;
    private JPanelChart chart;
    private JPanel tmp;
    private int i = 0;
    private Frame frame;
    private Genetic gen;
    private JSpinner jSpinnerPopSize;
    private JSpinner jSpinnerMutProb;
    private JSpinner jSpinnerCrosProb;
    private JSpinner jSpinnerCrosType;
    private JSpinner jSpinnerPostSlect;
    private JSpinner jSpinnerPreSelect;
    private JSpinner jSpinnerIteration;
    private JSpinner jSpinnerContinueType;
    private JSpinner jSpinnerRefresh;
    private JCheckBox jCheckBoxShowSolution;
    private JCheckBox jCheckBoxShowChart;
    private JSpinner test;
    private Timer t;
    private JDialogSolution jDSolution;
    private JComboBox jComboPreSelect;
    private JComboBox jComboPostSelect;
    private JComboBox jComboCrossType;
    private JComboBox jComboContinueType;

    public JPanelControl(Frame _frame) {
        frame = _frame;
        JDialogFitness = new JDialog(_frame);
        jDSolution = new JDialogSolution(frame, this);
        JButtonSolve = new JButton("Start");
        JButtonPause = new JButton("Pause");
        JButtonStop = new JButton("Stop");
        chart = new JPanelChart("Fitness function", "Populacja", "Wartoœæ");
        chart.setBorder(BorderFactory.createTitledBorder("Wykres"));

        jCheckBoxShowChart = new JCheckBox("Schowaj wykres");
        jCheckBoxShowSolution = new JCheckBox("Schowaj rozwi¹zanie");

        //  setBorder(BorderFactory.createRaisedBevelBorder());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        SpinnerModel model = new SpinnerNumberModel(new Integer(400), new Integer(10), new Integer(2000), new Integer(1));
        jSpinnerPopSize = new JSpinner(model);
        
        model = new SpinnerNumberModel(70, 0, 100, 1);
        jSpinnerCrosProb = new JSpinner(model);
        
        String[] tabCross = {"Jednopunktowe z 2 potomkami", "Jednopunktowe z 1 potomkiem", "Wielopunktowe z 2 potomkami", "Wielopunktowe z 1 potomkiem"};
        jComboCrossType = new JComboBox(tabCross);
        
        String[] tabPost = {"Zastêpywanie ca³kowite", "Selekcja elitarna"};
        jComboPostSelect = new JComboBox(tabPost);
        
        String[] tabPre = {"Selekcja ruletkowa", "Selekcja turniejowa"};
        jComboPreSelect = new JComboBox(tabPre);
        
        model = new SpinnerNumberModel(1, 0, 100, 1);
        jSpinnerMutProb = new JSpinner(model);
        
        model = new SpinnerNumberModel(1000, 1, 1000000, 1);
        jSpinnerIteration = new JSpinner(model);
        
        model = new SpinnerNumberModel(3, 1, 3, 1);
        jSpinnerContinueType = new JSpinner(model);
        //jComboContinueType
        String[] tabCont = {"Limit iteracji", "Niezmiennoœæ rozwi¹zania", "Podwójne zakoñczenie"};
        jComboContinueType = new JComboBox(tabCont);
        
        model = new SpinnerNumberModel(10, 1, 50, 1);
        jSpinnerRefresh = new JSpinner(model);
        
        
        
        Dimension dimensionSmall = new Dimension(80, 20);
        Dimension dimensionBig = new Dimension(140,20);
        jSpinnerIteration.setMaximumSize(dimensionSmall);
        jSpinnerCrosProb.setMaximumSize(new Dimension(70, 20));
        jComboCrossType.setMaximumSize(new Dimension(190, 20));
        jSpinnerMutProb.setMaximumSize(dimensionSmall);
        jSpinnerPopSize.setMaximumSize(dimensionSmall);
        jComboPostSelect.setMaximumSize(dimensionBig);
        jComboPreSelect.setMaximumSize(dimensionBig);
        jComboContinueType.setMaximumSize(new Dimension(160, 20));
        jSpinnerRefresh.setMaximumSize(dimensionSmall);

        Box box0 = Box.createVerticalBox();
        box0.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 5));
        box0.add(jSpinnerIteration);

        Box box1 = Box.createHorizontalBox();
        box1.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 5));
        box1.add(JButtonSolve);
        box1.add(Box.createRigidArea(new Dimension(10, 10)));
        box1.add(JButtonStop);
        box1.add(Box.createRigidArea(new Dimension(10, 10)));
        box1.add(JButtonPause);

        Box box2 = Box.createHorizontalBox();
        box2.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 5));
        box2.add(jSpinnerPopSize);
        box2.add(Box.createRigidArea(new Dimension(10, 10)));
        box2.add(jSpinnerMutProb);

        Box box3 = Box.createHorizontalBox();
        box3.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 5));
        box3.add(jComboPreSelect);
        box3.add(Box.createRigidArea(new Dimension(10, 10)));
        box3.add(jComboPostSelect);

        Box box4 = Box.createHorizontalBox();
        box4.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 5));
        box4.add(jComboCrossType);
        box4.add(Box.createRigidArea(new Dimension(10, 10)));
        box4.add(jSpinnerCrosProb);

        Box box5 = Box.createHorizontalBox();
        box5.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 5));
        box5.add(jCheckBoxShowChart);
        box5.add(Box.createRigidArea(new Dimension(10, 10)));
        box5.add(jCheckBoxShowSolution);
        
        Box box6 = Box.createHorizontalBox();
        box6.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 5));
        box6.add(jComboContinueType);
        box6.add(Box.createRigidArea(new Dimension(10, 10)));
        box6.add(jSpinnerRefresh);


        JLabel lab0 = new JLabel("Iloœæ iteracji");
        lab0.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(lab0);
        box0.setAlignmentX(CENTER_ALIGNMENT);
        add(box0);

       

        JLabel lab2 = new JLabel("Wielkoœæ populacji i prawdopodobieñstwo mutacji");
        lab2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(lab2, JLabel.CENTER_ALIGNMENT);
        box2.setAlignmentX(CENTER_ALIGNMENT);
        add(box2, Box.CENTER_ALIGNMENT);

        JLabel lab3 = new JLabel("Preselekcja i postselekcja");
        lab3.setAlignmentX(CENTER_ALIGNMENT);
        add(lab3);
        box3.setAlignmentX(CENTER_ALIGNMENT);
        add(box3, Box.CENTER_ALIGNMENT);

        JLabel lab4 = new JLabel("Typ i prawdopodobieñstwo krzyzowania");
        lab4.setAlignmentX(CENTER_ALIGNMENT);
        add(lab4);
        box4.setAlignmentX(CENTER_ALIGNMENT);
        add(box4, Box.CENTER_ALIGNMENT);

        JLabel lab6 = new JLabel("Typ zakoñczenia i czêstoœæ odœwie¿ania");
        lab6.setAlignmentX(CENTER_ALIGNMENT);
        add(lab6);
        box6.setAlignmentX(CENTER_ALIGNMENT);
        add(box6, Box.CENTER_ALIGNMENT);
        
        JLabel lab5 = new JLabel("Ustawienia dodoatkowe");
        lab5.setAlignmentX(CENTER_ALIGNMENT);
        add(lab5);
        box5.setAlignmentX(CENTER_ALIGNMENT);
        add(box5, Box.CENTER_ALIGNMENT);
        
         JLabel lab1 = new JLabel("Sterowanie");
        lab1.setAlignmentX(CENTER_ALIGNMENT);
        add(lab1);
        box1.setAlignmentX(CENTER_ALIGNMENT);
        add(box1);

        jCheckBoxShowChart.setSelected(true);
        jCheckBoxShowSolution.setSelected(true);

        jCheckBoxShowChart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (jCheckBoxShowChart.isSelected()) {
                    jCheckBoxShowChart.setText("Schowaj wykres");
                    if (frame.jung.getG().getVertexCount() != 0 && gen != null) {
                        JDialogFitness.setVisible(true);
                    }
                } else {
                    jCheckBoxShowChart.setText("Poka¿ wykres");
                    JDialogFitness.setVisible(false);
                }
            }
        });

        jCheckBoxShowSolution.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (jCheckBoxShowSolution.isSelected()) {
                    jCheckBoxShowSolution.setText("Schowaj rozwi¹zanie");
                    if (frame.jung.getG().getVertexCount() != 0 && gen != null) {
                        jDSolution.setVisible(true);
                        jDSolution.reInitDialog();
                    }
                } else {
                    jCheckBoxShowSolution.setText("Poka¿ rozwi¹zanie");
                    jDSolution.setVisible(false);
                }
            }
        });

        JDialogFitness.setLayout(new BorderLayout());
        JDialogFitness.setMinimumSize(new Dimension(500, 400));
        JDialogFitness.add(chart, BorderLayout.CENTER);


        JButtonStop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (Genetic.isIsWorking()) {
                    gen.myStop();
                }
            }
        });
        
        JButtonPause.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (Genetic.isIsWorking()) {
                    if(gen.isPause())
                    {
                        gen.setPause(false);
                        JButtonPause.setBackground(null);
                    }
                    else
                    {
                        gen.setPause(true);
                        JButtonPause.setBackground(Color.red);
                        
                    }
                }
            }
        });

        JButtonSolve.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButtonSlove(e);
            }
        });
    }

    private void JButtonSlove(ActionEvent e) {
        if (!Genetic.isIsWorking() && frame.getJung().getG().getVertexCount() != 0) {
            if (jCheckBoxShowChart.isSelected()) {
                JDialogFitness.setVisible(true);
            }
            if (jCheckBoxShowSolution.isSelected()) {
                jDSolution.setVisible(true);
            }
            chart.setNewSeries();
            int mutProb = (Integer) jSpinnerMutProb.getValue();
            int crosProb = (Integer) jSpinnerCrosProb.getValue();
            gen = new Genetic(frame.getJung().getG(), (Integer) jSpinnerPopSize.getValue(),
                    ((double) mutProb) / 100, ((double) crosProb) / 100,
                    (Integer) jComboPreSelect.getSelectedIndex()+1, (Integer) jComboCrossType.getSelectedIndex()+1,
                    (Integer) jComboPostSelect.getSelectedIndex()+1,
                    (Integer) jSpinnerIteration.getValue(), chart, frame.getJung(), jDSolution, 
                    (Integer) jComboContinueType.getSelectedIndex()+1, (Integer) jSpinnerRefresh.getValue());
            // gen.start();
            gen.execute();

        } else {
            if (frame.getJung().getG().getVertexCount() == 0) {
                JOptionPane.showMessageDialog(frame, "Najpierw wygeneruj lub za³aduj graf");
            } else {
               
            }
        }
    }

    public JCheckBox getjCheckBoxShowChart() {
        return jCheckBoxShowChart;
    }

    public JCheckBox getjCheckBoxShowSolution() {
        return jCheckBoxShowSolution;
    }

    public JPanelChart getChart() {
        return chart;
    }

    public Genetic getGen() {
        return gen;
    }
}
