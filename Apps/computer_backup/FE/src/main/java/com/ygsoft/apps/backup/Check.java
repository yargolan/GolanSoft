package com.ygsoft.apps.backup;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Check {

    public static void main ( String[] args )
    {
        JPanel middlePanel = new JPanel ();
        middlePanel.setBorder ( new TitledBorder( new EtchedBorder(), "Display Area" ) );

        // create the middle panel components

        JTextArea display = new JTextArea ( 10, 40 );
        display.setEditable ( true ); // set textArea non-editable
        JScrollPane scroll = new JScrollPane ( display );
        scroll.setVerticalScrollBarPolicy  (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        //Add Textarea in to middle panel
        middlePanel.add ( scroll );

        // My code
        JFrame frame = new JFrame ();
        frame.add ( middlePanel );
        frame.pack ();
        frame.setVisible ( true );
        frame.setLocationRelativeTo ( null );
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

