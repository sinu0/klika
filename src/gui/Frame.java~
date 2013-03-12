package gui;

import Graph.Node;
import GraphIO.Io;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.event.InputEvent;
import java.io.FilePermission;
import java.security.AccessController;
import org.apache.commons.collections15.Transformer;

public class Frame extends JFrame implements WindowListener {

    private JMenuBar JMenuBar1;
    private JMenu JMenuView;
    private JMenu JMenuFile;
    private JMenu JMenuHelp;
    private JMenuItem ItemOpen;
    private JMenuItem ItemSave;
    private JMenuItem ItemExit;
    private JMenuItem ItemGenerate;
    private JMenuItem ItemClearGraph;
    private JMenuItem ItemAbout;
    private JPanelControl jPanelControl;
    private JComboBox jComboLayout;
    private JComboBox modeMenu;
    private JPanel jPanelJungControls;
    private JDialogGenerate jDialogGenerate;
    private JProgressBar jProgressBar;
    private JCheckBoxMenuItem jItemVertexCount;
    private JCheckBoxMenuItem jItemAutoFit;
    Jung jung;
    JFileChooser file;

    public Frame() {
        super("klika");
        // sprawdza czy system na ktorym dzia³a program nie jest przypadkiem 
        // linuxem jezeli nie to zmienia wyglad okien ala windows
        if (!System.getProperty("os.name").toString().equals("Linux")) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        jung = new Jung();
        jung.init();
        file = new JFileChooser();
        file.setFileFilter(new FileFilterTxt());
        init();
        //TODO ustawic background na vv
        // BufferedImage image;
        // image=ImageIO.read(new File("DNA.jpg"));

        jung.getVv().setBackground(Color.getHSBColor(0.5f, 0.05f, 0.95f));
        setIconImage(new ImageIcon("icon.png").getImage());

        jDialogGenerate = new JDialogGenerate(this, jung, jComboLayout);
    }

