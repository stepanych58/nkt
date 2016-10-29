package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by stbe on 26.10.2016.
 */
public class AdditionalInputFrame extends JFrame
{
    private JLabel inputLabel = new JLabel();
    private JTextField inputField = new JTextField();
    private JButton ok = new JButton("ok");
    private JButton cancel = new JButton("cancel");

    private Double a;

    public Double getA() {
        return a;
    }
    AdditionalInputFrame(String text)
    {
        super("Input Frame");
        setLayout(null);
        setSize(new Dimension(160, 100));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        inputLabel.setText(text);


        inputLabel.setBounds(10,5,80,10);
        inputField.setBounds(10,20,135,20);

        ok.setBounds(10,45,50,20);
        cancel.setBounds(65,45,80,20);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    a = Double.parseDouble(inputField.getText());
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(new Frame(), "a input not true", "Inane error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        add(inputLabel);
        add(ok);
        add(cancel);
        add(inputField);

    }

    public static void main(String[] args) {


    }
}
