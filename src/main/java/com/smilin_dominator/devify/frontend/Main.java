// Main.java -> This is the main frontend of the program
/*
    Devify
    Copyright (C) 2021 Devisha Padmaperuma

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
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
               case "Verify" -> {
                    verifyClass.resetState();
                    verifyClass.run();
               }
               case "Hash" -> {
                    hashClass.resetState();
                    hashClass.run();
               }
          }
     }

     public static void main(String[] args) {
          new Main();
     }

}
