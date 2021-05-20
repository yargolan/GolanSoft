package com.ygsoft.apps;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LottoUi extends JFrame implements ActionListener {

    private final JButton bUpdate, bGuess;
//    private final PythonActions pythonActions = new PythonActions();



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bUpdate) {
            System.out.println("Update");
            PythonActions pythonActions = new PythonActions();
            pythonActions.update();
        }
        else if (e.getSource() == bGuess) {
            System.out.println("Guess");
            PythonActions pythonActions = new PythonActions();
            pythonActions.guess();
        }
        else {
            System.err.println("BOO !!");
        }
    }



    public LottoUi() {
        setTitle("Registration Form");
        setBounds(300, 90, 500, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Container c = getContentPane();
        c.setLayout(null);

        JLabel title = new JLabel("Lotto draws");
        title.setFont(new Font("Arial", Font.PLAIN, 25));
        title.setSize(300, 30);
        title.setLocation(150, 30);
        c.add(title);

        bUpdate = new JButton("Update draw results");
        bUpdate.setSize(150, 50);
        bUpdate.setLocation(50, 100);
        bUpdate.addActionListener(this);
        c.add(bUpdate);

        bGuess  = new JButton("Guess next numbers");
        bGuess.setSize(150, 50);
        bGuess.setLocation(250, 100);
        bGuess.addActionListener(this);
        c.add(bGuess);





        setVisible(true);
    }
}
