package xpta;

import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class CalcApplet extends JApplet implements ActionListener
{
    JTextField num1 = new JTextField("0.00");
    JTextField num2 = new JTextField("0.00");

    JLabel answer = new JLabel("0.00");
    JLabel symbol = new JLabel("+");
    JLabel equals = new JLabel("=");

    JButton addButton = new JButton(" + ");
    JButton subButton = new JButton(" - ");

    public void init()
    {
        JPanel fields = new JPanel(new FlowLayout());
        fields.add(num1);
        fields.add(symbol);
        fields.add(num2);
        fields.add(equals);
        fields.add(answer);

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(addButton);
        buttons.add(subButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add("North", fields);
        getContentPane().add("Center", buttons);

        addButton.addActionListener(this);
        subButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        double x = Double.parseDouble(num1.getText());
        double y = Double.parseDouble(num2.getText());
        if (e.getSource() == addButton)
        {
            answer.setText(String.valueOf( x + y ));
            symbol.setText("+");
        }
        if (e.getSource() == subButton)
        {
            answer.setText(String.valueOf( x - y ));
            symbol.setText("-");
        }
    }
}
