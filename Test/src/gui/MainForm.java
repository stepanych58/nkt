package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class MainForm
        extends JFrame
{
    private JFileChooser fileChooser = new JFileChooser();
    private JMenu fileMenu;
    private JMenuBar menuBar;
    private JMenuItem openDwellingItem;
    private JMenuItem openOfficeBuildingItem;
    private JMenu plafMenu;
    private JPanel planPanel;
    private JScrollPane scrollPane;

    public MainForm()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 4, screenSize.height / 4);
        initComponents();
    }




    private void readFromFile()
    {
        if (this.fileChooser.showOpenDialog(this) == 0) {
            try
            {
                FileReader in = new FileReader(this.fileChooser.getSelectedFile().getAbsoluteFile());
                int c;
                while ((c = in.read()) != -1)
                {
                    System.out.print((char)c);
                }
                in.close();
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(this, "File could not be read", "Error", 0);
            }
        }
    }

    private void initComponents()
    {
        this.scrollPane = new JScrollPane();
        this.planPanel = new JPanel();
        this.menuBar = new JMenuBar();
        this.fileMenu = new JMenu();
        this.openDwellingItem = new JMenuItem();
        this.plafMenu = new JMenu();
        ButtonGroup bGroup = new ButtonGroup();
        setBounds(200,200,300,300);
        final Map stylesMap = new HashMap();
        ActionListener styleListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    UIManager.setLookAndFeel((String)stylesMap.get(((JRadioButtonMenuItem)e.getSource()).getText()));
                    SwingUtilities.updateComponentTreeUI(MainForm.this.getContentPane());
                    SwingUtilities.updateComponentTreeUI(MainForm.this.menuBar);
                }
                catch (Throwable ex)
                {
                    JOptionPane.showMessageDialog(MainForm.this, "Can't apply selected style", "Error", 0);
                }
            }
        };
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        String currentName = UIManager.getLookAndFeel().getName();
        for (int i = 0; i < infos.length; i++)
        {
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(infos[i].getName(), false);
            bGroup.add(item);
            if (currentName.equals(infos[i].getName())) {
                item.setSelected(true);
            }
            item.addActionListener(styleListener);
            this.plafMenu.add(item);
            stylesMap.put(infos[i].getName(), infos[i].getClassName());
        }
        setDefaultCloseOperation(3);
        setTitle("Buildings");
        setResizable(true);


        GroupLayout planPanelLayout = new GroupLayout(this.planPanel);
        this.planPanel.setLayout(planPanelLayout);
        planPanelLayout.setHorizontalGroup(planPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 436, 32767));

        planPanelLayout.setVerticalGroup(planPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 421, 32767));

        this.scrollPane.setViewportView(this.planPanel);

        this.fileMenu.setText("File");

        this.openDwellingItem.setText("Open file with XY vect");
        this.openDwellingItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                MainForm.this.openDwellingItemActionPerformed(evt);
            }
        });
        this.fileMenu.add(this.openDwellingItem);

        ;


        this.menuBar.add(this.fileMenu);

        this.plafMenu.setText("Look&Feel");
        this.menuBar.add(this.plafMenu);

        setJMenuBar(this.menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        pack();
    }

    private void openDwellingItemActionPerformed(ActionEvent evt)
    {
        readFromFile();
    }

    private void openOfficeBuildingItemActionPerformed(ActionEvent evt)
    {
        readFromFile();
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new MainForm().setVisible(true);
            }
        });
    }
}
