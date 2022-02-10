package com.smilin_dominator.devify.frontend;

import com.smilin_dominator.devify.backend.resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** This is the main UI class. It returns the logo and a choice of hashing or verifying */
public class Main extends JFrame implements ActionListener {

     private final Hash hashClass = new Hash();
     private final Verify verifyClass = new Verify();
     private final resources resourcesClass = new resources();

     public Main() {

          // Main Options
          setSize(485,800);
          setTitle("Devify - The Verifier!");
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          // Main Container
          JPanel overall = new JPanel();
          overall.setLayout(new BorderLayout());

          // The Buttons
          JPanel buttonContainer = new JPanel();
          buttonContainer.setLayout(new GridLayout(1, 2));

          JButton verify = new JButton("Verify");
          verify.addActionListener(this);

          JButton hash = new JButton("Hash");
          hash.addActionListener(this);

          buttonContainer.add(hash);
          buttonContainer.add(verify);

          // Logo
          JLabel logo = new JLabel(resourcesClass.image("logos", "DevifyLogo1.png"), JLabel.CENTER);

          // Final Options
          overall.add(logo, BorderLayout.CENTER);
          overall.add(buttonContainer, BorderLayout.PAGE_END);

          this.add(overall);
          setVisible(true);

     }

     public void actionPerformed(ActionEvent e) {
          String com = e.getActionCommand();
          switch (com) {
               case "Verify" -> verifyClass.run();
               case "Hash" -> hashClass.run();
          }
     }

     public static void main(String[] args) {
          new Main();
     }

}
