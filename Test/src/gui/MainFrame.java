package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static nkt.Lab1.*;

/**
 * Created by stbe on 25.10.2016.
 */
public class MainFrame extends JFrame
{
    private    JButton chooseFail = new JButton("Choose Fail with X, Y");
    private JButton getFunctionResult = new JButton("Get Result");
    private JFileChooser fileChooser = new JFileChooser();
    private JLabel labelU = new JLabel("Result text");
    private JLabel labelFuntion = new JLabel();
    private JLabel labelA= new JLabel("input a:");
    private JLabel labelAlpha= new JLabel("input alpha:");
    private JLabel labelk= new JLabel("input k:");
    private JTextField textFieldA= new JTextField();
    private JTextField textFieldAlpha= new JTextField();
    private JTextField textFieldK= new JTextField();
    List choise = new List(1, false);
    private Double u,a,k,alpha;

    public MainFrame()
    {
        super("nkt1");
        initialized();
        addActionListiners();

        choise.add("Heaviside");
        choise.add("Sigmoid");
        choise.add("Piecewise linear");
        choise.add("Bipolar sigmoid");
        choise.add("Pow");

        setView();
        defaultField();
    }

    public void addActionListiners()
    {
        choise.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(choise.isIndexSelected(0))
                {
                    defaultField();
                }
                else if(choise.isIndexSelected(1)||choise.isIndexSelected(2))
                {
                    defaultField();
                    textFieldA.setEnabled(true);
                }
                else if(choise.isIndexSelected(3))
                {
                    defaultField();
                    textFieldAlpha.setEnabled(true);
                }
                else if(choise.isIndexSelected(4))
                {
                    defaultField();
                    textFieldK.setEnabled(true);
                }
                else
                {
                    defaultField();
                }
            }
        });

        chooseFail.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                u = MainFrame.this.openFileWithVectors(event);
                labelU.setText("u=X*Y="+u);
            }
        });

        getFunctionResult.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(choise.isIndexSelected(0))
                {
                    labelFuntion.setText(choise.getItem(0)+" f(u)="+getHevisaid(u));
                }
                else if(choise.isIndexSelected(1)||choise.isIndexSelected(2))
                {
                    a = Double.parseDouble(textFieldA.getText());
                    labelFuntion.setText(choise.getItem(1)+" f(u)="+getSigmoid(u,a));
                }
                else if(choise.isIndexSelected(3))
                {
                    alpha = Double.parseDouble(textFieldAlpha.getText());
                    labelFuntion.setText(choise.getItem(3)+" f(u)="+getSigmoidBipol(u,alpha));
                }
                else if(choise.isIndexSelected(4))
                {
                    k = Double.parseDouble(textFieldK.getText());
                    labelFuntion.setText(choise.getItem(4)+" f(u)="+Math.pow(u,k));
                }
                else
                {
                }
            }
        });

    }

    private void setView()
    {
        chooseFail.setBounds(10,10,200,20);
        choise.setBounds(10,40,200,80);
        labelA.setBounds(10,125,70,20);
        textFieldA.setBounds(10,145,200,20);
        labelAlpha.setBounds(10,165,70,20);
        textFieldAlpha.setBounds(10,185,200,20);
        labelk.setBounds(10,205,70,20);
        textFieldK.setBounds(10,225,200,20);
        getFunctionResult.setBounds(10,250,200,20);
        labelU.setBounds(10,275,200,20);
        labelFuntion.setBounds(10,290,200,20);

        add(chooseFail);
        add(choise);
        add(labelU);
        add(labelA);
        add(textFieldA);
        add(labelAlpha);
        add(textFieldAlpha);
        add(labelk);
        add(textFieldK);
        add(getFunctionResult);
        add(labelFuntion);
    }

    private void initialized()
    {
        setLayout(null);
        setSize(new Dimension(225, 360));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void defaultField()
    {
        textFieldA.setEnabled(false);
        textFieldAlpha.setEnabled(false);
        textFieldK.setEnabled(false);
    }

    private Double openFileWithVectors(ActionEvent evt)
    {
        return readFromFile();
    }

    private Double readFromFile()
    {
        if (this.fileChooser.showOpenDialog(this) == 0)
        {
            return getU(readFile(this.fileChooser.getSelectedFile().getAbsoluteFile()));
        }
        return null;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new MainFrame().setVisible(true);
            }
        });
    }
}

