package com.company;

import daw.view.DAWGUIView;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        DAWGUIView.setDefaultLookAndFeelDecorated(true);
        DAWGUIView gui = new DAWGUIView();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}
