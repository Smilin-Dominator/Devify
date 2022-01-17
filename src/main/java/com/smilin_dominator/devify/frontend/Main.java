package com.smilin_dominator.devify.frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** This is the main UI class. It returns the logo and a choice of hashing or verifying */
public class Main extends JFrame implements ActionListener {

     public Main() {

          // Main Options
          setSize(485,800);
          setTitle("Devify - The Verifier!");
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     }

     /**
      * Sets the JFrame Visible
      */
     public void run() {
          setVisible(true);
     }

     public void actionPerformed(ActionEvent e) {

     }

     public static void main(String[] args) {

     }

}
