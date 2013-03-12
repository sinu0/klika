/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.border.Border;

class JDialogGenerate extends JDialog   {

    private JComboBox jComboBoxLayout;
    private JButton JButtonClose;
    private JButton JButtonGenerate;
    private JLabel jLabelClicque;
    private JLabel jLabelNode;
    private JLabel jLabelEdge;
    private JSpinner jSpinnerClicque;
    private JSpinner jSpinnerNode;
    private JSpinner jSpinnerEdge;
    private Jung jung;
    private  Frame frame;
    public JDialogGenerate(Frame owener, Jung ju, JComboBox comboBox) {
        super(owener,"Generowanie grafu");
        jComboBoxLayout = comboBox;
        jung = ju;
        frame=owener;
        
        JButtonClose = new JButton("Zamknij");
        JButtonGenerate = new JButton("Generuj");
        jLabelClicque = new JLabel("Rozmiar kliki");
        jLabelNode = new JLabel("Liczba dodatkowych wierzcho³ków", JLabel.LEFT);
        jLabelEdge = new JLabel("Liczba dodatkowych krawêdzi", JLabel.LEFT);
        SpinnerNumberModel model = new SpinnerNumberModel(3, 3, 1000, 1);
        jSpinnerClicque = new JSpinner(model);
        model = new SpinnerNumberModel(0, 0, 1000, 1);
        jSpinnerEdge = new JSpinner(model);
        model = new SpinnerNumberModel(0, 0, 1000, 1);
        jSpinnerNode = new JSpinner(model);
        setjDialog();
    }

    private void setjDialog() {

        Border border = BorderFactory.createTitledBorder("Graf");
        setSize(300, 200);
        setMinimumSize(new Dimension(250, 200));
        setLayout(new BorderLayout());
        jSpinnerClicque.setAlignmentX(RIGHT_ALIGNMENT);
        jSpinnerEdge.setAlignmentX(RIGHT_ALIGNMENT);
        jSpinnerNode.setAlignmentX(RIGHT_ALIGNMENT);
        JPanel panel = new JPanel();
        JPanel buttons = new JPanel();

        buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttons.add(Box.createHorizontalGlue());
        buttons.add(JButtonGenerate);
        buttons.add(Box.createRigidArea(new Dimension(10, 0)));
        buttons.add(JButtonClose);


        panel.setBorder(border);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        Box box1 = Box.createHorizontalBox();
        box1.setAlignmentX(LEFT_ALIGNMENT);
        box1.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));
        // box1.add(Box.createRigidArea(new Dimension(10,0)));
        box1.add(jLabelClicque);
        box1.add(Box.createRigidArea(new Dimension(5, 0)));
        box1.add(jSpinnerClicque);
        panel.add(box1);

        Box box2 = Box.createHorizontalBox();
        box2.setAlignmentX(LEFT_ALIGNMENT);
        box2.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
        // box2.add(Box.createVerticalGlue());
        box2.add(jLabelNode);
        box2.add(Box.createRigidArea(new Dimension(10, 0)));
        box2.add(jSpinnerNode);
        panel.add(box2);

        Box box3 = Box.createHorizontalBox();
        box3.setAlignmentX(LEFT_ALIGNMENT);
        box3.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 0));
        // box3.add(Box.createVerticalGlue());
        box3.add(jLabelEdge);
        box3.add(Box.createRigidArea(new Dimension(10, 0)));
        box3.add(jSpinnerEdge);
        panel.add(box3);

        jSpinnerClicque.setMaximumSize(new Dimension(70, 20));
        jSpinnerNode.setMaximumSize(new Dimension(70, 20));
        jSpinnerEdge.setMaximumSize(new Dimension(70, 20));
       
        add(panel, BorderLayout.CENTER);
        JButtonClose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JButtonGenerate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButtonGenerateAction();
            }
        });
        JButtonGenerate.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
             public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    JButtonGenerate.doClick();
                    System.out.println("KLawisz");
                }
            }
        });
        add(buttons, BorderLayout.PAGE_END);
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
             public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    JButtonGenerate.doClick();
                    
                }
            }
        });
       setFocusable(true); //po tym dzia³aja klawisze na danym oknie czyli
       //na generowaniu grafu
    }

    public Jung getJung() {
        return jung;
    }

    private void JButtonGenerateAction() {

        //if (!jSpinnerClicque) {

            jung.setG(jung.generateGraph((Integer)jSpinnerClicque.getValue(),
                    (Integer)jSpinnerNode.getValue(),(Integer)jSpinnerEdge.getValue()));
            jung.getVv().getGraphLayout().setGraph(jung.getG());

            jung.changeLayout(jComboBoxLayout.getSelectedIndex());
            jung.getVv().repaint();
            setVisible(false);
            

       // }
    }
}