    private void init() {
        //elemety gui
        JMenuBar1 = new JMenuBar();
        JMenuView = new JMenu("Widok");
        JMenuFile = new JMenu("Graf");
        JMenuHelp = new JMenu("Pomoc");
        ItemOpen = new JMenuItem("Otwórz...", new ImageIcon("open.png"));
        ItemSave = new JMenuItem("Zapisz...", new ImageIcon("Save.png"));
        ItemExit = new JMenuItem("Wyjscie");
        ItemAbout = new JMenuItem("O programie...");
        jItemVertexCount = new JCheckBoxMenuItem("Liczba wierzcho³ków");
        jItemAutoFit = new JCheckBoxMenuItem("Automatyczne dopasowanie wierzcho³ków");
        ItemClearGraph = new JMenuItem("Wyczyœæ");
        ItemGenerate = new JMenuItem("Generuj nowy graf...");
        jPanelControl = new JPanelControl(this);
        jProgressBar = new JProgressBar();
        addWindowListener(this);
        setLayout(new BorderLayout());
        JMenuFile.add(ItemOpen);

        ItemOpen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                ItemOpenAction();

            }
        });

        JMenuFile.add(ItemSave);
        ItemSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ItemSaveAction();
            }
        });
        JMenuFile.addSeparator();
        JMenuFile.add(ItemClearGraph);

        ItemClearGraph.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                jung.setG(jung.generateGraph(0, 0, 0));
                jung.getVv().getGraphLayout().setGraph(jung.getG());
                jDialogGenerate.getJung().setG(jung.generateGraph(0, 0, 0));
                jPanelControl.getChart().setNewSeries();
                jDialogGenerate.getJung().getVv().repaint();
                jung.getVv().repaint();

            }
        });
        JMenuFile.add(ItemGenerate);
        ItemGenerate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!genetic.Genetic.isIsWorking()) {
                    jDialogGenerate.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Aby wygenerowaæ nowy graf najpierw zatrzymaj algorytm");
                }
            }
        });
        JMenuFile.addSeparator();
        JMenuFile.add(ItemExit);
        ItemExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ItemExitAction();

            }
        });
        //zmiana edycji 
        JMenu jungItem = jung.getGm().getModeMenu();
        jungItem.setIcon(null);
        jungItem.setPreferredSize(new Dimension(120, 20));
        jungItem.setText("Tryb kursora");

        jItemVertexCount.setSelected(true);
        jItemAutoFit.setSelected(true);

        JMenuView.add(jungItem);
        JMenuView.addSeparator();
        JMenuView.add(jItemVertexCount);
        JMenuView.add(jItemAutoFit);
        //ustawanie podpisu pod wierzcho³ek
        jItemVertexCount.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (jItemVertexCount.isSelected()) {
                    jung.getVv().getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
                } else {
                    jung.getVv().getRenderContext().setVertexLabelTransformer(new Transformer<Node, String>() {

                        @Override
                        public String transform(Node i) {
                            return "";
                        }
                    });
                }
                jung.getVv().repaint();
            }
        });
        ItemAbout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog okno = new JDialog();
                okno.setVisible(true);
                Box box = Box.createVerticalBox();

                JLabel label = new JLabel("Jest to program znajduj¹cy maksymaln¹ klike w grafie ");
                JLabel label4=new JLabel("nie skierowanym za pomoc¹ algorytmu genetycznego.");
                JLabel label1 = new JLabel("Program wykonali:");
                JLabel label2 = new JLabel("Mariusz Pacek");
                JLabel label3 = new JLabel("Zbigniew Pieprzak");
                JLabel label5 = new JLabel("");
                JLabel label6 = new JLabel("");
                label.setAlignmentX(CENTER_ALIGNMENT);
                label1.setAlignmentX(CENTER_ALIGNMENT);
                label2.setAlignmentX(CENTER_ALIGNMENT);
                label3.setAlignmentX(CENTER_ALIGNMENT);
                label4.setAlignmentX(CENTER_ALIGNMENT);
                label5.setAlignmentX(CENTER_ALIGNMENT);
                label6.setAlignmentX(CENTER_ALIGNMENT);
                box.add(label5);
                box.add(Box.createRigidArea(new Dimension(10, 10)));
                box.add(label);
                box.add(Box.createRigidArea(new Dimension(10, 10)));
                box.add(label4);
                box.add(Box.createRigidArea(new Dimension(10, 10)));
                box.add(new JToolBar.Separator());
                box.add(label5);
                box.add(Box.createRigidArea(new Dimension(10, 10)));
                box.add(label1);
                box.add(Box.createRigidArea(new Dimension(10, 10)));
                box.add(label6);
                box.add(Box.createRigidArea(new Dimension(10, 10)));
                box.add(label2);
                box.add(Box.createRigidArea(new Dimension(10, 10)));
                
                box.add(label3);
                box.setBorder(BorderFactory.createTitledBorder("O programie:"));
                okno.setMinimumSize(new Dimension(450, 300));
                okno.add(box);

            }
        });
        JMenuHelp.add(ItemAbout);

        JMenuBar1.add(JMenuFile);
        JMenuBar1.add(JMenuView);
        JMenuBar1.add(JMenuHelp);

        jPanelControl.setBorder(BorderFactory.createEtchedBorder());
        JPanel jpanel2 = new JPanel();
        jpanel2.add(jPanelControl);
        jpanel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 1));
        // jPanelControl.setBackground(Color.getHSBColor());
        add(jPanelControl, BorderLayout.WEST);
        setJMenuBar(JMenuBar1);
        setJungControl();
        getContentPane().add(jung.getVv());
        JLabel jLabel = new JLabel("STATUS:");
        //  jLabel.add

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.LINE_AXIS));

        //jPanel.add(jLabel,BorderLayout.EAST);
        jPanel.setBorder(BorderFactory.createEtchedBorder());
        add(jPanel, BorderLayout.SOUTH);

        //skroty klawiszowe
        ItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        ItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        ItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

        jProgressBar.setAlignmentX(RIGHT_ALIGNMENT);
        JSeparator jSeparator = new JSeparator(JSeparator.VERTICAL);
        jSeparator.setAlignmentX(RIGHT_ALIGNMENT);
        jPanel.add(Box.createHorizontalGlue());
        // jPanel.add(jSeparator,Box.RIGHT_ALIGNMENT);
        jPanel.add(Box.createRigidArea(new Dimension(4, 0)));
        jPanel.add(jProgressBar, Box.RIGHT_ALIGNMENT);
        jProgressBar.setMinimumSize(new Dimension(200, 20));
        jProgressBar.setMaximumSize(new Dimension(200, 20));
//       jProgressBar.add(jPanelControl.gen);

        //zmiana rozmiaru okna powoduje przerysowanie grafu a to powduje zakutu
        //lizowanie jego wygladu i dopasowanie sie do okna 
        this.addComponentListener(new java.awt.event.ComponentAdapter() {

            public void componentResized(ComponentEvent e) {
                if (jItemAutoFit.isSelected()) {
                    jung.changeLayout(jComboLayout.getSelectedIndex());
                }
            }
        });
    }

    protected void ItemExitAction() {
        int ans = JOptionPane.showConfirmDialog(this, "Napewno chcesz wyjœæ z programu ?", "Exit?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("Exit.png"));

        if (ans == JOptionPane.YES_OPTION) {
            // Zamkniêcie okna
            if (jPanelControl.getGen() != null) {
                jPanelControl.getGen().myStop();
            }
            removeAll();
            dispose();
        }

    }

    protected void ItemOpenAction() {
        if (!genetic.Genetic.isIsWorking()) {

            int returnVal = file.showOpenDialog(Frame.this);
            try {
                AccessController.checkPermission(new FilePermission(file.getSelectedFile().getAbsolutePath(), "read,write"));
            } catch (SecurityException e) {

               
            }
            if (returnVal == JFileChooser.APPROVE_OPTION) {

                try {

                    jung.setG(Io.getGraph(file.getSelectedFile(), jung));
                    jung.changeLayout(jComboLayout.getSelectedIndex());

                } catch (IOException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Aby otworzyc plik najpierw zatrzymaj algorytm");
        }
    }

    protected void ItemSaveAction() {

        int returnVal = file.showSaveDialog(Frame.this);
        try {
                AccessController.checkPermission(new FilePermission(file.getSelectedFile().getAbsolutePath(), "read,write"));
            } catch (SecurityException e) {

               
            }
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File f = file.getSelectedFile();
            try {
                String string = f.getPath();
                if (!string.contains(".txt")) {
                    string = string + ".txt";
                }
                Io.saveGraph(jung.getG(), string);
            } catch (IOException ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setJungControl() {
        String[] comboList = {"FRLayout2", "KKLayout", "CircleLayout",
            "ISOMLayout", "SpringLayout", "SpringLayout2"};
        jComboLayout = new JComboBox(comboList);
        jComboLayout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                jung.changeLayout(cb.getSelectedIndex());
            }
        });
        // Border border = BorderFactory.createTitledBorder(
        //          BorderFactory.createRaisedBevelBorder(), "Edit graph layout and mouse mode:");

        modeMenu = jung.getGm().getModeComboBox();
        jPanelJungControls = new JPanel();
        jPanelJungControls.setLayout(new BoxLayout(jPanelJungControls, BoxLayout.LINE_AXIS));
        //jPanelJungControls.setBorder(BorderFactory.createRaisedBevelBorder());
        jComboLayout.setBorder(BorderFactory.createTitledBorder("Uk³ad grafu"));
        modeMenu.setBorder(BorderFactory.createTitledBorder("Tryby kursora:"));

        Box box = Box.createHorizontalBox();
        box.add(jComboLayout);
        box.add(Box.createRigidArea(new Dimension(10, 10)));
        box.add(modeMenu);
        box.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        jPanelJungControls.add(box);

        //jPanelJungControls.setBorder(BorderFactory.createRaisedBevelBorder());
        //dolny panel do zmiany wugladu i edycji grafu
        jPanelJungControls.setAlignmentY(BOTTOM_ALIGNMENT);
        jPanelJungControls.setMaximumSize(new Dimension(350, 60));
        jPanelControl.add(Box.createVerticalGlue());
        jPanelControl.add(jPanelJungControls, BorderLayout.LINE_END);

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Frame newframe = new Frame();
                newframe.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                newframe.setSize(1024, 768);
                newframe.setLocationRelativeTo(null);
                newframe.setVisible(true);

            }
        });

    }

    @Override
    public void windowClosing(WindowEvent e) {
        ItemExitAction();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    public Jung getJung() {
        return jung;
    }

    public JPanelControl getjPanelControl() {
        return jPanelControl;
    }

    public JProgressBar getjProgressBar() {
        return jProgressBar;
    }
}
